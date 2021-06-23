import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncRelatorio {
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
      Pedido ultimoPedido = this.syncList.get(this.syncList.size() - 1);
      return ultimoPedido.getHorarioConclusao();
   }

   public String relatorioEstatistico() {
      String finalizacao = String.format("Finalizado às: %s\n", getHorarioFinalizacao().toString());
      String retorno = String.format("Tempo médio de retorno: %.1f min\n", this.getTempoRetornoMedio() / 60);
      String numAntes12h = String.format("Nº de pacotes antes das 12h: %d\n", this.getNumAntes12h());
      String atrasos = String.format("Nº de atrasos: %d\n", this.getNumAtrasos());

      return "RELATÓRIO ESTATÍSTICO:\n" +
              finalizacao +
              retorno +
              numAntes12h +
              atrasos;
   }
}