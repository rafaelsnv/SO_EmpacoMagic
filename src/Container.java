public class Container implements Comparable<Container>, Cloneable {
    private static final int MAX_VOLUME_CONTAINER = 1000000; // valores em cm³
    private static final int TEMPO_TROCA_CONTAINER = 30; // valores em segundos
    private final int produtoID;
    private final int qtdMaxProdutos;
    private int qtdAtualProdutos;
    private int idade;

    /**
     * Construtor com parâmetros para cálculo de quantidade de produtos.
     * @param produtoID Tipo de produto contido no container.
     * @param produtoVol (int) - Volume do produto
     */
    public Container(int produtoID, int produtoVol) {
        this.produtoID = produtoID;
        this.idade = 0;
        this.qtdMaxProdutos = this.qtdAtualProdutos = MAX_VOLUME_CONTAINER / produtoVol;
    }

    /**
     * Método serve para verificar se o container está vazio
     * @return Verdadeiro se estiver vazio.
     */
    public boolean isEmpty() {
        return this.qtdAtualProdutos == 0;
    }

    public int getProdutoID() {
        return this.produtoID;
    }

    public int getQtdAtualProdutos() {
        return this.qtdAtualProdutos;
    }

    public void consumirProdutos(int quantos) {
        this.qtdAtualProdutos -= quantos;
    }

    /**
     * Método para reabastecer container.
     */
    public void reabastecer() {
        this.qtdAtualProdutos = this.qtdMaxProdutos;
    }

    public int getTempoTroca() {
        return TEMPO_TROCA_CONTAINER;
    }

    /**
     * Método para incrementar a idade do container sempre que ele for utilizado
     */
    public void increaseIdade() {
        this.idade += 8;
        if (this.idade > 15)
            System.out.println("Idade inválida!"); // Somente um teste para sermos informados em caso de falha
        System.out.println("Container " + this.produtoID + " idade: " + this.idade);
    }

    /**
     * Método para decrementar a idade do container sempre que um ciclo terminar
     */
    public void decreaseIdade() {
        this.idade /= 2;
//        System.out.println("Container " + this.produtoID + " idade: " + this.idade);
    }

    /**
     * Método get para idade atual do container
     * @return (int) Idade do container.
     */
    public int getIdade() {
        return this.idade;
    }

    /**
     * Método para comparação de idade entre containers
     * @param outro Outro Container.
     * @return Diferença de idade entre containers.
     */
    @Override
    public int compareTo(Container outro) {
        return this.idade - outro.idade;
    }

    public boolean equals(int id) {
        return this.produtoID == id;
    }

    @Override
    public String toString() {
        return "Container " + this.produtoID;
    }

    @Override
    public Container clone() throws CloneNotSupportedException {
//        Container novo = new Container(this.produtoID, this.produtoVol);
//        return novo;
        return (Container) super.clone();
    }
}
