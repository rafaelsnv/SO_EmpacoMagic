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

   public int getNumAtrasos() {
      int numAtrasos = 0;
      for(int i=0; i < listaPedidos.size(); i++) {
         Pedido pedido = listaPedidos.get(i);
         Horario conclusao = pedido.getHorarioConclusao();
         Horario esperado = pedido.getHorarioPrazo();
         if (conclusao.compareTo(esperado) > 0)
            numAtrasos++;
      }
      return numAtrasos;
   }

   private int getNumAntes12h() {
      int numPacotes = 0;
      for(int i=0; i < listaPedidos.size(); i++) {
         Pedido pedido = listaPedidos.get(i);
         Horario conclusao = pedido.getHorarioConclusao();
         Horario meioDia = new Horario(43200);
         if (conclusao.compareTo(meioDia) < 0)
            numPacotes++;
      }
      return numPacotes;
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
      String retorno = String.format("Tempo médio de retorno: %.1f min\n", this.getTempoRetornoMedio() / 60);
      String numAntes12h = String.format("Nº de pacotes antes das 12h: %d\n", this.getNumAntes12h());
      String atrasos = String.format("Nº de atrasos: %d\n", this.getNumAtrasos());
      builder.append(retorno);
      builder.append(numAntes12h);
      builder.append(atrasos);

      this.relatorioEstatistico = builder.toString();
   }



   public String getRelatorioEstatistico() {
      return this.relatorioEstatistico;
   }

}
