/**
 * Fila que irá conter somente os pedidos já prontos e aqueles que chegarem.
 */
public class FilaEmpacotar {
    private final ListaPedidos filaPedidos = new ListaPedidos();

    /**
     * Construtor da fila de Pedidos prontos para empacotamento.
     */
    public FilaEmpacotar() {
    }

    /**
     * Método sincronizado para adicionar um  na fila.
     * @param pedido O novo pedido a ser adicionado.
     */
    public synchronized void addToList(Pedido pedido){
        this.filaPedidos.add(pedido);
    }

    /**
     * Método sincronizado para obter o primeiro  da fila, ou seja, o com maior prioridade.
     * @return o  com maior prioridade;
     */
    public synchronized Pedido getFirst(){
        return this.filaPedidos.get(0);
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
}