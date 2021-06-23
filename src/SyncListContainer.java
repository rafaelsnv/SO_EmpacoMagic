import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncListContainer {

    private ArrayList<Container>  listaGeral;
    private List<Container> listaUsando;

    /**
     * Construtor com parâmetros
     * @param qtdProdutos a quantidade total de tipos de produtos
     */
    public SyncListContainer(int qtdProdutos ) {
        this.listaGeral =  new ArrayList<Container>(qtdProdutos);
        
        List<Container> lista = new ArrayList<>();
        this.listaUsando = Collections.synchronizedList(lista);
    }

    /**
     * Adiciona um container na lista
     */
    public void add(Container novo) {
        this.listaGeral.add(novo);
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
     * @param listaGeral - Recebe a lista geral de containers para fazer o swap
     * @param idEntra    - Recebe o id do container a ser retirado da listaGeral e
     *                   inserido na this.lista
     *
     */
    public void swapContainer(ArrayList<Container> listaGeral, int idEntra) {
        int menorIdade = 16;
        int idSai = 0;

        for (int i = 0; i < this.listaUsando.size(); i++) {// Percorre a lista de containers próximos ao braço
            Container aux = listaUsando.get(i);
            int idade = aux.getIdade();
            if (idade < menorIdade) { // Busca pelo containers de menor idade da lista
                menorIdade = idade;
                idSai = i; // Guarda o id do containers de menor idade
            }
        }

        for (int i = 0; i < this.listaUsando.size(); i++) { // Percorre a lista dos containers próximos aos braços
            Container containerSai = listaUsando.get(i);

            if (containerSai.getProdutoID() == idSai) {
                for (int j = 0; j < listaGeral.size(); j++) {
                    Container containerEntra = listaGeral.get(j);

                    if (containerEntra.getProdutoID() == idEntra) {
                        listaGeral.set(j, containerSai);
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
