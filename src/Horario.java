import java.text.*;

public class Horario {
   private double hrs;
   private double min;
   private double sec;

   /**
    * Método construtor
    * @param seconds (double) Horário em segundos. Zero (0) segundos representa meia-noite, 00h.
    */
   public Horario (double seconds) {
      this.setHorario(seconds);
   }

   /**
    * Método privado para ajustar o horário
    * @param seconds (double) Horário em segundos. Zero (0) segundos representa meia-noite, 00h.
    */
   private void setHorario(double seconds) {
      this.sec = seconds % 60;
      double aux = Math.floor(seconds/60);
      this.min = aux % 60;
      this.hrs = Math.floor(aux/60);
   }

   /**
    * Adiciona segundos ao Horário
    * @param seconds (double) Número de segundos a serem adicionados.
    * @return
    */
   public void addSeconds(double seconds) {
      double aux = this.toSeconds() + seconds;
      this.setHorario(aux);
   }

   /**
    * Subtrai segundos do Horário
    * @param seconds (double) Número de segundos a serem subtraídos.
    */
   public void subtractSeconds(double seconds) {
      double aux = this.toSeconds() - seconds;
      this.setHorario(aux);
   }

   /**
    * Adiciona minutos ao Horário
    * @param minutes (double) Número de minutos a serem adicionados.
    */
   public void addMinutes(double minutes) {
      double aux = this.toSeconds() + minutes*60;
      this.setHorario(aux);
   }

   /**
    * Subtrai minutos do Horário
    * @param minutes (double) Número de minutos a serem subtraídos.
    */
   public void subtractMinutes(double minutes) {
      double aux = this.toSeconds() - minutes*60;
      this.setHorario(aux);
   }

   /**
    * Adiciona horas ao Horário
    * @param hours (double) Número de horas a serem adicionadas.
    */
   public void addHours(double hours) {
      double aux = this.toSeconds() + hours*3600;
      this.setHorario(aux);
   }

   /**
    * Subtrai horas do Horário
    * @param hours (double) Número de horas a serem subtraídas.
    */
   public void subtractHours(double hours) {
      double aux = this.toSeconds() - hours*3600;
      this.setHorario(aux);
   }

   /**
    *
    * @param horario
    */
   public void subtractHorario(Horario horario) {
      double aux = this.toSeconds() - horario.toSeconds();
      this.setHorario(aux);
   }

   /**
    * Método de comparação entre dois horários
    * @param horario Objeto da classe Horario.
    * @return (double) Diferença em segundos. Positivo se objeto for maior que o parâmetro.
    */
   public double compareTo(Horario horario) {
      return this.toSeconds() - horario.toSeconds();
   }

   /**
    * Método de comparação com horário em segundos
    * @param seconds (double) Horário em segundos. Zero (0) segundos representa meia-noite, 00h.
    * @return (double) Diferença em segundos. Positivo se objeto for maior que o parâmetro.
    */
   public double compareTo(double seconds) {
      return this.toSeconds() - seconds;
   }

   /**
    * Converte objeto Horário para horário em segundos
    * @return (double) Horário em segundos.  Zero (0) segundos representa meia-noite, 00h.
    */
   public double toSeconds() {
      return this.hrs*3600 + this.min*60 + this.sec;
   }

   /**
    * Converte objeto Horário para horário em minutos
    * @return (double) Horário em minutos.  Zero (0) minutos representa meia-noite, 00h.
    */
   public double toMinutes() {
      return this.hrs/60 + this.min + this.sec*60;
   }

   /**
    * Converte objeto Horário para horário em horas
    * @return (double) Horário em horas.  Zero (0) horas representa meia-noite, 00h.
    */
   public double toHours() {
      return this.hrs + this.min/60 + this.sec/3600;
   }

   /**
    * Formata objeto Horário para String
    * @return (String) Horário formatado.
    */
   @Override
   public String toString() {
      DecimalFormat myFormatter = new DecimalFormat("00.0");
      String seg = myFormatter.format(this.sec);
      return String.format("%1$02.0f:%2$02.0f:%3$s", this.hrs, this.min, seg);
   }
}
