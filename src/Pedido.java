public class Pedido implements Comparable<Pedido> {
   private static final int PRAZO_ZERO_BASE = (Integer.MAX_VALUE/1_000_000)*60*10_000;
   private static final int PRAZO_NORMAL_BASE = 10_000;
   private static final int VOL_PACOTE_MAX = 5000;            // cm^3
   private static final int VOL_PRODUTO = 250;                // cm^3

   private int id;
   private String cliente;
   private int qtdProdutos;                                   // unidades
   private int prazo;                                         // segundos
   private int prioridade;
   private double tempoDeRetorno;                             // segundos
   private Horario horarioConclusao;
   private Horario horarioPrazo;


   /**
    * Método Construtor sem parâmetros
    */
   public Pedido () {
      this.setID(0);
      this.setCliente("unknown");
      this.setQtdProdutos(0);
      this.setPrazo(0);
      this.setPrioridade(0);
      this.tempoDeRetorno = -1;
      this.horarioConclusao = new Horario(0);
      this.horarioPrazo = new Horario(0);
   }

   /**
    * Método Construtor com parâmetros
    * @param cliente O nome do cliente
    * @param qtdProdutos A quantidade total de produtos
    * @param prazo O prazo para entrega do pedido
    */
   public Pedido (int id, String cliente, int qtdProdutos, int prazo) {
      this.setID(id);
      this.setCliente(cliente);
      this.setQtdProdutos(qtdProdutos);
      this.setPrazo(prazo);
      this.setPrioridade(prazo);
      this.tempoDeRetorno = -1;
      this.horarioConclusao = new Horario(0);
      this.horarioPrazo = new Horario(0);
   }

   public void setID(int id) {
      this.id = id;
   }

   public int getID() {
      return this.id;
   }

   public void setCliente(String cliente) {
      this.cliente = cliente;
   }

   public String getCliente() {
      return cliente;
   }

   public void setPrazo(int prazo) {
      this.prazo = prazo * 60;
   }

   public int getPrazo() {
      return prazo;
   }

   public void setQtdProdutos(int qtdProdutos) {
      this.qtdProdutos = qtdProdutos;
   }

   public int getQtdProdutos() {
      return qtdProdutos;
   }

   public void setPrioridade(int prazo) {
      if (prazo == 0)
         this.prioridade = PRAZO_ZERO_BASE + this.qtdProdutos;
      else
         this.prioridade = prazo * PRAZO_NORMAL_BASE + this.qtdProdutos;
   }

   public int getPrioridade() {
      return this.prioridade;
   }

   public int getQtdPacotes() {
      return this.qtdProdutos * VOL_PRODUTO / VOL_PACOTE_MAX;
   }

   public void setConclusao(double entrada, double conclusao) {
      this.tempoDeRetorno = conclusao - entrada;
      this.horarioConclusao = new Horario(conclusao);

      if (this.getPrazo() == 0)
         this.horarioPrazo = new Horario(conclusao);
      else
         this.horarioPrazo = new Horario(entrada + this.getPrazo());
   }

   public double getTempoDeRetorno() {
      return this.tempoDeRetorno;
   }

   public Horario getHorarioConclusao() {
      return this.horarioConclusao;
   }

   public Horario getHorarioPrazo() {
      return this.horarioPrazo;
   }
   /**
    * Método de comparação entre dois Pedidos
    * @param other Outro objeto da Classe Pedido.
    * @return (int) Diferença entre prioridades. Positivo se objeto for maior que o parâmetro.
    */
   @Override
   public int compareTo (Pedido other){
      return this.getPrioridade() - other.getPrioridade();
   }

   /**
    * Método conversor para impressão
    * @return Uma string com os valores armazenados no objeto
    */
   @Override
   public String toString() {
      String pedido = String.format("Pedido nº%03d | ", this.getID());
      String cliente = String.format("Cliente: %-19s | ", this.getCliente());
      String numProdutos = String.format("Nº produtos: %4d | ", this.getQtdProdutos());
      String prazo = String.format("Prazo: %3d min | ", this.getPrazo() / 60);
      String retorno = String.format("Retorno: %5.1f min | ", this.getTempoDeRetorno() / 60);
      String conclusao = String.format("Concluído em: %s | ", this.getHorarioConclusao().toString());
      String horaEsperada = String.format("Esperado: %s | ", this.getHorarioPrazo());
      String estouroPrazo = String.format("Diferença prazo: %5.1f min", horarioConclusao.compareTo(horarioPrazo) / 60);

      return pedido + cliente + numProdutos + prazo + retorno + conclusao + horaEsperada + estouroPrazo;
   }
}
