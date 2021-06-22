public class Conteiner {
    private static final int MAX_VOLUME_CONTEINER = 1000000;        // valores em cm³
    private static final int trocaConteiner = 30;                   // valores em segundos

    private int ID;
    public final ListaPedidos listaPedidosUnicos;

    /**
     * Construtor Vazio
     * ID = 0
     * listaPedidosUnicos = vazia
     */
    public Conteiner(){
        this.ID = 0;
        this.listaPedidosUnicos = new ListaPedidos();
    }
   
    /**
     * Construtor com parâmetros
     * @param ID                    ID de identificação do conteiner. Ideia inicial do ID ser = código do produto
     * @param listaPedidosUnicos    É a lista de produtos correspondente ao código do produto importado
     */
    public Conteiner(int ID, ListaPedidos listaPedidosUnicos) {
        this.ID = ID;
        this.listaPedidosUnicos = listaPedidosUnicos;    
    }

    /**
     * Método serve para verificar se a lista está vazia
     * @return False != 0  
     */
    public boolean isEmpty(){
        if (this.listaPedidosUnicos.size() != 0) {
            return false;   
        }
            return true;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public ListaPedidos getListaPedidosUnicos() {
        return listaPedidosUnicos;
    }

    public int getTrocaConteiner() {
        return trocaConteiner;
    }

    public static int getMaxVolumeConteiner() {
        return MAX_VOLUME_CONTEINER;
    }

    
}
