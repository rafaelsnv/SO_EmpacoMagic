public class Escalonador extends Thread{
    private ListaPedidos listaTodos;
    public SyncList filaEmpacotar;

    public Escalonador(ListaPedidos listaPedidosTodos, SyncList filaEmpacotar) {
        this.setListaTodos(listaPedidosTodos);
        this.filaEmpacotar = filaEmpacotar;
    }

    private void setListaTodos(ListaPedidos list) {
         this.listaTodos = list;
    }

    public ListaPedidos getListaTodos() {
        return this.listaTodos;
    }

    private void updateFilaEmpacotamento(SyncRelogio generalClock, SyncList filaEmpacotar){
        ListaPedidos pedidosValidos = new ListaPedidos();
        for (Pedido pedido: listaTodos.getListaPedidos()){
            Horario tempoChegada = pedido.getHoraDeChegada();
            Horario relogioGeral = generalClock.getHorarioGeral();
            if (relogioGeral.compareTo(tempoChegada)<=0) {
                pedidosValidos.add(pedido);
                listaTodos.remove(pedido);
            }
        }
        filaEmpacotar.addToList(pedidosValidos);
    }

    public void run() {
        while (this.listaTodos.size()>0){
            updateFilaEmpacotamento(relogio.getHorarioGeral());
        }
        System.out.println("Escalonador encerrado.");
    }
}