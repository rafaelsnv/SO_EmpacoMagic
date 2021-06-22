import java.io.IOException;
public class App {
   static String caminhoArquivo = "src/SO_20_DadosEmpacotadeira_3.txt";

   public static void main(String[] args) throws IOException {
      Leitor ler = new Leitor(caminhoArquivo);

      ListaPedidos listaPedidosTodos = ler.getListaPedidos();
      SyncList filaEmpacotar = new SyncList();
      SyncRelatorio relatorio = new SyncRelatorio();

      SyncRelogio relogio = new SyncRelogio();

      Escalonador escalonador = new Escalonador(listaPedidosTodos, filaEmpacotar, relogio);

      Esteira esteira_1 = new Esteira(1, filaEmpacotar, relatorio, listaPedidosTodos, relogio);
      Esteira esteira_2 = new Esteira(2, filaEmpacotar, relatorio, listaPedidosTodos, relogio);

      try {
         escalonador.start();
         esteira_1.start();
         esteira_2.start();

         escalonador.join();
         esteira_1.join();
         esteira_2.join();
      } catch (InterruptedException ignored) {}

      System.out.println("\nEmpacotamento concluído com sucesso! \n");

      System.out.println(relatorio + "\n\n");
      System.out.println(relatorio.relatorioEstatistico());
   }
}