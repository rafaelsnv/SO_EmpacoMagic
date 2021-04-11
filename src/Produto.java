public class Produto {
   private String tipo;
   private double volume;

   public Produto (String qualTipo, double qualVolume){
      this.tipo = qualTipo;
      this.volume = qualVolume;
   }

   public void setVolume(double qualVolume) {
      this.volume = qualVolume;
   }

   public double getVolume() {
      return volume;
   }

   public void setTipo(String qualTipo) {
      this.tipo = qualTipo;
   }

   public String getTipo() {
      return tipo;
   }

   @Override
   public String toString() {
      return "Produto"+
              "\ntipo: " + tipo +
              "\nvolume: " + volume;
   }
}