import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ListaPedidos {
    private ArrayList<Pedido> prazoArrayList;

    public ListaPedidos() {
        this.prazoArrayList = new ArrayList<>();
    }

    public Pedido get(int index) {
        return this.prazoArrayList.get(index);
    }

    public boolean add(Pedido newPedido){
        return this.prazoArrayList.add(newPedido);
    }

    public int size(){
        return this.prazoArrayList.size();
    }

    public int compareTo(Pedido first, Pedido second){
        return this.prazoArrayList.get(first.getPrioridade()).compareTo(second);
    }

    public void sort (){
        Collections.sort(this.prazoArrayList, new Comparator<Pedido>() {
            @Override
            public int compare(Pedido p1, Pedido p2) {
                return p1.compareTo(p2);
            }
        });
    }

}
