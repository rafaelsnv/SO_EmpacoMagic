public class Esteira extends Thread {
   private static final double TEMPO_ROLAMENTO = 0.5;    // segundos
   private static final double TEMPO_EMPACOTAMENTO = 5;  // segundos

   private final int meuID;
   private final SyncFila fila;
   private final SyncRelatorio relatorio;
   private SyncListContainer containeres;
   public final Horario meuRelogio;

   public Esteira(int ID, SyncFila fila, SyncRelatorio relatorio, SyncListContainer containeres) {
      this.meuID = ID;
      this.fila = fila;
      this.relatorio = relatorio;
      this.containeres = containeres;
      this.meuRelogio = new Horario();
   }

   public void empacotar() throws CloneNotSupportedException {
      if(fila.size() > 0) {
         Pedido pedido = fila.pop();

         int qtdProdutos = pedido.getQtdProdutos();
         Container cont = containeres.getContainer(pedido.getID());

         if (qtdProdutos > cont.getQtdAtualProdutos()) {
            qtdProdutos -= cont.getQtdAtualProdutos();
            cont.reabastecer();
            this.meuRelogio.addSeconds(cont.getTempoTroca());
         }

         cont.consumirProdutos(qtdProdutos);

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