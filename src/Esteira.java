public class Esteira {
   private static final int VOL_MAX_PACOTE = 5000;
   private static final int VOL_PRODUTO = 250;
   private static final double TEMPO_ROLAMENTO = 0.5;
   private static final double TEMPO_EMPACOTAMENTO = 5.0;

   private ListaPedidos listaPedidos;
   private int volumeTotal = 0;
   private int totalProdutos = 0;
   private double tempoEmpacotando;

   public Esteira() {
      this.listaPedidos = new ListaPedidos();
      this.volumeTotal = 0;
      this.totalProdutos = 0;
   }

   /**
    * O método deve retornar o tempo médio gasto para executar o lote de produtos.
    * @return Sempre retorna o tempo médio de empacotamento do lote.
    */
   public double getTempoMedio() {
      return 0.0;
   }

   /**
    * O método serve para retornar a quantidade de pacotes possíveis de serem
    * produzidos no intervalo de tempo inserido.
    * @param listaPedidos A.
    */
   public void setListaPedidos(ListaPedidos listaPedidos) {
      this.listaPedidos = listaPedidos;
   }

   /**
    * Método para empacotar todos os produtos da lista
    * @param pedidosList A
    * @param inicio A
    */
   public void empacotarPedidos(ListaPedidos pedidosList, Horario inicio) {

   }
}
