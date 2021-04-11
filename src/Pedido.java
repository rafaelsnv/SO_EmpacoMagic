public class Pedido {
   private String cliente;
   private int totalProdutos;
   private int prazo;

   /**
    * Método Construtor
    * @param qualCliente O nome do cliente
    * @param quantoTotal A quantidade total de produtos
    * @param qualPrazo O prazo para entrega do pedido
    */
   public Pedido (String qualCliente, int quantoTotal, int qualPrazo){
      this.cliente = qualCliente;
      this.totalProdutos = quantoTotal;
      this.prazo  = qualPrazo*60;
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

   public void setTotalProdutos(int totalProdutos) {
      this.totalProdutos = totalProdutos;
   }

   public int getTotalProdutos() {
      return totalProdutos;
   }

   /**
    * Método conversor para impressão
    * @return Uma string com os valores armazenados no objeto
    */
   @Override
   public String toString() {
      return "Pedido" +
              "\nCliente: '" + cliente +
              "\nNúmero total de produtos: " + totalProdutos +
              "\nPrazo: " + (prazo/60) + " minutos";
   }
}
