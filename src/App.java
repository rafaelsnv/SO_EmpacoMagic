import java.io.IOException;
import java.util.Scanner;

public class App {
   public static void main(String[] args) throws IOException {

      Scanner ler = new Scanner(System.in);

      HeapPrazo pedidosPrazo = new HeapPrazo(); // Heap vazia para a inserção dos pedidos
      HeapQtd pedidosQtd = new HeapQtd(); // Heap vazia para inserção dos pedidos

      Leitor leitor = new Leitor();
      leitor.lerArquivo("src/SO_20_DadosEmpacotadeira1.txt", pedidosPrazo, pedidosQtd);

//      Esteira esteira = new Esteira();
//
//
//      System.out.println("Tempo de Empacotamento do mais prioritário: " + esteira.empacotarPedido(pedidosPrazo, pedidosQtd) + " segundos.");
//      System.out.println("Quantidades de Pacotes: " + esteira.getQntPacotesTotal(pedidosPrazo, pedidosQtd));
//      System.out.println("-----");
//      System.out.println("-----");
//      System.out.println("-----");
//      System.out.println("-----");
//      System.out.println("-----");
//      System.out.println("-----");

      System.out.println("Impressão da Heap dos pedidos prioritários\n");
      for (int i = pedidosPrazo.size()-1; i >= 0; i--) { // Loop para teste do carregamento e
         HeapQtd teste = pedidosPrazo.retorno(); // impressão dos pedidos com prioridade
         int noPrazo = teste.getPrazo();
         System.out.println("Prazo do nó: " + noPrazo+"");
         for (int j = teste.size(); j > 0; j--) {
            Pedido pedi = teste.retorno();
            System.out.println(" Cliente: "+pedi.getCliente() + " | N produtos: " + pedi.getTotalProdutos() + " | Prazo: " + pedi.getPrazo());
         }
         System.out.println();
      }

      System.out.println("___________________________________________________________________________________________\n" +
              "Impressão da Heap dos pedidos sem prioridade\n");
      for (int i = pedidosQtd.size(); i > 0; i--) { // Loop para teste do carregamento e
         Pedido testeQtd = pedidosQtd.retorno(); // impressão dos pedidos sem prioridade
         System.out.println("Posição na heap: "+i+" | Cliente: "+testeQtd.getCliente() + " | N produtos: " + testeQtd.getTotalProdutos() + " | Prazo: " + testeQtd.getPrazo());
      }
      ler.close();
   }
}