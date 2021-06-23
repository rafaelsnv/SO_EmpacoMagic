import java.io.*;

public class LeitorContainers {
    private final String caminhoArquivo;
    private SyncListContainer listaContainers;
 
    /**
     * Método construtor
     * @param caminho Caminho do arquivo.
     * @throws IOException Erro de leitura.
     */
    public LeitorContainers(String caminho) throws IOException {
       this.caminhoArquivo = caminho;
       this.lerArquivo();
    }
 
    /**
     * Lê arquivo, converte linhas em Containers e adiciona em lista
     * @throws IOException Erro de leitura.
     */
    public void lerArquivo() throws IOException {
       File arquivoContainers = new File(this.caminhoArquivo);
       BufferedReader br = new BufferedReader(new FileReader(arquivoContainers));
 
       String[] categorias;
       SyncListContainer lista = new SyncListContainer(1); // Inicializado com valor genérico 1. Vai ser substituido pelo tamanho real na linha 0 do txt.
       boolean primeira = true;
 
       for (String linha; (linha = br.readLine()) != null;) {
        
        if (primeira) {
            lista = new SyncListContainer(Integer.parseInt(linha));
            primeira = false;
        }
        else {        
            if (!linha.equals("")) {
                categorias = linha.split(";");
                    if (categorias.length == 2) {
                        Container container = new Container(Integer.parseInt(categorias[0]),Integer.parseInt(categorias[1]));
                        lista.add(container);
             }
          }
       }}
       this.listaContainers = lista;
       br.close();
    }
 
    /**
     * Retorna a lista de Containers lida
     * @return (ListaContainers) Lista de Containers do objeto.
     */
    public SyncListContainer getListaContainers() {
       return this.listaContainers;
    }
 }