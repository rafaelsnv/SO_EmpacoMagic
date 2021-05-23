public class SyncRelogio {
   private final Horario horarioGeral;

   public SyncRelogio() {
      this.horarioGeral = new Horario(28_800);
   }

   public synchronized void addSeconds(double seconds) {
      this.horarioGeral.addSeconds(seconds);
   }

   public synchronized Horario getHorarioGeral() {
      return this.horarioGeral;
   }
}
