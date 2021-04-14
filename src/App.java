import java.io.IOException;
import java.util.Scanner;

public class App {
   public static void main(String[] args) throws IOException {

      Scanner ler = new Scanner(System.in);

      HeapPrazo pedidosPrazo = new HeapPrazo(); // Heap vazia para a inserção dos pedidos
      HeapQtd pedidosQtd = new HeapQtd(); // Heap vazia para inserção dos pedidos

      Leitor leitor = new Leitor();
      leitor.lerArquivo("SO_20_DadosEmpacotadeira1.txt", pedidosPrazo, pedidosQtd);

      for (int i = pedidosPrazo.size(); i > 0; i--) {
         HeapQtd teste = pedidosPrazo.retorno();
         for (int j = teste.size(); j > 0; j--) {
            Pedido pedi = teste.retorno();
            System.out.println(pedi.getCliente() + " " + pedi.getTotalProdutos() + " " + pedi.getPrazo());
         }
      }
      ler.close();

   }
}
