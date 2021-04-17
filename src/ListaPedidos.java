import java.util.ArrayList;
import java.util.Collections;

public class ListaPedidos {
    private ArrayList<Pedido> listaPedidos;
    private int qtdPacotesTotal;
    private int volumeTotal;

    public ListaPedidos() {
        this.listaPedidos = new ArrayList<>();
        this.qtdPacotesTotal = 0;
    }

    public Pedido get(int index) {
        return this.listaPedidos.get(index);
    }

    public void addPacotes(int numPacotes) {
        this.qtdPacotesTotal += numPacotes;
    }

    public void add(Pedido newPedido){
        this.listaPedidos.add(newPedido);
        this.addPacotes( newPedido.getQtdPacotesTotal() );
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
