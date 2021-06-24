import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SyncRelatorio {
   private static final double HORARIO_FINALIZACAO = 61_200;
   private final List<Pedido> syncList;
   private double tempoRetornoAcumulado = 0;

   public SyncRelatorio() {
      List<Pedido> lista = new ArrayList<>();
      this.syncList = Collections.synchronizedList(lista);
   }

   public void append(Pedido pedido){
      this.syncList.add(pedido);
      this.tempoRetornoAcumulado += pedido.getTempoDeRetorno();
   }

   public double getTempoRetornoMedio() {
      return this.tempoRetornoAcumulado / this.syncList.size();
   }

   public int getNumAntes12h() {
      int numPacotes = 0;
      for(Pedido pedido : this.syncList) {
         Horario conclusao = pedido.getHorarioConclusao();
         Horario meioDia = new Horario(43200);
         if (conclusao.compareTo(meioDia) <= 0)
            numPacotes++;
      }
      return numPacotes;
   }

   public int getNumAtrasos() {
      int numAtrasos = 0;
      for(Pedido pedido : this.syncList) {
         Horario conclusao = pedido.getHorarioConclusao();
         Horario esperado = pedido.getHorarioPrazo();
         if (conclusao.compareTo(esperado) > 0)
            numAtrasos++;
      }
      return numAtrasos;
   }

   public Horario getHorarioFinalizacao() {
      Horario maiorHorario = new Horario(0);

      for (Pedido pedido : this.syncList) {
         if(pedido.getHorarioConclusao().compareTo(maiorHorario) > 0)
            maiorHorario = pedido.getHorarioConclusao();
      }

      Horario horarioMax = new Horario(HORARIO_FINALIZACAO);

      if (maiorHorario.compareTo(horarioMax) > 0) {
         maiorHorario = horarioMax;
      }

      return maiorHorario;
   }

   public int getNumNaoAtentidos() {
      Horario horarioMax = new Horario(HORARIO_FINALIZACAO);
      int numNaoAtendidos = 0;

      for (Pedido pedido : this.syncList) {
         if(pedido.getHorarioConclusao().compareTo(horarioMax) > 0)
            numNaoAtendidos++;
      }

      return numNaoAtendidos;
   }




   @Override
   public String toString() {
      Comparator<Pedido> compararPorHorario = (Pedido p1, Pedido p2) ->
              (int) p1.getHorarioConclusao().compareTo(p2.getHorarioConclusao());

      Collections.sort(this.syncList, compararPorHorario);

      StringBuilder result = new StringBuilder("ORDEM DE EXECUÇÃO DOS PEDIDOS: \n");
      for (Pedido pd : this.syncList) {
         String aux = "\n" + pd.toString();
         result.append(aux);
      }
      return result.toString();
   }

   public String relatorioEstatistico() {
      String finalizacao = String.format("Finalizado às: %s\n", getHorarioFinalizacao().toString());
      String retorno = String.format("Tempo médio de retorno: %.1f min\n", getTempoRetornoMedio() / 60);
      String numAntes12h = String.format("Nº de pacotes antes das 12h: %d\n", getNumAntes12h());
      String atrasos = String.format("Nº de atrasos: %d\n", getNumAtrasos());
      String numNaoAtendidos = String.format("Nº não atentidos: %d\n", getNumNaoAtentidos());

      return "RELATÓRIO ESTATÍSTICO:\n" +
              finalizacao +
              retorno +
              numAntes12h +
              atrasos +
              numNaoAtendidos;
   }
}