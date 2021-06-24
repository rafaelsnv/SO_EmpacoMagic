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

    private boolean passarEpoca() {
        if (this.numeroUsos == 4) {
            this.numeroUsos = 0;
            this.envelhecerTodos();
            return true;
        }
        return false;
    }

    private Container clonar(int idContainer) throws CloneNotSupportedException {
        Container cont = this.listaGeral.get(idContainer - 1);
        Container clone = cont.clone();
        return clone;
    }


    public boolean exists(int idContainer) {
        for (Container container : this.listaUsando) {
            if (container.equals(idContainer))
                return true;
        }
        return false;
    }

    public synchronized Container getContainer(int idContainer) throws CloneNotSupportedException {
        boolean passouEpoca = this.passarEpoca();
        this.numeroUsos++;

        Container container = this.get(idContainer);

        // Falta de página
        if (container == null)
            if (this.listaUsando.size() < NUM_MAX_MOLDURAS) {
                Container novoCont = this.listaGeral.get(idContainer-1);
                novoCont.zerarIdade();
                novoCont.increaseIdade();
                this.listaUsando.add(novoCont);
                return novoCont;
            } else {
                Container novo = this.swapContainer(idContainer);
                if (novo == null)
                    System.out.println("opa");
                novo.zerarIdade();
                novo.increaseIdade();
                return novo;
            }
        else
            if(!container.foiUsado())
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

        Container sai = this.listaUsando.get(idSai);
        this.listaUsando.remove(sai);
        this.listaGeral.set(sai.getProdutoID() - 1, sai);

        Container entra = this.listaGeral.get(idEntra - 1);
        this.listaUsando.add(entra);

        return entra;
    }
}
