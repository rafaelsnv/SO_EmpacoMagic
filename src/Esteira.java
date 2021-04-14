import java.util.ArrayList;


public class Esteira {
   private static int CAPACIDADE = 5000;
   private static int PACOTE = 250; 
   private static double TEMPO_ROLAMENTO = 0.5;
   private static double TEMPO_EMPACOTAMENTO = 5.0;
   private static Horario HORA_INICIO = new Horario(28800);
   private static Horario HORA_FINAL = new Horario(61200);
   private ArrayList tempoMedio = new ArrayList();
   private double tempoDeProducao;
   
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
    * O método serve para retornar a quantidade de pacotes possíveis de serem produzidos no intervalo de tempo inserido.
    * @param inicio  Hora de inicio do empacotamento.
    * @param fim     Hora do fim do empacotamento.
    * @return        Retorna a quantidade possível de pacotes produzidos no intervalo de tempo.
    */
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
   }

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
         }

      } while (esteiraLivre == false);       // Fim do loop de preenchimento

      double qtdDePacote = cargaAtual/PACOTE;     // Qnt de pacote que a esteira irá produzir
      return qtdDePacote * TEMPO_EMPACOTAMENTO;   // Retorna o Tempo gasto para EMPACOTAR o máxixmo da esteira (Em segundos)
   }

   
}
