import java.util.ArrayList;
import java.util.Collections;

public class ListaPedidos {
    private final ArrayList<Pedido> listaPedidos;
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
            return null;
        else
            return this.listaPedidos.get(index);
    }

    public void addPacotes(int numPacotes) {
        this.qtdPacotesTotal += numPacotes;
    }

    public void add(Pedido novo) {
        this.listaPedidos.add(novo);
        this.addPacotes( novo.getQtdPacotes() );
    }

    public int size(){
        return this.listaPedidos.size();
    }

    public int getQtdPacotesTotal(){
        return this.qtdPacotesTotal;
    }

    public ArrayList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void sort() {
        Collections.sort(listaPedidos);
    }

    public void remove(Pedido p){
        this.listaPedidos.remove(p);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("ORDEM DE EXECUÇÃO DOS PEDIDOS: \n");
        for (Pedido p:this.listaPedidos) {
            String aux = "\n" + p.toString();
            result.append(aux);
        }
        return result.toString();
    }
}