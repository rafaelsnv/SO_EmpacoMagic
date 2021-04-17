import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
   static String caminhoArquivo = "src/SO_20_DadosEmpacotadeira1.txt";


   public static void main(String[] args) throws IOException {
      Leitor leitor = new Leitor(caminhoArquivo);
      ListaPedidos listaPedidos = leitor.getListaPedidos();

      listaPedidos.sort();

      for(int i=0; i < listaPedidos.size(); i++) {
         Pedido pedido = listaPedidos.get(i);
         System.out.println(pedido.toString());
      }

   }
}