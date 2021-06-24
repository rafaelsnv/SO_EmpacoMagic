import java.io.IOException;
import java.util.concurrent.Semaphore;

public class App {
   static String caminhoPedidos = "src/SO_20_DadosEmpacotadeira_3.txt";
   static String caminhoContainers = "src/SO_20_DadosProdutos.txt";

   public static void main(String[] args) throws IOException {
      LeitorPedidos readPedidos = new LeitorPedidos(caminhoPedidos);
      LeitorContainers readContainers = new LeitorContainers(caminhoContainers);

      SyncRelatorio relatorio = new SyncRelatorio();
      SyncListContainer containeres = readContainers.getListaContainers();
      Semaphore mutex = new Semaphore(1);

      SyncFila filaPedidos = readPedidos.getFilaPedidos();
      int numOp = filaPedidos.size();

      Esteira esteira_1 = new Esteira(1, filaPedidos, relatorio, containeres, mutex, numOp/2);
      Esteira esteira_2 = new Esteira(2, filaPedidos, relatorio, containeres, mutex, numOp - numOp/2);

      try {
         esteira_1.start();
         esteira_2.start();

         esteira_1.join();
         esteira_2.join();
      } catch (InterruptedException ignored) {}

      System.out.println("\nEmpacotamento concluído com sucesso! \n");

      System.out.println(relatorio + "\n\n");
      System.out.println(relatorio.relatorioEstatistico());
   }
}