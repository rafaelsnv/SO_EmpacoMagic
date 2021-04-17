import java.io.IOException;

public class App {
   static String caminhoArquivo = "src/SO_20_DadosEmpacotadeira1.txt";

   public static void main(String[] args) throws IOException {
      Leitor leitor = new Leitor(caminhoArquivo);
      Esteira esteira = new Esteira();

      esteira.setListaPedidos( leitor.getListaPedidos() );
      esteira.empacotarPedidos();

      esteira.buildRelatorioPedidos();
      esteira.buildRelatorioEstatistico();

      String relatorioPedidos = esteira.getRelatorioPedidos();
      String relatorioEstatistico = esteira.getRelatorioEstatistico();

      System.out.println(relatorioPedidos);
      System.out.println(relatorioEstatistico);

   }
}