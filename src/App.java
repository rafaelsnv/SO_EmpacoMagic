import java.util.Scanner;

public class App {
   public static void main(String[] args) {

      Scanner ler = new Scanner(System.in);

      HeapPrazo pedidosPrazo = new HeapPrazo(); // Heap vazia para a inserção dos pedidos
      HeapQtd pedidosQtd = new HeapQtd(0); // Heap vazia para inserção dos pedidos

      Pedido aux; // Auxiliar Pedido. Vai ser subscrito conforme cada linha for lida
      String[] divisao; // Vetor de strings com cada categoria do .txt
      String linha;
      int fim = 4;
      while (fim != 0) {
         System.out.println("insira uma linha");
         linha = ler.nextLine();
         divisao = linha.split(";");
         if (divisao.length == 3) { // Se a linha não estiver no padrão de 3 categorias separadas por ";", ela é
                                    // ignorada
            aux = new Pedido(divisao[0], Integer.parseInt(divisao[1]), Integer.parseInt(divisao[2]));
            if (Integer.parseInt(divisao[2]) == 0) // Testa se há prazo para o pedido
               pedidosQtd.insere(aux); // Adiciona o pedido na Heap de pedidos sem prazo
            else
               pedidosPrazo.insereNovo(aux); // Adiciona o pedido na Heap de pedidos com prazo
         }
         fim--;
      }

      System.out.println("Prazo size final: " + pedidosPrazo.size());
      System.out.println("Qtd size final: " + pedidosQtd.size());
   }
}
