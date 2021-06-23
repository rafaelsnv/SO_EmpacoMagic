import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class App {
   static String caminhoArquivo = "src/SO_20_DadosEmpacotadeira_3.txt";

   public static void main(String[] args) throws IOException {
      LeitorPedidos ler = new LeitorPedidos(caminhoArquivo);

      SyncFila filaPedidos = ler.getFilaPedidos();
      SyncRelatorio relatorio = new SyncRelatorio();

      System.out.println(relatorio);


      ArrayList<Container> lista = new ArrayList<>();
      Container container_1 = new Container(1);
      container_1.increaseIdade();
      container_1.decreaseIdade();
      lista.add(container_1);

      Container container_2 = new Container(2);
      container_2.increaseIdade();
      container_2.increaseIdade();
      container_2.decreaseIdade();
      lista.add(container_2);

      Container oldest = Collections.min(lista);
      System.out.println(oldest);

//      Esteira esteira_1 = new Esteira(1, filaPedidos, relatorio);
//      Esteira esteira_2 = new Esteira(2, filaPedidos, relatorio);
//
//      try {
//         esteira_1.start();
//         esteira_2.start();
//
//         esteira_1.join();
//         esteira_2.join();
//      } catch (InterruptedException ignored) {}
//
//      System.out.println("\nEmpacotamento concluído com sucesso! \n");
//
//      System.out.println(relatorio + "\n\n");
//      System.out.println(relatorio.relatorioEstatistico());
   }
}