import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Estrutura HeapMin Ordena os dados de forma em que o menor fica sempre como nó
 * principal
 */
public class HeapPrazo {
   private ArrayList<HeapQtd> itens;

   public HeapPrazo() {
      itens = new ArrayList<HeapQtd>();
   }

   /**
    * SiftUp Estrutura usada na inserção da heap, tem a função de manter os dados
    * sempre ordenados
    */
   private void siftUp() {
      int indexNovoItem = itens.size() - 1; // variável recebe o índice do novo item inserido da heap
      while (indexNovoItem > 0) {
         int indexPai = (indexNovoItem - 1) / 2; // variável recebe o índice do nó pai do novo item
         HeapQtd item = itens.get(indexNovoItem);
         HeapQtd pai = itens.get(indexPai);
         if (compare(item, pai) < 0) { // se novo item for menor que o pai, trocam de lugar
            itens.set(indexNovoItem, pai);
            itens.set(indexPai, item);

            indexNovoItem = indexPai;
         }

         if (compare(item, pai) == 0) {
            int tamanho = item.size();
            while (tamanho > 0) {
               pai.insere(item.retorno());
               if (item.size() == 0)
                  itens.remove(item);
               tamanho--;
            }
            indexNovoItem = 0;
         }

         else {
            break;
         }
      }

   }

   public void insereNovo(Pedido item) {
      HeapQtd pedidosQtd = new HeapQtd(item.getPrazo());
      pedidosQtd.insere(item);
      itens.add(pedidosQtd);
      siftUp();
   }

   public void insere() {

   }

   /**
    * siftDown Estrutura usada na retirada para manter sempre a Heap ordenada
    */
   private void siftDown() {
      int indexPai = 0; // recebe o índice do nó principal
      int indexFilho1 = 2 * indexPai + 1; // recebe o índice do nó filho

      while (indexFilho1 < itens.size()) {
         int max = indexFilho1;
         int indexFilho2 = indexFilho1 + 1;

         if (indexFilho2 < itens.size()) {
            if (compare(itens.get(indexFilho2), itens.get(indexFilho1)) < 0) {
               max++;
            }
         }
         if (compare(itens.get(indexPai), itens.get(max)) > 0) {
            HeapQtd aux = itens.get(indexPai);
            itens.set(indexPai, itens.get(max));
            itens.set(max, aux);
            indexPai = max;
            indexFilho1 = 2 * indexPai + 1;
         } else {
            break;
         }
      }

   }

   /**
    * Retorna sempre o primeiro item da Heap já o excluindo da árvore
    */
   public HeapQtd retorno() throws NoSuchElementException {
      if (itens.size() == 0) {
         throw new NoSuchElementException();
      }
      if (itens.size() == 1) {
         return itens.remove(0);
      }
      HeapQtd hold = itens.get(0);

      itens.set(0, itens.remove(itens.size() - 1));

      siftDown();

      return hold;

   }

   /**
    * Método de comparação entre dois itens
    * 
    * @param item
    * @param item2
    * @return
    */
   private int compare(HeapQtd item, HeapQtd item2) {
      int aux = item.getPrazo();
      int aux2 = item2.getPrazo();

      return aux - aux2;
   }

   public int size() {
      return itens.size();
   }
}