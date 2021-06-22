public class Esteira extends Thread {
   private static final int TEMPO_ROLAMENTO = 5;       // deciseconds (1 sec = 10 deciseconds)
   private static final int TEMPO_EMPACOTAMENTO = 50;  // deciseconds (1 sec = 10 deciseconds)
   private static final Object lock = new Object();
   private static int numEsteirasAtivas = 0;
   private static Esteira anterior;

   private final int meuID;
   public final SyncList fila;
   public final SyncRelatorio relatorio;
   public final SyncRelogio relogio;
   public final ListaPedidos listaTodos;

   public Esteira(int ID, SyncList fila, SyncRelatorio relatorio, ListaPedidos listaTodos, SyncRelogio relogio) {
      this.meuID = ID;
      this.fila = fila;
      this.relatorio = relatorio;
      this.relogio = relogio;
      this.listaTodos = listaTodos;
      numEsteirasAtivas += 1;
   }

   private void addDecisecRelogio(Esteira me) throws InterruptedException {
      synchronized (lock) {
         while (anterior == me & numEsteirasAtivas > 1)
            lock.wait();

         relogio.addSeconds(0.1 / numEsteirasAtivas);
         anterior = me;

         if(numEsteirasAtivas > 1)
            lock.notifyAll();
      }
   }

   public void empacotar(Esteira me) throws InterruptedException {
      if(fila.getSize() > 0) {
         Pedido pedido = fila.getFirst();

         int tempoEmpacotamento = (TEMPO_EMPACOTAMENTO + TEMPO_ROLAMENTO) * pedido.getQtdPacotes();
         for (int dsec = 1; dsec <= tempoEmpacotamento; dsec++)
            this.addDecisecRelogio(this);

         synchronized (lock) {
            while (anterior == me & numEsteirasAtivas > 1)
               lock.wait();

            Horario horarioAtual = new Horario(this.relogio.getHorarioGeral().toSeconds());
            pedido.setConclusao(horarioAtual, this.meuID);
            relatorio.addToList(pedido);

            anterior = me;

            if(numEsteirasAtivas > 1)
               lock.notifyAll();
         }
      }
   }

   public void run() {
      while (this.listaTodos.size() > 0 | this.fila.getSize() > 0)
         try {
            this.empacotar(this);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }

      synchronized (lock) {
         while (anterior == this & numEsteirasAtivas > 1)
            try {
               lock.wait();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }

         numEsteirasAtivas--;
         System.out.println("Esteira " + this.meuID + " finalizou!");

         if(numEsteirasAtivas != 0)
            lock.notifyAll();
      }

   }

}