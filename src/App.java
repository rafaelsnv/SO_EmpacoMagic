import java.io.IOException;
import java.util.Scanner;

public class App {
   public static void main(String[] args) throws IOException {

      final HeapPrazo pedidosPrazo = new HeapPrazo(); // Heap vazia para a inserção dos pedidos prioritários
      final HeapQtd pedidosQtd = new HeapQtd(); // Heap vazia para inserção dos pedidos não prioritários
      final Horario inicioExpediente = new Horario(28_800);
      final Horario meioExpediente = new Horario(43_200);
      final Horario fimExpediente = new Horario(61_200);

      Scanner ler = new Scanner(System.in);
      Leitor leitor = new Leitor();

      leitor.lerArquivo("src/SO_20_DadosEmpacotadeira1.txt", pedidosPrazo, pedidosQtd); /* Carregamento dos
                                                                                                  pedidos na estrutura*/
      Esteira esteira = new Esteira();
      esteira.empacotarPedidos(pedidosPrazo, pedidosQtd, inicioExpediente);

      /* System.out.println("Impressão da Heap dos pedidos prioritários\n");
      for (int i = pedidosPrazo.size()-1; i >= 0; i--) { // Loop para teste do carregamento e
         HeapQtd teste = pedidosPrazo.retorno(); // impressão dos pedidos com prioridade
         int noPrazo = teste.getPrazo();
         System.out.println("Prazo do nó: " + noPrazo+"");
         for (int j = teste.size(); j > 0; j--) {
            Pedido pedi = teste.retorno();
            System.out.println(" Cliente: "+pedi.getCliente() + "    | N° produtos: " + pedi.getTotalProdutos() + "     | Prazo: " + pedi.getPrazo());
         }
         System.out.println();
      }

      System.out.println("___________________________________________________________________________________________\n" +
              "Impressão da Heap dos pedidos sem prioridade\n");
      for (int i = pedidosQtd.size(); i > 0; i--) { // Loop para teste do carregamento e
         Pedido testeQtd = pedidosQtd.retorno(); // impressão dos pedidos sem prioridade
         System.out.println("Posição na heap: "+i+" | Cliente: "+testeQtd.getCliente() + " | N produtos: " + testeQtd.getTotalProdutos() + " | Prazo: " + testeQtd.getPrazo());
      } */
      ler.close();
   }
}