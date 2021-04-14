public class Esteira {
   private static int CAPACIDADE = 5000;
   private static int PACOTE = 250;
   private static double TEMPO_ROLAMENTO = 0.5;
   private static double TEMPO_EMPACOTAMENTO = 5.0;
   private int qntPacotes = 0;
   private int volumeTotal = 0;
   private int totalProdutos = 0;

   public Esteira() {
   }

   /**
    * O método deve retornar o tempo medio gasto para executar o lote de produtos.
    * 
    * @return Sempre retorna o tempo medio de empacotamento do lote.
    */
   public double getTempoMedio() {
      return 0.0;
   }

   
   /**
    * O método serve para retornar a quantidade de pacotes possíveis de serem
    * produzidos no intervalo de tempo inserido.

    * @param pedidosPrazo
    * @param pedidosQtd
    * @return Retorna a quantidade total de pacotes
    */
   public int getQntPacotesTotal(HeapPrazo pedidosPrazo, HeapQtd pedidosQtd) {
      for (int i = pedidosPrazo.size(); i > 0; i--) { // Loop para teste do carregamento e
         HeapQtd teste = pedidosPrazo.retorno(); // impressão dos pedidos com prioridade
         for (int j = teste.size(); j > 0; j--) {
            Pedido pedi = teste.retorno();
            qntPacotes += ((pedi.getTotalProdutos()*PACOTE)/CAPACIDADE);
         }
      }
      return qntPacotes; // retorna a quantidade de pacotes Total
   }
   /**
    * Método para empacotar o elemento com maior prioridade
    * @param pedidosPrazo
    * @param pedidosQtd
    * @return
    */
   public double empacotarPedido(HeapPrazo pedidosPrazo, HeapQtd pedidosQtd) {

      for (int i = 1; i > 0; i--) { // Loop para teste do carregamento e
         HeapQtd qntProdutos = pedidosPrazo.retorno(); // impressão dos pedidos com prioridade
         for (int j = 1; j > 0; j--) {
            Pedido pedi = qntProdutos.retorno();
            totalProdutos = pedi.getTotalProdutos();

         }
      }

      volumeTotal = totalProdutos * PACOTE;
      qntPacotes = volumeTotal / CAPACIDADE;
      return (qntPacotes * TEMPO_EMPACOTAMENTO) + (qntPacotes * TEMPO_ROLAMENTO); // Retorna o tempo do empacotamento em
                                                                                  // segundos (Apenas do mais
                                                                                  // prioritário)

   }
}
