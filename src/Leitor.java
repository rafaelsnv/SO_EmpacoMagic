import java.io.*;

public class Leitor {
   private String caminhoArquivo;
   private File arquivoPedidos;
   private ListaPedidos listaPedidos;

   public Leitor(String caminho, ListaPedidos listaPedidos) throws IOException {
      this.caminhoArquivo = caminho;
      this.listaPedidos = listaPedidos;
      this.lerArquivo();
   }

   /**
    * O método lê cada linha do .txt, transforma em pedido e retorna uma Heap de pedido
    * @param caminho
    * @param listaPedidos
    * @throws IOException
    */
   public void lerArquivo() throws IOException {
      this.arquivoPedidos = new File(this.caminhoArquivo);
      BufferedReader br = new BufferedReader(new FileReader(this.arquivoPedidos));

      String[] categorias;
      int i = 0;

      for (String linha; (linha = br.readLine()) != null;) {
         if (!linha.equals("")) {
            categorias = linha.split(";");
            if (categorias.length == 3) {
               i++;
               String cliente = categorias[0];
               int qtdProdutos = Integer.parseInt(categorias[1]);
               int prazo = Integer.parseInt(categorias[2]);

               this.listaPedidos.add( new Pedido(i, cliente, qtdProdutos, prazo) );
            }
         }
      }
      br.close();
   }
}