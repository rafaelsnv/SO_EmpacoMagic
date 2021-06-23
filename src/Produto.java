public class Produto {
   private String tipo;
   private double volume;

   /**
    * Método Construtor
    * 
    * @param qualTipo   O tipo de produto
    * @param qualVolume O volume do produto
    */
   public Produto(String qualTipo, double qualVolume) {
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

   /**
    * Método conversor para impressão
    * 
    * @return Uma string com os valores armazenados no objeto
    */
   @Override
   public String toString() {
      return "Produto" + "\ntipo: " + tipo + "\nvolume: " + volume;
   }

}