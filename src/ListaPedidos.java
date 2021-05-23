import java.util.ArrayList;
import java.util.Collections;

public class ListaPedidos {
    private ArrayList<Pedido> listaPedidos;
    private int qtdPacotesTotal;

    /**
     * Construtor sem parâmetros
     */
    public ListaPedidos() {
        this.listaPedidos = new ArrayList<>();
        this.qtdPacotesTotal = 0;
    }

    /**
     * 
     * @param index Posição da lista
     * @return  Se vazio, cria pedido; Com item, retorna o pedido
     */
    public Pedido get(int index) {
        if(this.listaPedidos.isEmpty())
            return new Pedido();
        else
            return this.listaPedidos.get(index);
    }

    public void update(int index, Pedido novo) {
        this.listaPedidos.set(index, novo);
    }

    public void addPacotes(int numPacotes) {
        this.qtdPacotesTotal += numPacotes;
    }

    public void add(Pedido novo) {
        this.listaPedidos.add(novo);
        this.addPacotes( novo.getQtdPacotes() );
    }

    public boolean isEmpty() {
        return this.qtdPacotesTotal == 0;
    }

    public int size(){
        return this.listaPedidos.size();
    }

    public int getQtdPacotesTotal(){
        return this.qtdPacotesTotal;
    }

    public void sort() {
        Collections.sort(listaPedidos);
    }
}
