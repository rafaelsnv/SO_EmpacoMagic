import java.io.IOException;

public class App {
   static String caminhoArquivo = "src/SO_20_DadosEmpacotadeira_3.txt";

   public static void main(String[] args) throws IOException {
      LeitorPedidos ler = new LeitorPedidos(caminhoArquivo);

      SyncFila filaPedidos = ler.getFilaPedidos();
      SyncRelatorio relatorio = new SyncRelatorio();
      SyncListContainer containeres = new SyncListContainer();

      System.out.println(relatorio);

      Esteira esteira_1 = new Esteira(1, filaPedidos, relatorio, containeres);
      Esteira esteira_2 = new Esteira(2, filaPedidos, relatorio, containeres);

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