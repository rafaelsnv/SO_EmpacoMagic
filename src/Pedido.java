public class Pedido {
   private String cliente;
   private int totalProdutos;
   private int prazo;
   private int prioridade;

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
      if (this.prazo == 0) {
         this.prioridade = ((Integer.MAX_VALUE/1_000_000)*60) * 10_000 + totalProdutos;
      }
      else {
         this.prioridade = prazo * 10_000 + totalProdutos;
      }
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

   public int getPrioridade() {
      return prioridade;
   }

   public void setPrioridade(int prioridade) {
      this.prioridade = prioridade;
   }

   public int compareTo (Pedido second){
      return this.getPrioridade() - second.getPrioridade();
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
