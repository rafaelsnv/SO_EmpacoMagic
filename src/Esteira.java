public class Esteira {
   private static int CAPACIDADE = 5000;
   private static int PACOTE = 250;
   private static double TEMPO_ROLAMENTO = 0.5;
   private static double TEMPO_EMPACOTAMENTO = 5.0;
   private int qntPacotes = 0;
   private int volumeTotal = 0;
   private int totalProdutos = 0;
   private double tempoEmpacotando;
   private double tempoPrevisao;

   public Esteira() {
   }

   /**
    * O método deve retornar o tempo medio gasto para executar o lote de produtos.
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
   public void empacotarPedidos(HeapPrazo pedidosPrazo, HeapQtd pedidosQtd, Horario horario) {
      
      System.out.println("Impressão da Heap dos pedidos prioritários\n");
      for (int i = pedidosPrazo.size()-1; i >= 0; i--) { // Loop para teste do carregamento e
         HeapQtd teste = pedidosPrazo.retorno(); // impressão dos pedidos com prioridade
         Horario inicio = horario;
         for (int j = teste.size(); j > 0; j--) {
            Pedido pedi = teste.retorno();

            this.totalProdutos = pedi.getTotalProdutos();
            this.volumeTotal = this.totalProdutos * PACOTE;
            this.qntPacotes = this.volumeTotal / CAPACIDADE;
            this.tempoEmpacotando = ((this.qntPacotes * TEMPO_EMPACOTAMENTO) + (this.qntPacotes * TEMPO_ROLAMENTO));
            horario.addSeconds(tempoEmpacotando);
            tempoPrevisao = (28_800 + tempoEmpacotando);
            Horario horarioPrevisao = new Horario(tempoPrevisao);
            Horario horarioPrazo = new Horario(pedi.getPrazo());
            
            System.out.println("Cliente: "+pedi.getCliente() + " | N° produtos: " + pedi.getTotalProdutos() + " | Prazo: " + horarioPrazo);
            System.out.println("Previsão: " + horarioPrevisao.toString() +" | Iniício: "+ inicio +" | Concluído: " + horario.toString());
            System.out.println();
         }
      }     

      System.out.println("___________________________________________________________________________________________\n" +
              "Impressão da Heap dos pedidos sem prioridade\n");
      for (int i = pedidosQtd.size(); i > 0; i--) { // Loop para teste do carregamento e
         Pedido testeQtd = pedidosQtd.retorno(); // impressão dos pedidos sem prioridade

         this.totalProdutos = testeQtd.getTotalProdutos();
         this.volumeTotal = this.totalProdutos * PACOTE;
         this.qntPacotes = this.volumeTotal / CAPACIDADE;
         this.tempoEmpacotando = ((this.qntPacotes * TEMPO_EMPACOTAMENTO) + (this.qntPacotes * TEMPO_ROLAMENTO));
         horario.addSeconds(tempoEmpacotando);
         tempoPrevisao = (28_800 + tempoEmpacotando);
         Horario horarioPrevisao = new Horario(tempoPrevisao);
         Horario horarioPrazo = new Horario(testeQtd.getPrazo()/60);
         
         System.out.println("Cliente: "+testeQtd.getCliente() + " | N° produtos: " + testeQtd.getTotalProdutos() + " | Prazo: " + horarioPrazo);
         System.out.println("Previsão: " +horarioPrevisao.toString()+" | Concluído: " + horario.toString());
         System.out.println();
      } 
   }
}
