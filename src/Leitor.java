import java.io.*;

public class Leitor {
   private final String caminhoArquivo;
   private ListaPedidos listaPedidos;

   /**
    * Método construtor
    * @param caminho Caminho do arquivo.
    * @throws IOException Erro de leitura.
    */
   public Leitor (String caminho) throws IOException {
      this.caminhoArquivo = caminho;
      this.lerArquivo();
   }

   /**
    * Deposita lista de pedidos no objeto
    * @param listaPedidos (ListaPedidos) Lista de pedidos a ser adicionada.
    */
   private void setListaPedidos(ListaPedidos listaPedidos) {
      this.listaPedidos = listaPedidos;
   }

   /**
    * Retorna a lista de pedidos lida
    * @return (ListaPedidos) Lista de pedidos do objeto.
    */
   public ListaPedidos getListaPedidos() {
      return this.listaPedidos;
   }

   /**
    * Lê arquivo, converte linhas em pedidos e adiciona em lista
    * @throws IOException Erro de leitura.
    */
   public void lerArquivo() throws IOException {
      File arquivoPedidos = new File(this.caminhoArquivo);
      BufferedReader br = new BufferedReader(new FileReader(arquivoPedidos));

      String[] categorias;
      ListaPedidos lista = new ListaPedidos();
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
               pedido.setMinutoChegada( Integer.parseInt(categorias[3]) );
               pedido.setCodProduto( Integer.parseInt(categorias[4]));
               lista.add(pedido);
            }
         }
      }

      this.setListaPedidos(lista);
      br.close();
   }
}