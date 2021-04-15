import java.io.IOException;
import java.util.Scanner;

public class App {
   public static void main(String[] args) throws IOException {

      Scanner ler = new Scanner(System.in);

      HeapPrazo pedidosPrazo = new HeapPrazo(); // Heap vazia para a inserção dos pedidos
      HeapQtd pedidosQtd = new HeapQtd(); // Heap vazia para inserção dos pedidos

      Leitor leitor = new Leitor();
      leitor.lerArquivo("src/SO_20_DadosEmpacotadeira1.txt", pedidosPrazo, pedidosQtd);

      Esteira esteira = new Esteira();


      System.out.println("Tempo de Empacotamento do mais prioritário: " + esteira.empacotarPedido(pedidosPrazo, pedidosQtd) + " segundos.");
      System.out.println("Quantidades de Pacotes: " + esteira.getQntPacotesTotal(pedidosPrazo, pedidosQtd));
      System.out.println("-----");
      System.out.println("-----");
      System.out.println("-----");
      System.out.println("-----");
      System.out.println("-----");
      System.out.println("-----");

      System.out.println("Impressão da Heap dos pedidos prioritários\n");
      for (int i = pedidosPrazo.size(); i > 0; i--) { // Loop para teste do carregamento e
         HeapQtd teste = pedidosPrazo.retorno(); // impressão dos pedidos com prioridade
         for (int j = teste.size(); j > 0; j--) {
            Pedido pedi = teste.retorno();
            System.out.println(pedi.getCliente() + " " + pedi.getTotalProdutos() + " " + pedi.getPrazo());
         }
      }

      System.out.println("\nImpressão da Heap dos pedidos sem prioridade\n");
      for (int i = pedidosQtd.size(); i > 0; i--) { // Loop para teste do carregamento e
         Pedido testeQtd = pedidosQtd.retorno(); // impressão dos pedidos sem prioridade
         System.out.println(testeQtd.getCliente() + " " + testeQtd.getTotalProdutos() + " " + testeQtd.getPrazo());
      }
      ler.close();
   }
}