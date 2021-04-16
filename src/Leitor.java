import java.io.*;
import java.util.ArrayList;

public class Leitor {
   private File dadosEmpacota;

   public Leitor() {
   }
   /**
    * O método lê cada linha do .txt, transforma em pedido e retorna uma Heap de pedido
    * @param nomeArquivo
    * @param pedidosList
    * @throws IOException
    */
   public void lerArquivo(String nomeArquivo, ListaPedidos pedidosList) throws IOException {
      dadosEmpacota = new File(nomeArquivo); /* Colocar nos atributos o nome do arquivo de texto + '.txt'.
                                              Deve estar na pasta mãe.*/

      Pedido aux = null; // Auxiliar Pedido. Vai ser subscrito conforme cada linha for lida
      String[] divisao; // Vetor de strings com cada categoria do .txt

      BufferedReader br = new BufferedReader(new FileReader(dadosEmpacota));

      for (String linha; (linha = br.readLine()) != null;) { // Cada linha é lida, transformada em pedido e adicionada
                                                             // na Heap
         int i = 0;
         if (!linha.equals("")) { // Se a linha estiver vazia, é ignorada
            divisao = linha.split(";");
            if (divisao.length == 3) { // Se a linha não estiver no padrão de 3 categorias separadas por ";", ela é
                                       // ignorada
               String cliente = divisao[0];
               int qtProdutos = Integer.parseInt(divisao[1]);
               int prazo = Integer.parseInt(divisao[2]);
               aux = new Pedido(cliente, qtProdutos, prazo); // Inicializa o auxiliar de Pedidos com os itens da linha

               pedidosList.add(aux); // Adiciona o pedido na Heap de pedidos com prazo
               pedidosList.sort();
            }
         }
      }
      br.close();
   }
}