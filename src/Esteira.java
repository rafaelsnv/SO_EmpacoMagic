public class Esteira {
   private static int CAPACIDADE = 5000;
   private static int PACOTE = 250;
   private static double TEMPO_ROLAMENTO = 0.5;
   private static double TEMPO_EMPACOTAMENTO = 5.0;
   private int qntPacotes = 0;
   private int volumeTotal = 0;
   private int totalProdutos = 0;
   private double tempoEmpacotando;

   public Esteira() {
      qntPacotes = 0;
      volumeTotal = 0;
      totalProdutos = 0;
   }

   /**
    * O método deve retornar o tempo médio gasto para executar o lote de produtos.
    * 
    * @return Sempre retorna o tempo médio de empacotamento do lote.
    */
   public double getTempoMedio() {
      return 0.0;
   }

   /**
    * O método serve para retornar a quantidade de pacotes possíveis de serem
    * produzidos no intervalo de tempo inserido.
    * 
    * @param pedidosList
    * @param
    * @return Retorna a quantidade total de pacotes
    */
   public int getQntPacotesTotal(ListaPedidos pedidosList) {
      for (int i = pedidosList.size(); i > 0; i--) { // Loop para teste do carregamento e
         Pedido teste = pedidosList.get(i); // impressão dos pedidos com prioridade
         qntPacotes += ((pedidosList.get(i).getTotalProdutos() * PACOTE) / CAPACIDADE);
      }
      return qntPacotes; // retorna a quantidade de pacotes Total
   }

   /**
    * Método para empacotar todos os produtos da lista
    * @param pedidosList
    * @param inicio
    */
   public void empacotarPedidos(ListaPedidos pedidosList, Horario inicio) {
      System.out.println("Impressão da Heap dos pedidos prioritários\n");
      double tempoMedio = 0.0;
      double tempoGasto = 0.0;
      double producaoFeita = 0.0;
      double producaoMedia = 0;
      double qtPedidoAtendidos = 1.0;
      Horario horarioInicio = new Horario(28_800);
      Horario horarioMedio = new Horario(0);
      for (int i = 0; i < pedidosList.size(); i++) { // Loop para teste do carregamento e
         Pedido pedido = pedidosList.get(i); // impressão dos pedidos com prioridade
         Horario concluido = inicio;
         this.totalProdutos = pedido.getTotalProdutos();
         this.volumeTotal = this.totalProdutos * PACOTE;
         this.qntPacotes = this.volumeTotal / CAPACIDADE;
         this.tempoEmpacotando = ((this.qntPacotes * TEMPO_EMPACOTAMENTO) + (this.qntPacotes * TEMPO_ROLAMENTO));
         tempoGasto += this.tempoEmpacotando;
         tempoMedio = tempoGasto / qtPedidoAtendidos;
         producaoFeita += qntPacotes;
         producaoMedia = producaoFeita / qtPedidoAtendidos;
         horarioMedio = new Horario(tempoMedio);
         qtPedidoAtendidos++;
         concluido.addSeconds(tempoEmpacotando);
         // Horario horarioPrevisao = new Horario(tempoPrevisao);
         Horario horarioPrazo = new Horario(pedido.getPrazo());
         System.out.println("Cliente: " + pedido.getCliente() + " | N° produtos: " + pedido.getTotalProdutos()
               + " | Prazo (mins): " + pedido.getPrazo() + " | Prazo (horario): " + horarioPrazo);
         System.out.println("Início: " + horarioInicio.toString() + " | Concluído: " + concluido.toString()
               + " | Prazo: " + concluido.compareTo(horarioPrazo) + "\n");
         horarioInicio.addSeconds(tempoEmpacotando);
      }
      System.out.println("Tempo Médio por pedido: " + horarioMedio);
      System.out.printf("Quantidade Média de pacotes por pedido: " + "%.2f", producaoMedia);
   }
}
