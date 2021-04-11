import java.io.*;
import java.util.ArrayList;

public class Leitor {
   private File dadosEmpacota;

   public ArrayList lerArquivo(String nomeArquivo) throws IOException {                                        // Colocar nos atributos o nome do .txt. Deve estar na pasta mãe.
      dadosEmpacota = new File(nomeArquivo);
      
      ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
      Pedido aux;
      String[] divisao;

      BufferedReader br = new BufferedReader(new FileReader(dadosEmpacota));
		
		for (String linha; (linha = br.readLine()) != null;) { 							                              // Cada linha é lida, transformada em pedido e adicionada na ArrayList
			if (!linha.equals("")) {                                                                              // Se a linha estiver vazia, é ignorada
            divisao = linha.split(";");                                                                        
            if (divisao.length == 3){                                                                          // Se a linha não estiver no padrão de 3 categorias separadas por ";", ela é ignorada
               aux = new Pedido(divisao[0], Integer.parseInt(divisao[1]), Integer.parseInt(divisao[2]));       // Inicializa o auxiliar de Pedidos com os itens da linha
               pedidos.add(aux);                                                                               // Adiciona o pedido no ArrayList
            }
			}
		}
      return pedidos;
   }
}
