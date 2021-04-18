public class EsteiraSJF extends Esteira {

   public EsteiraSJF() {
      super();
   }

   @Override
   public void setListaPedidos(ListaPedidos listaPedidos) {
      listaPedidos.sort();
      this.listaPedidos = listaPedidos;
   }
}
