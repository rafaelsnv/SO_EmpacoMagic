import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncListContainer {
    private static final int NUM_MAX_MOLDURAS = 4;
    private final ArrayList<Container> listaGeral;
    private final List<Container> listaUsando;
    private int numeroUsos;

    /**
     * Construtor com parâmetros
     * @param qtdProdutos a quantidade total de tipos de produtos
     */
    public SyncListContainer(int qtdProdutos ) {
        this.numeroUsos = 0;
        this.listaGeral =  new ArrayList<>(qtdProdutos);

        List<Container> lista = new ArrayList<>(NUM_MAX_MOLDURAS);
        this.listaUsando = Collections.synchronizedList(lista);
    }

    /**
     * Adiciona um container na lista
     */
    public synchronized void add(Container novo) {
        this.listaGeral.add(novo);
    }

    private Container get(int id) {
        if (this.listaUsando.size() == 0)
            return null;
        else
            for (Container container : this.listaUsando) {
                if (container.equals(id))
                    return container;
            }

        return null;
    }

    private void passarEpoca() {
        if (this.numeroUsos == 4) {
            this.numeroUsos = 0;
            this.envelhecerTodos();
        }
    }

    private Container clonar(int idContainer) throws CloneNotSupportedException {
        Container cont = this.listaGeral.get(idContainer - 1);
        Container clone = cont.clone();
        return clone;
    }

    public synchronized Container getContainer(int idContainer) throws CloneNotSupportedException {
        this.numeroUsos++;
        this.passarEpoca();

        Container container = this.get(idContainer);

        // Falta de página
        if (container == null)
            if (this.listaUsando.size() < NUM_MAX_MOLDURAS) {
                Container clone = this.clonar(idContainer);
                this.listaUsando.add(clone);
                return clone;
            } else {
                Container novo = this.swapContainer(idContainer);
                return novo;
            }
        else
            container.increaseIdade();

        return container;
    }

    private void envelhecerTodos (){
        for (Container container:listaUsando)
            container.decreaseIdade();
    }

//    /**
//     * Verifica se o container em questão está zerado de produtos
//     * @return true se .size() == 0; false se .size() != 0;
//     */
//    public boolean isEmpty() {
//        return this.listaUsando.size() == 0;
//    }

    /**
     * Método para trocar o conteiner de menor idade com o requisitado
     *
     * @param listaGeral - Recebe a lista geral de conteiners para fazer o swap
     * @param idEntra    - Recebe o id do Conteiner a ser retirado da listaGeral e
     *                   inserido na this.lista
     *
     */
    public synchronized Container swapContainer(int idEntra) throws CloneNotSupportedException {
        int menorIdade = 16;
        int idSai = 0;

        for (int i = 0; i < this.listaUsando.size(); i++) {// Percorre a lista de conteiners próximos ao braço
            Container aux = this.listaUsando.get(i);
            int idade = aux.getIdade();
            if (idade < menorIdade) { // Busca pelo Conteiner de menor idade da lista
                menorIdade = idade;
                idSai = i; // Guarda o id do conteiner de menor idade
            }
        }

        for (int i = 0; i < this.listaUsando.size(); i++) {
            Container contSai = this.listaUsando.get(i);
            if(contSai.equals(idSai)) {
                this.listaUsando.remove(contSai);
                Container entra = this.clonar(idEntra);
                this.listaUsando.add(entra);
                return contSai;
            }
        }

        return null;
    }
}
