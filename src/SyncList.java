import java.util.ArrayList;

/**
 * Fila que irá conter somente os pedidos já prontos e aqueles que chegarem.
 */
public class SyncList {
    private final ListaPedidos filaPedidos = new ListaPedidos();

    /**
     * Construtor da fila de Pedidos prontos para empacotamento.
     */
    public SyncList() {
    }

    /**
     * Método sincronizado para adicionar um  na fila.
     * @param pedidos A lista de pedidos a serem adicionados.
     */
    public synchronized void addToList(ArrayList<Pedido> pedidos){
        for (Pedido p:pedidos) {
            this.filaPedidos.add(p);
        }
        this.filaPedidos.sort();
    }

    /**
     * Método sincronizado para obter o primeiro  da fila, ou seja, o com maior prioridade.
     * @return o  com maior prioridade;
     */
    public synchronized Pedido getFirst(){
        Pedido pedido = this.filaPedidos.get(0);
        this.filaPedidos.remove(pedido);
        return pedido;
    }

    /**
     * Método sincronizado para remover um  da fila.
     * @param pedido o  a ser removido da fila.
     */
    public synchronized void removeAtList(Pedido pedido){
        this.filaPedidos.remove(pedido);
    }

    /**
     * Método sincronizado para obter o tamanho da fila.
     * @return o tamanho da fila.
     */
    public synchronized int getSize(){
        return this.filaPedidos.size();
    }

    public synchronized String toString(){
        return this.filaPedidos.toString();
    }
}