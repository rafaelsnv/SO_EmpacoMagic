import java.io.IOException;

public class App {
   static String caminhoArquivo = "src/SO_20_DadosEmpacotadeira_2.txt";

   public static void main(String[] args) throws IOException {
      Leitor ler = new Leitor(caminhoArquivo);

      ListaPedidos listaPedidosTodos = ler.getListaPedidos();
      SyncList filaEmpacotar = new SyncList();
      SyncList relatorio = new SyncList();

      Escalonador escalonador = new Escalonador(listaPedidosTodos, filaEmpacotar);

      int numTotalPacotes = listaPedidosTodos.size();
      Esteira esteira_1 = new Esteira(1, numTotalPacotes/2, filaEmpacotar, relatorio, );


   }
}