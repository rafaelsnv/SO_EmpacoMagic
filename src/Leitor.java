import java.io.*;

public class Leitor {
   private final String caminhoArquivo;
   private ListaPedidos listaPedidos;

   public Leitor(String caminhoArquivo) throws IOException {
      this.caminhoArquivo = caminhoArquivo;
      this.lerArquivo();
   }

   /**
    * O método lê cada linha do .txt, transforma em pedido e retorna uma Heap de pedido
    * @param caminho
    * @param listaPedidos
    * @throws IOException
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
            if (categorias.length == 3) {
               i++;
               String cliente = categorias[0];
               int qtdProdutos = Integer.parseInt(categorias[1]);
               int prazo = Integer.parseInt(categorias[2]);

               lista.add( new Pedido(i, cliente, qtdProdutos, prazo) );
            }
         }
      }

      this.setListaPedidos(lista);
      br.close();
   }

   public void setListaPedidos(ListaPedidos listaPedidos) {
      this.listaPedidos = listaPedidos;
   }

   public ListaPedidos getListaPedidos() {
      return this.listaPedidos;
   }

}