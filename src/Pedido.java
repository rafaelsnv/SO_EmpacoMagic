public class Pedido implements Comparable<Pedido> {
   private static final int PRAZO_ZERO_BASE = (Integer.MAX_VALUE / 1_000_000) * 60 * 10_000;
   private static final int PRAZO_NORMAL_BASE = 10_000;
   private static final int VOL_PACOTE_MAX = 5_000; // cm^3
   private static final int VOL_PRODUTO = 250; // cm^3

   private int id;
   private String cliente;
   private int qtdProdutos; // unidades
   private int prazo; // segundos
   private int horaDeChegada;
   private double tempoDeRetorno; // segundos
   private Horario horarioConclusao;
   private Horario horarioPrazo;
   private int prioridade;

   /**
    * Método Construtor sem parâmetros
    */
   public Pedido() {
      this.setID(0);
      this.setCliente("unknown");
      this.setQtdProdutos(0);
      this.setPrazo(0);
      this.setHoraDeChegada(0);
      this.tempoDeRetorno = -1;
      this.horarioConclusao = new Horario(0);
      this.horarioPrazo = new Horario(0);
   }

   /**
    * Método Construtor com parâmetros
    * @param id               ID do pedido
    * @param cliente          O nome do cliente
    * @param qtdProdutos      A quantidade total de produtos
    * @param prazo            O prazo para empacotamento
    * @param horaDeChegada    Hora de chegada do pedido
    */
   public Pedido(int id, String cliente, int qtdProdutos, int prazo, int horaDeChegada) {
      this.setID(id);
      this.setCliente(cliente);
      this.setQtdProdutos(qtdProdutos);
      this.setPrazo(prazo);
      this.setHoraDeChegada(horaDeChegada);
      this.tempoDeRetorno = -1;
      this.horarioConclusao = new Horario(0);
      this.horarioPrazo = new Horario(0);
   }

    /**
    * Método de comparação entre dois Pedidos
    * 
    * @param outro Outro objeto da Classe Pedido.
    * @return (int) Diferença entre prioridades. Positivo se objeto for maior que o
    *         parâmetro.
    */
    @Override
    public int compareTo(Pedido outro) {
       return this.getPrioridade() - outro.getPrioridade();
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
       String horaEsperada = String.format("Esperado às: %s | ", this.getHorarioPrazo());
       String estouroPrazo = String.format("Diferença: %5.1f min", horarioConclusao.compareTo(horarioPrazo) / 60);
 
       return pedido + cliente + numProdutos + prazo + retorno + conclusao + horaEsperada + estouroPrazo;
    }

   public int getHoraDeChegada() {
      return horaDeChegada;
   }

   public void setHoraDeChegada(int horaDeChegada) {
      this.horaDeChegada = horaDeChegada;
   }

   public void setConclusao(Horario entrada, Horario conclusao) {
      this.tempoDeRetorno = conclusao.toSeconds() - entrada.toSeconds();
      this.horarioConclusao = new Horario(conclusao.toSeconds());

      if (this.getPrazo() == 0)
         this.horarioPrazo = new Horario(conclusao.toSeconds());
      else
         this.horarioPrazo = new Horario(entrada.toSeconds() + (double) this.getPrazo());
   }
   
   public void setID(int id) {
      this.id = id;
   }

   public int getID() {
      return this.id;
   }

   public void setCliente(String nome) {
      this.cliente = nome;
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

   public double getTempoDeRetorno() {
      return this.tempoDeRetorno;
   }

   public Horario getHorarioConclusao() {
      return this.horarioConclusao;
   }

   public Horario getHorarioPrazo() {
      return this.horarioPrazo;
   }

}
