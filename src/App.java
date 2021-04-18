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


      // Esteira SJF
      EsteiraSJF esteiraSJF = new EsteiraSJF();

      esteiraSJF.setListaPedidos(leitor.getListaPedidos());
      esteiraSJF.empacotar();

      esteiraSJF.buildRelatorioPedidos();
      esteiraSJF.buildRelatorioEstatistico();

      System.out.println("\n\nESTEIRA SJF\n===========\n");
      System.out.println( esteiraSJF.getRelatorioPedidos() );
      System.out.println( esteiraSJF.getRelatorioEstatistico() );
   }
}