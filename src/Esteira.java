public class Esteira {
   protected static final double TEMPO_ROLAMENTO = 0.5;                        // segundos
   protected static final double TEMPO_EMPACOTAMENTO = 5.0;                    // segundos
   protected static final Horario HORARIO_INICIAL = new Horario(28_800); // 08:00:00
   protected static final Horario HORARIO_FINAL = new Horario(61_200);   // 17:00:00

   protected ListaPedidos listaPedidos;
   protected Horario horarioAtual;
   protected String relatorioPedidos;
   protected String relatorioEstatistico;
   protected double tempoRetornoAcumulado;

   public Esteira() {
      this.listaPedidos = new ListaPedidos();
      this.horarioAtual = new Horario(28_800);
      this.tempoRetornoAcumulado = 0;
      this.relatorioPedidos = "RELATÓRIO DE PEDIDOS:\nAguardando empacotamento!";
      this.relatorioEstatistico = "RELATÓRIO ESTATÍSTICO:\nAguardando empacotamento!";
   }

   public void setListaPedidos(ListaPedidos listaPedidos) {
      this.listaPedidos = listaPedidos;
   }

   public void empacotar() {
      for(int i=0; i < this.listaPedidos.size(); i++) {
         Pedido pedido = this.listaPedidos.get(i);

         double tempoEmpacotamento = (TEMPO_EMPACOTAMENTO + TEMPO_ROLAMENTO) * pedido.getQtdPacotes();
         this.horarioAtual.addSeconds(tempoEmpacotamento);

         pedido.setConclusao(HORARIO_INICIAL, this.horarioAtual);

         this.listaPedidos.update(i, pedido);

         this.tempoRetornoAcumulado += pedido.getTempoDeRetorno();
      }
   }

   /**
    * O método deve retornar o tempo médio gasto para executar o lote de produtos.
    * 
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
      String finalizacao = String.format("Finalizado às: %s\n", this.horarioAtual.toString());
      String retorno = String.format("Tempo médio de retorno: %.1f min\n", this.getTempoRetornoMedio() / 60);
      String numAntes12h = String.format("Nº de pacotes antes das 12h: %d\n", this.getNumAntes12h());
      String atrasos = String.format("Nº de atrasos: %d\n", this.getNumAtrasos());

      StringBuilder builder = new StringBuilder("RELATÓRIO ESTATÍSTICO:\n");
      builder.append(finalizacao);
      builder.append(retorno);
      builder.append(numAntes12h);
      builder.append(atrasos);

      this.relatorioEstatistico = builder.toString();
   }

   public String getRelatorioEstatistico() {
      return this.relatorioEstatistico;
   }

}