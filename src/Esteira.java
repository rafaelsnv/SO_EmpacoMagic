//import java.util.ArrayList;
//import java.util.List;
//
//public class Esteira {
//   private static int CAPACIDADE = 5000;
//   private static int PACOTE = 250;
//   private static double TEMPO_ROLAMENTO = 0.5;
//   private static double TEMPO_EMPACOTAMENTO = 5.0;
//   private int qntPacotes = 0;
//   private int volumeTotal = 0;
//   private int totalProdutos = 0;
//   private double tempoEmpacotando;
//   private double tempoPrevisao;
//
//   public Esteira() {
//   }
//
//   /**
//    * O método deve retornar o tempo médio gasto para executar o lote de produtos.
//    * @return Sempre retorna o tempo médio de empacotamento do lote.
//    */
//   public double getTempoMedio() {
//      return 0.0;
//   }
//   /**
//    * O método serve para retornar a quantidade de pacotes possíveis de serem
//    * produzidos no intervalo de tempo inserido.
//    * @param pedidosList
//    * @param
//    * @return Retorna a quantidade total de pacotes
//    */
//   public int getQntPacotesTotal(ListaPedidos pedidosList) {
//      for (int i = pedidosList.size(); i > 0; i--) { // Loop para teste do carregamento e
//         Pedido teste = pedidosList.get(i); // impressão dos pedidos com prioridade
//         qntPacotes += ((pedidosList.get(i).getTotalProdutos() *PACOTE)/CAPACIDADE);
//      }
//      return qntPacotes; // retorna a quantidade de pacotes Total
//   }
//   /**
//    * Método para empacotar o elemento com maior prioridade
//    * @param pedidosList
//    * @return
//    */
//   public void empacotarPedidos(ListaPedidos pedidosList, Horario inicio) {
//      System.out.println("Impressão da Heap dos pedidos prioritários\n");
//      for (int i=0; i < pedidosList.size(); i++) { // Loop para teste do carregamento e
//         Pedido pedido = pedidosList.get(i); // impressão dos pedidos com prioridade
//         Horario concluido = inicio;
//         this.volumeTotal = this.totalProdutos * PACOTE;
//         this.totalProdutos = pedido.getTotalProdutos();
//         this.qntPacotes = this.volumeTotal / CAPACIDADE;
//         this.tempoEmpacotando = ((this.qntPacotes * TEMPO_EMPACOTAMENTO) + (this.qntPacotes * TEMPO_ROLAMENTO));
//         concluido.addSeconds(tempoEmpacotando);
//         tempoPrevisao = (28_800 + tempoEmpacotando);
//         Horario horarioPrevisao = new Horario(tempoPrevisao);
//         Horario horarioPrazo = new Horario(pedido.getPrazo());
//         System.out.println("Cliente: " + pedido.getCliente() + " | N° produtos: " + pedido.getTotalProdutos() +
//                 " | Prazo (mins): " + pedido.getPrazo() + " | Prazo (horario): " + horarioPrazo);
//         System.out.println(" Previsão: " + horarioPrevisao.toString() + " | Início: " + inicio.toString() +
//                 " | Concluído: " + concluido.toString());
//      }
//   }
//}
