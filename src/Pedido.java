public class Pedido implements Comparable<Pedido> {
   private static final int PRAZO_ZERO_BASE = (Integer.MAX_VALUE / 1_000_000) * 60 * 10_000;
   private static final int PRAZO_NORMAL_BASE = 10_000;
   private static final int VOL_PACOTE_MAX = 5_000;    // cm^3
   private static final int VOL_PRODUTO = 250;         // cm^3

   private int id;
   private String cliente;
   private int qtdProdutos;                   // unidades
   private int prazo;                         // segundos
   private int prioridade;
   private Horario horaDeChegada;
   private Horario horarioPrazo;
   private Horario horarioConclusao;
   private double tempoDeRetorno;             // segundos
   private int esteiraUsada;                  // ID da esteira que processou o pedido

   /**
    * Método Construtor sem parâmetros
    */
   public Pedido() {
      this.setID(0);
      this.setCliente("unknown");
      this.setQtdProdutos(0);
      this.setPrazo(0);
      this.setMinutoChegada(0);
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
    * @param minutoChegada    Minuto em que o pedido chegou após o início às 08:00
    */
   public Pedido(int id, String cliente, int qtdProdutos, int prazo, int minutoChegada) {
      this.setID(id);
      this.setCliente(cliente);
      this.setQtdProdutos(qtdProdutos);
      this.setPrazo(prazo);
      this.setMinutoChegada(minutoChegada);
      this.tempoDeRetorno = -1;
      this.horarioConclusao = new Horario(0);
      this.horarioPrazo = new Horario(0);
   }

   public void setID(int id) {
      this.id = id;
   }

   public void setCliente(String nome) {
      this.cliente = nome;
   }

   public void setQtdProdutos(int qtdProdutos) {
      this.qtdProdutos = qtdProdutos;
   }

   public void setPrazo(int prazo) {
      this.prazo = prazo * 60;
      this.setPrioridade(prazo);
   }

   public void setMinutoChegada(int minutoChegada) {
      this.horaDeChegada = new Horario(28_800 + (double) minutoChegada * 60);
   }

   private void setPrioridade(int prazo) {
      if (prazo == 0)
         this.prioridade = PRAZO_ZERO_BASE + this.qtdProdutos;
      else
         this.prioridade = prazo * PRAZO_NORMAL_BASE + this.qtdProdutos;
   }

   public void setConclusao(Horario conclusao, int esteiraID) {
      this.esteiraUsada = esteiraID;
      this.horarioConclusao = conclusao;
      this.tempoDeRetorno = conclusao.toSeconds() - this.horaDeChegada.toSeconds();

      if (this.getPrazo() == 0)
         this.horarioPrazo = conclusao;
      else
         this.horarioPrazo = new Horario(this.horaDeChegada.toSeconds() + (double) this.getPrazo());
   }

   public int getID() {
      return this.id;
   }

   public String getCliente() {
      return cliente;
   }

   public int getQtdProdutos() {
      return qtdProdutos;
   }

   public int getPrazo() {
      return prazo;
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

   public Horario getHoraDeChegada() {
      return horaDeChegada;
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
      String numProdutos = String.format("Nº prod.: %4d | ", this.getQtdProdutos());
      String esteira = String.format("Esteira %s | ", this.esteiraUsada);
      String horaChegada = String.format("Chegada: %s | ", this.getHoraDeChegada().toString());
      String horaConclusao = String.format("Conclusão: %s | ", this.getHorarioConclusao().toString());
      String horaPrazo = String.format("Prazo: %s | ", this.getHorarioPrazo());
      String estouroPrazo = String.format("Diferença: %6.1f min | ", horarioConclusao.compareTo(horarioPrazo) / 60);
      String retorno = String.format("Retorno: %5.1f min", this.getTempoDeRetorno() / 60);

      return pedido + cliente + numProdutos + esteira + horaChegada + horaConclusao + horaPrazo + estouroPrazo + retorno;
   }
}
