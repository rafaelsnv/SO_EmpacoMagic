public class Esteira {
   protected static final double TEMPO_ROLAMENTO = 0.5;                        // segundos
   protected static final double TEMPO_EMPACOTAMENTO = 5.0;                    // segundos

   private FilaEmpacotar fila;
   private final SyncRelogio relogio;

   public Esteira(SyncRelogio relogio, FilaEmpacotar fila) {
      this.fila = fila;
      this.relogio = relogio;
   }

   public void empacotar() {
      
   }


}