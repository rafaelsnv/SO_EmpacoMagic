public class Container {
    private static final int MAX_VOLUME_CONTEINER = 1000000; // valores em cm³
    private static final int trocaConteiner = 30; // valores em segundos
    private int idade = 0;
    private int qtdMaxProdutos = 0;
    private int qtdAtualProdutos = 0;
    private int ID;

    /**
     * Construtor Vazio ID = 0 listaPedidosUnicos = vazia
     */
    public Container() {
        this.ID = 0;
    }

    /**
     * Construtor com parâmetros
     * 
     * @param ID     ID de identificação do conteiner. Ideia inicial do ID ser =
     *               código do produto
     * @param volume (int) - Volume do produto
     */
    public Container(int ID, int volume) {
        this.ID = ID;
        qtdMaxProdutos(volume);
    }

    /**
     * Método serve para verificar se o conteiner está vazio
     * 
     * @return False != 0
     */
    public boolean isEmpty() {
        if (this.qtdAtualProdutos == 0)
            return true;
        else
            return false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getTrocaConteiner() {
        return trocaConteiner;
    }

    public static int getMaxVolumeConteiner() {
        return MAX_VOLUME_CONTEINER;
    }

    public int getQtdMaxProdutos() {
        return this.qtdMaxProdutos;
    }

    public int getQtdAtualProdutos() {
        return this.qtdAtualProdutos;
    }

    /**
     * @param qtd (int) - Quantidade de produtos a ser inserida
     * 
     *            Tive a seguinte ideia: A gente pode usar só esse método para
     *            modificar a qtdAtual. Passando pra ele um número positivo em caso
     *            de refresh, ou um número negativo em caso de retirada de produtos.
     * 
     *            By: João
     * 
     */
    public void setQtdAtualProdutos(int qtd) {
        this.qtdAtualProdutos += qtd;
    }

    /**
     * Método para inserir a quantidade máxima de produtos no conteiner
     */
    public boolean refreshConteiner() {
        setQtdAtualProdutos(this.qtdMaxProdutos);
        if (this.qtdAtualProdutos > this.qtdMaxProdutos) { // Somente um teste para ficarmos cientes em caso de falhas
            System.out.println("Refresh com erros, favor verificar " + " QtdAtual: " + this.qtdAtualProdutos);
            return false;
        }
        return true;
    }

    /**
     * Método para calcular a quantidade máxima de produtos suportada pelo conteiner
     * 
     * @param volume (int) - Volume do produto
     */
    public void qtdMaxProdutos(int volume) {
        this.qtdMaxProdutos = MAX_VOLUME_CONTEINER / volume;
    }

    /**
     * Método para incrementar a idade do conteiner sempre que ele for utilizado
     */
    public void increaseIdade() {
        this.idade += 8;

        if (this.idade > 15)
            System.out.println("Idade inválida!"); // Somente um teste para sermos informados em caso de falha

        System.out.println("Conteiner " + this.ID + " idade: " + this.idade);

    }

    /**
     * Método para decrementar a idade do conteiner sempre que um ciclo terminar
     */
    public void decreaseIdade() {
        this.idade /= 2;

        System.out.println("Conteiner " + this.ID + " idade: " + this.idade);
    }

    public int getIdade() {
        return this.idade;
    }
}
