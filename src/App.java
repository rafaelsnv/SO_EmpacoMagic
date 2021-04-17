import java.io.IOException;
import java.util.Scanner;

public class App {
   static final ListaPedidos pedidosList = new ListaPedidos(); // Heap vazia para inserção dos pedidos não prioritários
   static final Horario inicioExpediente = new Horario(28_800);
   static final Horario meioExpediente = new Horario(43_200);
   static final Horario fimExpediente = new Horario(61_200);
   static final Scanner ler = new Scanner(System.in);
   static final Leitor leitor = new Leitor();

   public static void main(String[] args) throws IOException {
      leitor.lerArquivo("src/SO_20_DadosEmpacotadeira1.txt", pedidosList); /* Carregamento dos pedidos na estrutura */
      pedidosList.sort(); // Ordenação dos pedidos

      Esteira esteira = new Esteira();
      esteira.empacotarPedidos(pedidosList, inicioExpediente);

      ler.close();
   }
}