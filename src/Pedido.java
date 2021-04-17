public class Pedido implements Comparable<Pedido> {
   private static final int PRAZO_ZERO_BASE = (Integer.MAX_VALUE/1_000_000)*60*10_000;
   private static final int PRAZO_NORMAL_BASE = 10_000;
   private static final int VOL_PACOTE_MAX = 5000;            // cm^3
   private static final int VOL_PRODUTO = 250;                // cm^3

   private int id;
   private String cliente;
   private int qtdProdutos;
   private int prazo;
   private int prioridade;

   /**
    * Método Construtor
    * @param qualCliente O nome do cliente
    * @param quantoProdutos A quantidade total de produtos
    * @param qualPrazo O prazo para entrega do pedido
    */
   public Pedido (int id, String qualCliente, int quantoProdutos, int qualPrazo) {
      this.setID(id);
      this.setCliente(qualCliente);
      this.setQtdProdutos(quantoProdutos);
      this.setPrazo(qualPrazo * 60);
      this.setPrioridade(qualPrazo);
   }

   public void setID(int id) {
      this.id = id;
   }

   public int getID() {
      return this.id;
   }

   public void setCliente(String cliente) {
      this.cliente = cliente;
   }

   public String getCliente() {
      return cliente;
   }

   public void setPrazo(int prazo) {
      this.prazo = prazo;
   }

   public int getPrazo() {
      return prazo;
   }

   public void setQtdProdutos(int qtdProdutos) {
      this.qtdProdutos = qtdProdutos;
   }

   public int getQtdProdutos() {
      return qtdProdutos;
   }

   public void setPrioridade(int prazo) {
      if (this.prazo == 0)
         this.prioridade = PRAZO_ZERO_BASE + qtdProdutos;
      else
         this.prioridade = prazo * PRAZO_NORMAL_BASE + qtdProdutos;
   }

   public int getPrioridade() {
      return this.prioridade;
   }

   public int getQtdPacotesTotal() {
      return this.qtdProdutos * VOL_PRODUTO / VOL_PACOTE_MAX;
   }

   @Override
   public int compareTo (Pedido second){
      return this.getPrioridade() - second.getPrioridade();
   }

   /**
    * Método conversor para impressão
    * @return Uma string com os valores armazenados no objeto
    */
   @Override
   public String toString() {
      String pedido = String.format("Pedido nº %03d | ", this.getID());
      String cliente = String.format("Cliente: %-30s | ", this.getCliente());
      String numProdutos = String.format("Nº produtos: %-5d | ", this.getQtdProdutos());
      String prazo = String.format("Prazo: %d min", this.getPrazo());

      StringBuilder builder = new StringBuilder(pedido);
      builder.append(cliente);
      builder.append(numProdutos);
      builder.append(prazo);

      return builder.toString();
   }
}
