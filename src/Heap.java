import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Estrutura HeapMin Ordena os dados de forma em que o menor fica sempre como nó
 * principal
 */
public class Heap {

   private ArrayList<Pedido> itens;

   public Heap() {
      itens = new ArrayList<>();
   }

   /**
    * SiftUp Estrutura tem a função de manter os dados sempre ordenados
    */
   private void siftUp() {
      int k = itens.size() - 1;
      while (k > 0) {
         int p = (k - 1) / 2;
         Pedido item = itens.get(k);
         Pedido pai = itens.get(p);
         if (compare(item, pai) < 0) {
            itens.set(k, pai);
            itens.set(p, item);

            k = p;
         } else {
            break;
         }
      }
   }

   public void insere(Pedido item) {
      itens.add(item);
      siftUp();
   }

   /**
    * siftDown Estrutura usada na retirada para manter sempre a Heap ordenada
    */
   private void siftDown() {
      int k = 0;
      int l = 2 * k + 1;

      while (l < itens.size()) {
         int max = l, r = l + 1;

         if (r < itens.size()) {
            if (compare(itens.get(r), itens.get(l)) < 0) {
               max++;
            }
         }
         if (compare(itens.get(k), itens.get(max)) > 0) {
            Pedido aux = itens.get(k);
            itens.set(k, itens.get(max));
            itens.set(max, aux);
            k = max;
            l = 2 * k + 1;
         } else {
            break;
         }
      }

   }

   /**
    * Retorna sempre o primeiro item da Heap já o excluindo da árvore
    */
   public Pedido retorno() throws NoSuchElementException {
      if (itens.size() == 0) {
         throw new NoSuchElementException();
      }
      if (itens.size() == 1) {
         return itens.remove(0);
      }
      Pedido hold = itens.get(0);

      itens.set(0, itens.remove(itens.size() - 1));

      siftDown();

      return hold;

   }

   private int compare(Pedido item, Pedido item2) {
      String aux = item.data.substring(6);
      aux = aux.concat(item.data.substring(3, 5));
      aux = aux.concat(item.data.substring(0, 2));

      String aux2 = item2.data.substring(6);
      aux2 = aux2.concat(item2.data.substring(3, 5));
      aux2 = aux2.concat(item2.data.substring(0, 2));

      int data1 = Integer.parseInt(aux);
      int data2 = Integer.parseInt(aux2);

      return data1 - data2;
   }

   public int size() {
      return itens.size();
   }
}