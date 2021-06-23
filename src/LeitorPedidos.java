import java.io.*;

public class LeitorPedidos {
   private final String caminhoArquivo;
   private SyncFila filaPedidos;

   /**
    * Método construtor
    * @param caminho Caminho do arquivo.
    * @throws IOException Erro de leitura.
    */
   public LeitorPedidos(String caminho) throws IOException {
      this.caminhoArquivo = caminho;
      this.lerArquivo();
   }

   /**
    * Lê arquivo, converte linhas em pedidos e adiciona em lista
    * @throws IOException Erro de leitura.
    */
   public void lerArquivo() throws IOException {
      File arquivoPedidos = new File(this.caminhoArquivo);
      BufferedReader br = new BufferedReader(new FileReader(arquivoPedidos));

      String[] categorias;
      SyncFila fila = new SyncFila();
      int i = 0;

      for (String linha; (linha = br.readLine()) != null;) {
         if (!linha.equals("")) {
            categorias = linha.split(";");
            if (categorias.length == 5) {
               Pedido pedido = new Pedido();
               pedido.setID(++i);
               pedido.setCliente(categorias[0]);
               pedido.setQtdProdutos( Integer.parseInt(categorias[1]) );
               pedido.setPrazo( Integer.parseInt(categorias[2]) );
               pedido.setHoraChegada( Integer.parseInt(categorias[3]) );
               pedido.setCodProduto( Integer.parseInt(categorias[4]));
               fila.push(pedido);
            }
         }
      }

      fila.sort();
      this.filaPedidos = fila;
      br.close();
   }

   /**
    * Retorna a lista de pedidos lida
    * @return (ListaPedidos) Lista de pedidos do objeto.
    */
   public SyncFila getFilaPedidos() {
      return this.filaPedidos;
   }
}