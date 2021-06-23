import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A fila irá conter a lista de pedidos carregados pelo leitor
 * ordenados por prioridade.
 */
public class SyncFila {
    private final List<Pedido> fila;

    /**
     * Construtor da fila de Pedidos prontos para empacotamento.
     */
    public SyncFila() {
        ArrayList<Pedido> fila = new ArrayList<>();
        this.fila = Collections.synchronizedList(fila);
    }

    /**
     * Método sincronizado para adicionar um  na fila.
     * @param pedidos A lista de pedidos a serem adicionados.
     */
    public void push(Pedido pedido){
        this.fila.add(pedido);
    }

    /**
     * Método sincronizado para obter o primeiro  da fila, ou seja, o com maior prioridade.
     * @return o  com maior prioridade;
     */
    public Pedido pop(){
        Pedido pedido = this.fila.get(0);
        this.fila.remove(pedido);
        return pedido;
    }

    /**
     * Método sincronizado para obter o tamanho da fila.
     * @return o tamanho da fila.
     */
    public int size(){
        return this.fila.size();
    }

    public void sort() {
        Collections.sort(this.fila);
    }
}