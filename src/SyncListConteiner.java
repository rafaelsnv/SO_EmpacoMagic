import java.util.ArrayList;

public class SyncListConteiner {

    private ArrayList<Container> lista;

    /**
     * Adiciona um conteiner na lista
     * 
     * @return
     */
    public synchronized boolean addConteiner() {
        return false;
    }

    /**
     * Verifica se o conteiner em questão está zerado de produtos
     */
    public synchronized boolean isEmpty() {
        return false;
    }

    /**
     * Método para trocar o conteiner de menor idade com o requisitado
     * 
     * @param listaGeral - Recebe a lista geral de conteiners para fazer o swap
     * @param idEntra    - Recebe o id do Conteiner a ser retirado da listaGeral e
     *                   inserido na this.lista
     * 
     */
    public synchronized void swapConteiner(ArrayList<Container> listaGeral, int idEntra) {
        int menorIdade = 16;
        int idSai = 0;

        for (int i = 0; i < this.lista.size(); i++) {// Percorre a lista de conteiners próximos ao braço
            Container aux = lista.get(i);
            int idade = aux.getIdade();
            if (idade < menorIdade) { // Busca pelo Conteiner de menor idade da lista
                menorIdade = idade;
                idSai = i; // Guarda o id do conteiner de menor idade
            }
        }

        for (int i = 0; i < this.lista.size(); i++) { // Percorre a lista dos conteiners próximos aos braços
            Container conteinerSai = lista.get(i);

            if (conteinerSai.getProdutoID() == idSai) {
                for (int j = 0; j < listaGeral.size(); j++) {
                    Container conteinerEntra = listaGeral.get(j);

                    if (conteinerEntra.getProdutoID() == idEntra) {
                        listaGeral.set(j, conteinerSai);
                        this.lista.set(i, conteinerEntra);
                    }
                }
            }
        }
    }

    /**
     * 
     * @return Retorna o id do conteiner com a menor idade
     */
    public synchronized int younger() {
        return -1;
    }

    /**
     * Método para repor os produtos do conteiner
     * 
     * @param tipo (int) recebe o tipo do conteiner a ser reposto
     * @return
     */
    public synchronized boolean refresh(int tipo) {
        return false;
    }

}
