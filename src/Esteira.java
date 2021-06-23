public class Esteira extends Thread {
   private static final double TEMPO_ROLAMENTO = 0.5;    // segundos
   private static final double TEMPO_EMPACOTAMENTO = 5;  // segundos

   private final int meuID;
   public final SyncFila fila;
   public final SyncRelatorio relatorio;
   public final Horario meuRelogio;

   public Esteira(int ID, SyncFila fila, SyncRelatorio relatorio) {
      this.meuID = ID;
      this.fila = fila;
      this.relatorio = relatorio;
      this.meuRelogio = new Horario();
   }

   public void empacotar() {
      if(fila.size() > 0) {
         Pedido pedido = fila.pop();

         double tempoEmpacotamento = (TEMPO_EMPACOTAMENTO + TEMPO_ROLAMENTO) * pedido.getQtdPacotes();
         this.meuRelogio.addSeconds(tempoEmpacotamento);

         Horario horarioAtual = new Horario(this.meuRelogio.toSeconds());
         pedido.fecharPedido(horarioAtual, this.meuID);
         relatorio.append(pedido);
      }
   }

   public void run() {

   }

}