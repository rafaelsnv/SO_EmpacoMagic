public class Esteira extends Thread {
   private static final double TEMPO_ROLAMENTO = 0.5;    // segundos
   private static final double TEMPO_EMPACOTAMENTO = 5;  // segundos

   private final int meuID;
   private final SyncFila pedidos;
   private final SyncRelatorio relatorio;
   private final SyncListContainer containeres;
   public final Horario meuRelogio;

   public Esteira(int ID, SyncFila pedidos, SyncRelatorio relatorio, SyncListContainer containeres) {
      this.meuID = ID;
      this.pedidos = pedidos;
      this.relatorio = relatorio;
      this.containeres = containeres;
      this.meuRelogio = new Horario();
   }

   public void empacotar() throws CloneNotSupportedException {
      if(pedidos.size() > 0) {
         Pedido pedido = pedidos.pop();

         int qtdProdutos = pedido.getQtdProdutos();

         // START: Proteger esta transação
         Container cont = containeres.getContainer(pedido.getID());

         if (qtdProdutos > cont.getQtdAtualProdutos()) {
            qtdProdutos -= cont.getQtdAtualProdutos();
            cont.reabastecer();
            this.meuRelogio.addSeconds(cont.getTempoTroca());
         }

         cont.consumirProdutos(qtdProdutos);
         // END: Proteger esta transação

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