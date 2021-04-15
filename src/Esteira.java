public class Esteira {
   private static int CAPACIDADE = 5000;
   private static int PACOTE = 250;
   private static double TEMPO_ROLAMENTO = 0.5;
   private static double TEMPO_EMPACOTAMENTO = 5.0;
<<<<<<< HEAD
   private static Horario HORA_INICIO = new Horario(28800);
   private static Horario HORA_FINAL = new Horario(61200);
   private ArrayList tempoMedio = new ArrayList();
   private double tempoDeProducao;
   
   public Esteira() {
=======
   private int qntPacotes = 0;
   private int volumeTotal = 0;
   private int totalProdutos = 0;
>>>>>>> Heaps

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
<<<<<<< HEAD
   public int qntPacotesParcial(Horario inicio, Horario fim) {
      //double qntPacotesParcial = 0;
      do {
         
         // Chama o metodo de empacotar 
         // calcula a quantidade de pacotes produzidos
         // soma TEMPO_ROLAMENTO
         // Acrescenta os segundos
         // recursividade passando o novo Horario de inicio
 

      } while (false);
      return 0;
=======
   public int getQntPacotesTotal(HeapPrazo pedidosPrazo, HeapQtd pedidosQtd) {
      for (int i = pedidosPrazo.size(); i > 0; i--) { // Loop para teste do carregamento e
         HeapQtd teste = pedidosPrazo.retorno(); // impressão dos pedidos com prioridade
         for (int j = teste.size(); j > 0; j--) {
            Pedido pedi = teste.retorno();
            qntPacotes += ((pedi.getTotalProdutos()*PACOTE)/CAPACIDADE);
         }
      }
      return qntPacotes; // retorna a quantidade de pacotes Total
>>>>>>> Heaps
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

<<<<<<< HEAD
   public double empacotarPedido(Horario inicio, Horario fim){
      HeapQtd Heap = new HeapQtd();
      boolean esteiraLivre = false;
      int cargaAtual = 0;
      Pedido aux;

      /**
       * Loop para preenchimento da esteira com capacidade de até 5000cm³ 
       */
      do {
         aux = Heap.retorno();            // Total de Produtos

         if (cargaAtual<CAPACIDADE) {
            cargaAtual += aux.getTotalProdutos();               // Autosoma do total de Produtos
         }else{
            cargaAtual -= aux.getTotalProdutos();               // Remove a qnt do último pedido, caso a esteira não suporte mais
            Heap.insere(aux);                // Retorna o pedido para a heap
            esteiraLivre = true;             // Fecha a produção da esteira
=======
>>>>>>> Heaps
         }
      }

<<<<<<< HEAD
      } while (esteiraLivre == false);       // Fim do loop de preenchimento

      double qtdDePacote = cargaAtual/PACOTE;     // Qnt de pacote que a esteira irá produzir
      return qtdDePacote * TEMPO_EMPACOTAMENTO;   // Retorna o Tempo gasto para EMPACOTAR o máxixmo da esteira (Em segundos)
=======
      volumeTotal = totalProdutos * PACOTE;
      qntPacotes = volumeTotal / CAPACIDADE;
      return (qntPacotes * TEMPO_EMPACOTAMENTO) + (qntPacotes * TEMPO_ROLAMENTO); // Retorna o tempo do empacotamento em
                                                                                  // segundos (Apenas do mais
                                                                                  // prioritário)

>>>>>>> Heaps
   }

   
}
