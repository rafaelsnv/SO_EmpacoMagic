import java.util.ArrayList;

public class Esteira extends Thread {
   private static final int TEMPO_ROLAMENTO = 5;       // deciseconds (1 sec = 10 deciseconds)
   private static final int TEMPO_EMPACOTAMENTO = 50;  // deciseconds (1 sec = 10 deciseconds)
   private static final Object lock = new Object();
   private static int numEsteirasAtivas = 0;
   private static Esteira anterior;

   private final int meuID;
   private final int numPacotesEmpacotar;
   public final SyncList fila;
   public final SyncList relatorio;
   public final SyncRelogio relogio;

   public Esteira(int ID, int numPacotes, SyncList fila, SyncList relatorio, SyncRelogio relogio) {
      this.meuID = ID;
      this.numPacotesEmpacotar = numPacotes;
      this.fila = fila;
      this.relatorio = relatorio;
      this.relogio = relogio;
      numEsteirasAtivas += 1;
   }

   private void addDecisecRelogio(Esteira me) throws InterruptedException {
      synchronized (lock) {
         while (anterior == me)
            lock.wait();

         relogio.addSeconds(0.1 / numEsteirasAtivas);
         anterior = me;
         lock.notifyAll();
      }
   }

   public void empacotar(Esteira me) throws InterruptedException {
      Pedido pedido = fila.getFirst();

      int tempoEmpacotamento = (TEMPO_EMPACOTAMENTO + TEMPO_ROLAMENTO) * pedido.getQtdPacotes();
      for (int dsec = 1; dsec <= tempoEmpacotamento; dsec++)
         this.addDecisecRelogio(this);

      synchronized (lock) {
         while (anterior == me)
            lock.wait();

         pedido.setConclusao(this.relogio.getHorarioGeral());
         relatorio.addToList(pedido);

         anterior = me;
         lock.notifyAll();
      }
   }

   public void run() {
      for (int i = 0; i < this.numPacotesEmpacotar; i++) {
         try {
            this.empacotar(this);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         System.out.println("Esteira " + this.meuID + " finalizou!");
         numEsteirasAtivas -= 1;
      }
   }
}