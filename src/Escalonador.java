public class Escalonador extends Thread{
    private ListaPedidos listaTodos;
    public SyncList filaEmpacotar;
    public SyncRelogio relogio;

    public Escalonador(ListaPedidos listaPedidosTodos, SyncList filaEmpacotar, SyncRelogio relogio) {
        this.listaTodos = listaPedidosTodos;
        this.filaEmpacotar = filaEmpacotar;
        this.relogio = relogio;
    }

    public ListaPedidos getListaTodos() {
        return this.listaTodos;
    }

    private void updateFilaEmpacotamento() {
        ListaPedidos pedidosValidos = new ListaPedidos();
        for (Pedido pedido : listaTodos.getListaPedidos()){
            Horario tempoChegada = pedido.getHoraDeChegada();
            if (tempoChegada.compareTo(relogio.getHorarioGeral())<=0)
                pedidosValidos.add(pedido);
            else
                break;
        }

        for (Pedido pedido : pedidosValidos.getListaPedidos())
            listaTodos.remove(pedido);

        if(pedidosValidos.size() > 0)
            filaEmpacotar.addToList(pedidosValidos);
    }

    public void run() {
        while (this.listaTodos.size()>0){
            updateFilaEmpacotamento();
        }
        System.out.println("Escalonador finalizou!");
    }

}