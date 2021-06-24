import java.util.concurrent.Semaphore;

public class Esteira extends Thread {
   private static final double TEMPO_ROLAMENTO = 0.5;    // segundos
   private static final double TEMPO_EMPACOTAMENTO = 5;  // segundos

   private final int meuID;
   private final SyncFila pedidos;
   private final SyncRelatorio relatorio;
   private final SyncListContainer listaContainers;
   private final Semaphore mutex;
   private final int numOp;
   public final Horario meuRelogio;

   public Esteira(int ID, SyncFila pedidos, SyncRelatorio relatorio, SyncListContainer containeres, Semaphore mutex, int numOp) {
      this.meuID = ID;
      this.pedidos = pedidos;
      this.relatorio = relatorio;
      this.listaContainers = containeres;
      this.meuRelogio = new Horario();
      this.numOp = numOp;
      this.mutex = mutex;
   }

   public void empacotar() throws CloneNotSupportedException, InterruptedException {
      if(pedidos.size() > 0) {
         Pedido pedido = pedidos.pop();

         int qtdProdutos = pedido.getQtdProdutos();

         // START: Transação protegida
         try {
            mutex.acquire();
               boolean containerExiste = listaContainers.exists(pedido.getCodProduto());
               Container cont = listaContainers.getContainer(pedido.getCodProduto());

               if (!containerExiste)
                  this.meuRelogio.addSeconds(cont.getTempoTroca());

               while (qtdProdutos > 0) {
                  int quantidade = cont.getQtdAtualProdutos();
                  int restanteContainer = cont.consumirProdutos(qtdProdutos);
                  int qtdConsumida = quantidade - restanteContainer;
                  qtdProdutos -= qtdConsumida;
                  if (restanteContainer == 0) {
                     cont.reabastecer();
                     this.meuRelogio.addSeconds(cont.getTempoTroca());
                  }
               }
            mutex.release();
         } catch (InterruptedException ie) {
            System.out.println("Thread interrompida");
            throw new InterruptedException();
         }
         // END: Transação protegida

         double tempoEmpacotamento = (TEMPO_EMPACOTAMENTO + TEMPO_ROLAMENTO) * pedido.getQtdPacotes();
         this.meuRelogio.addSeconds(tempoEmpacotamento);

         Horario horarioAtual = new Horario(this.meuRelogio.toSeconds());
         pedido.fecharPedido(horarioAtual, this.meuID);
         relatorio.append(pedido);
      }
   }

   public void run() {
      for(int i=0; i < this.numOp; i++) {
         try {
            this.empacotar();
         } catch (CloneNotSupportedException | InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println("Esteira " + this.meuID + " finalizou!");
   }

}