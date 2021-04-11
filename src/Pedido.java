public class Pedido {
   private String cliente;
   private int totalProdutos;
   private int prazo;

   public Pedido (String qualCliente, int qualTotal, int qualPrazo){
      this.cliente = qualCliente;
      this.totalProdutos = qualTotal;
      this.prazo  = qualPrazo;
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

   @Override
   public String toString() {
      return "Pedido" +
              "\nCliente: '" + cliente +
              "\nNúmero total de produtos: " + totalProdutos +
              "\nPrazo: " + prazo;
   }
}
