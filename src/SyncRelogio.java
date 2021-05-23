public class SyncRelogio {
   private final Horario horarioGeral;

   public SyncRelogio() {
      this.horarioGeral = new Horario(28_800);
   }

   public synchronized void addSeconds() {
         this.horarioGeral.addSeconds(30);
   }

   public synchronized void addMinutes() {
      this.horarioGeral.addMinutes(0.5);
   }

   public synchronized Horario getHorarioGeral() {
      return this.horarioGeral;
   }
}
