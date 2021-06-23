public class Container {
    private static final int MAX_VOLUME_CONTAINER = 1000000; // valores em cm³
    private static final int TEMPO_TROCA_CONTAINER = 30; // valores em segundos
    private int idade;
    private final int qtdMaxProdutos;
    private int qtdAtualProdutos;
    private int produtoID;

    /**
     * Construtor com parâmetros
     * 
     * @param ID     ID de identificação do conteiner. Ideia inicial do ID ser =
     *               código do produto
     * @param produtoVol (int) - Volume do produto
     */
    public Container(int produtoID, int produtoVol) {
        this.produtoID = produtoID;
        this.idade = 0;
        this.qtdMaxProdutos = this.qtdAtualProdutos = MAX_VOLUME_CONTAINER / produtoVol;
    }

    /**
     * Método serve para verificar se o conteiner está vazio
     * 
     * @return False != 0
     */
    public boolean isEmpty() {
        return this.qtdAtualProdutos == 0;
    }

    public int getProdutoID() {
        return this.produtoID;
    }

    public void setProdutoID(int produtoID) {
        this.produtoID = produtoID;
    }

    public int getTempoTroca() {
        return TEMPO_TROCA_CONTAINER;
    }

    public int getQtdAtualProdutos() {
        return this.qtdAtualProdutos;
    }

    /**
     * Método para inserir a quantidade máxima de produtos no conteiner
     */
    public void refresh() {
        this.qtdAtualProdutos = this.qtdMaxProdutos;
        // atualizar idade?
    }

    /**
     * Método para incrementar a idade do conteiner sempre que ele for utilizado
     */
    public void increaseIdade() {
        this.idade += 8;
        if (this.idade > 15)
            System.out.println("Idade inválida!"); // Somente um teste para sermos informados em caso de falha
        System.out.println("Container " + this.produtoID + " idade: " + this.idade);
    }

    /**
     * Método para decrementar a idade do conteiner sempre que um ciclo terminar
     */
    public void decreaseIdade() {
        this.idade /= 2;
        System.out.println("Container " + this.produtoID + " idade: " + this.idade);
    }

    public int getIdade() {
        return this.idade;
    }
}
