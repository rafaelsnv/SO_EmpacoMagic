public class Escalonador {
    private ListaPedidos listaTodos;
    public final SyncList listEmpacotar = new SyncList();

    public Escalonador(ListaPedidos list) {
        this.setListaTodos(list);
    }

    private void setListaTodos(ListaPedidos list) {
         this.listaTodos = list;
    }

    public ListaPedidos getListaTodos() {
        return this.listaTodos;
    }

    private ListaPedidos collect(SyncRelogio generalClock){
        ListaPedidos pedidosValidos = new ListaPedidos();
        for (Pedido pedido: listaTodos.getListaPedidos()){
            Horario tempoChegada = pedido.getHoraDeChegada();
            Horario horarioGeral = generalClock.getHorarioGeral();
            if (horarioGeral.compareTo(tempoChegada)<=0) {
                pedidosValidos.add(pedido);
                listaTodos.remove(pedido);
            }
        }
        return pedidosValidos;
    }
}
