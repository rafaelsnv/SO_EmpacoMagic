import java.util.Scanner;

public class App {
   public static void main(String[] args) {

      Scanner ler = new Scanner(System.in);

      HeapPrazo pedidosPrazo = new HeapPrazo(); // Heap vazia para a inserção dos pedidos
      HeapQtd pedidosQtd = new HeapQtd(); // Heap vazia para inserção dos pedidos

      Pedido aux; // Auxiliar Pedido. Vai ser subscrito conforme cada linha for lida
      String[] divisao; // Vetor de strings com cada categoria do .txt
      String linha;
      int fim = 10;
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

      for (int i = pedidosPrazo.size(); i > 0; i--) {
         HeapQtd teste = pedidosPrazo.retorno();
         for (int j = teste.size(); j > 0; j--) {
            Pedido pedi = teste.retorno();
            System.out.println(pedi.getCliente() + " " + pedi.getTotalProdutos() + " " + pedi.getPrazo());
         }
      }
   }
}
