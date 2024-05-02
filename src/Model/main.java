//d/Visual Studio Projects/Eclipse/2220502_2212759_2210814
package Model;

public class main {

	public static void main(String[] args) {
		Jogador P1 = new Jogador("P1");
		P1.tabuleiro.setCasa(new Coordenada(6,0), 1);
		P1.InserirEmbarcacao(new Coordenada(4,1), new Couracado());
		P1.tabuleiro.ExibeTabuleiro();
	}
}
