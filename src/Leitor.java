import java.io.*;

public class Leitor {
   private File dadosEmpacota;

   public Leitor() {
   }

   public void lerArquivo(String nomeArquivo, HeapPrazo pedidosPrazo, HeapQtd pedidosQtd) throws IOException { /* O método
                                                                                            lê cada linha do .txt,
                                                                                            transforma em pedido
                                                                                            e retorna uma Heap de pedidos*/

      dadosEmpacota = new File(nomeArquivo); // Colocar nos atributos o nome do arquivo de texto + '.txt'. Deve estar na
                                             // pasta mãe.

      Pedido aux = null; // Auxiliar Pedido. Vai ser subscrito conforme cada linha for lida
      String[] divisao; // Vetor de strings com cada categoria do .txt

      BufferedReader br = new BufferedReader(new FileReader(dadosEmpacota));

      for (String linha; (linha = br.readLine()) != null;) { // Cada linha é lida, transformada em pedido e adicionada
                                                             // na Heap
         if (!linha.equals("")) { // Se a linha estiver vazia, é ignorada
            divisao = linha.split(";");
            if (divisao.length == 3) { // Se a linha não estiver no padrão de 3 categorias separadas por ";", ela é
                                       // ignorada
               aux = new Pedido(divisao[0], Integer.parseInt(divisao[1]), Integer.parseInt(divisao[2])); /* Inicializa o
                                                                                                            auxiliar de
                                                                                                            Pedidos com
                                                                                                            os itens da
                                                                                                            linha
                                                                                                            */
               if (aux.getPrazo() == 0) // Testa se há prazo para o pedido
                  pedidosQtd.insere(aux); // Adiciona o pedido na Heap de pedidos sem prazo
               else
                  pedidosPrazo.insereNovo(aux); // Adiciona o pedido na Heap de pedidos com prazo
            }
         }
      }
   }
}