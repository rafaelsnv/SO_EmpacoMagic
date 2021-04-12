import java.util.ArrayList;


public class Esteira {
   private static int CAPACIDADE = 5000;
   private static int PACOTE = 250; 
   private static double TEMPO_ROLAMENTO = 0.5;
   private static double TEMPO_EMPACOTAMENTO = 5.0;
   private static Horario HORA_INICIO = new Horario(28800);
   private static Horario HORA_FINAL = new Horario(61200);
   private ArrayList tempoMedio = new ArrayList();
   
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
   public int getQntPacotesParcial(Horario inicio, Horario fim) {
      return 0;
   }

   public void empacotarPedidos(){
      Heap Heap = new Heap();
      boolean esteiraLivre = false;
      int cargaAtual = 0;
      Pedido aux;

      /**
       * Loop para preenchimento da esteira com capacidade de até 5000cm³ 
       */
      do {
         aux = Heap.retorno();            // TOTAL DE PRODUTOS

         if (cargaAtual<CAPACIDADE) {
            cargaAtual += aux.getTotalProdutos();               // AUTOSOMA DO TOTAL DE PRODUTOS
         }else{
            cargaAtual -= aux.getTotalProdutos();               // REMOVE A QNT DO ULTIMO PEDIDO, CASO A ESTEIRA NÃO SUPORTE MAIS
            Heap.insere(aux);                // RETORNA O PEDIDO PARA A HEAP
            esteiraLivre = true;             // FECHA A PRODUÇÃO DA ESTEIRA
         }

      } while (esteiraLivre == false);       // FIM DO LOOP DE PREENCHIMENTO

      double qtdDePacote = cargaAtual/PACOTE;                        // QNT DE PACOTE QUE A ESTEIRA IRA PRODUZIR
      double tempoEmpacotando = qtdDePacote * TEMPO_EMPACOTAMENTO;   // TEMPO GASTO PARA EMPACOTAR OS PRODUTOS (Em segundos)
   }
}
