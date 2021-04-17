import java.util.ArrayList;
import java.util.Collections;

public class ListaPedidos {
    private ArrayList<Pedido> listaPedidos;

    public ListaPedidos() {
        this.listaPedidos = new ArrayList<>();
    }

    public Pedido get(int index) {
        return this.listaPedidos.get(index);
    }

    public boolean add(Pedido newPedido){
        return this.listaPedidos.add(newPedido);
    }

    public int size(){
        return this.listaPedidos.size();
    }

    public int compareTo(Pedido first, Pedido second){
        return this.listaPedidos.get(first.getPrioridade()).compareTo(second);
    }

    public void sort (){
        Collections.sort(listaPedidos);
    }

}
