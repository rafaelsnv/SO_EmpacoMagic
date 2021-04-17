public class Esteira {
   private static final double TEMPO_ROLAMENTO = 0.5;                        // segundos
   private static final double TEMPO_EMPACOTAMENTO = 5.0;                    // segundos
   private static final Horario HORARIO_INICIAL = new Horario(28_800); // 08:00:00
   private static final Horario HORARIO_FINAL = new Horario(61_200);   // 17:00:00

   private ListaPedidos listaPedidos;
   private Horario horarioAtual;
   private String relatorioPedidos;
   private String relatorioEstatistico;
   private double tempoRetornoAcumulado;

   public Esteira() {
      this.listaPedidos = new ListaPedidos();
      this.horarioAtual = new Horario(28_800);
      this.tempoRetornoAcumulado = 0;
      this.relatorioPedidos = "Pedidos: Aguardando empacotamento!";
      this.relatorioEstatistico = "Estatística: Aguardando empacotamento!";
   }

   public void setListaPedidos(ListaPedidos listaPedidos) {
      listaPedidos.sort();
      this.listaPedidos = listaPedidos;
   }

   public void empacotarPedidos() {
      for(int i=0; i < this.listaPedidos.size(); i++) {
         Pedido pedido = this.listaPedidos.get(i);

         double tempoEmpacotamento = (TEMPO_EMPACOTAMENTO + TEMPO_ROLAMENTO) * pedido.getQtdPacotes();
         this.horarioAtual.addSeconds(tempoEmpacotamento);

         pedido.setConclusao(HORARIO_INICIAL.toSeconds(), this.horarioAtual.toSeconds());

         this.listaPedidos.updatePedido(i, pedido);

         this.tempoRetornoAcumulado += pedido.getTempoDeRetorno();
      }
   }

   /**
    * O método deve retornar o tempo médio gasto para executar o lote de produtos.
    * @return Sempre retorna o tempo médio de empacotamento do lote.
    */
   public double getTempoRetornoMedio() {
      return this.tempoRetornoAcumulado / listaPedidos.size();
   }

   public void buildRelatorioPedidos() {
      StringBuilder builder = new StringBuilder("RELATÓRIO DE PEDIDOS:\n");
      for(int i=0; i < this.listaPedidos.size(); i++) {
         Pedido pedido = listaPedidos.get(i);
         builder.append( pedido.toString() );
         builder.append("\n");
      }

      this.relatorioPedidos = builder.toString();
   }

   public String getRelatorioPedidos() {
      return this.relatorioPedidos;
   }

   public void buildRelatorioEstatistico() {
      StringBuilder builder = new StringBuilder("RELATÓRIO ESTATÍSTICO:\n");
      String aux = String.format("Tempo médio de retorno: %.1f min", this.getTempoRetornoMedio() / 60);
      builder.append(aux);

      this.relatorioEstatistico = builder.toString();
   }

   public String getRelatorioEstatistico() {
      return this.relatorioEstatistico;
   }

}
