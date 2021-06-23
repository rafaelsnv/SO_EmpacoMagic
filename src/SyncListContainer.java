import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncListContainer {

    private ArrayList<Container>  listaGeral;
    private List<Container> listaUsando;
    private int numeroUsos;

    /**
     * Construtor com parâmetros
     * @param qtdProdutos a quantidade total de tipos de produtos
     */
    public SyncListContainer(int qtdProdutos ) {
        this.numeroUsos = 0;
        this.listaGeral =  new ArrayList<Container>(qtdProdutos);

        List<Container> lista = new ArrayList<>(4);
        this.listaUsando = Collections.synchronizedList(lista);
    }

    /**
     * Adiciona um container na lista
     */
    public void add(Container novo) {
        this.listaGeral.add(novo);
    }

    public Container getContainer (int idContainer){
        if(this.numeroUsos == 4){
            this.numeroUsos=0;
            this.envelhecerTodos();
        }

        Container container = this.listaUsando.get(idContainer);

        if (container == null){
            swapContainer(idContainer);

        }

        return this.listaUsando.get(idContainer);
    }

    public void envelhecerTodos (){
        for (Container container:listaUsando) {
            container.decreaseIdade();
        }
    }

//    /**
//     * Verifica se o container em questão está zerado de produtos
//     * @return true se .size() == 0; false se .size() != 0;
//     */
//    public boolean isEmpty() {
//        return this.listaUsando.size() == 0;
//    }

    /**
     * Método para trocar o container de menor idade com o requisitado
     * 
     * @param idEntra    - Recebe o id do container a ser retirado da listaGeral e
     *                   inserido na this.lista
     *
     */
    public void swapContainer(int idEntra) {
        int menorIdade = 16;
        int idSai = 0;

        for (int i = 0; i < this.listaUsando.size(); i++) {// Percorre a lista de containers próximos ao braço
            Container aux = listaUsando.get(i);
            int idade = aux.getIdade();
            if (idade < menorIdade) { // Busca pelo container de menor idade da lista
                menorIdade = idade;
                idSai = i; // Guarda o id do container de menor idade
            }
        }

        for (int i = 0; i < this.listaUsando.size(); i++) { // Percorre a lista dos containers próximos aos braços
            Container containerSai = listaUsando.get(i);

            if (containerSai.getProdutoID() == idSai) {
                for (int j = 0; j < this.listaGeral.size(); j++) {
                    Container containerEntra = this.listaGeral.get(j);

                    if (containerEntra.getProdutoID() == idEntra) {
                        this.listaGeral.set(j, containerSai);
                        this.listaUsando.set(i, containerEntra);
                    }
                }
            }
        }
    }

    /**
     * 
     * @return Retorna o id do container com a menor idade
     */
    public synchronized int younger() {
        return -1;
    }

    /**
     * Método para repor os produtos do container
     * 
     * @param tipo (int) recebe o tipo do container a ser reposto
     * @return
     */
    public synchronized boolean refresh(int tipo) {
        return false;
    }

}
