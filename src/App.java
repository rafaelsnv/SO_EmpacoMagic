import java.io.IOException;

public class App {
   static String caminhoArquivo = "src/SO_20_DadosEmpacotadeira1.txt";

   public static void main(String[] args) throws IOException {
      Leitor leitor = new Leitor(caminhoArquivo);

      // Esteira comum
      Esteira esteira = new Esteira();

      esteira.setListaPedidos( leitor.getListaPedidos() );
      esteira.empacotar();
      esteira.buildRelatorioPedidos();
      esteira.buildRelatorioEstatistico();

      System.out.println("\nESTEIRA COMUM\n=============\n");
      System.out.println( esteira.getRelatorioPedidos() );
      System.out.println( esteira.getRelatorioEstatistico() );
   }
}