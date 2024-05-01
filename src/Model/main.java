//d/Visual Studio Projects/Eclipse/2220502_2212759_2210814
package Model;

public class main {

	public static void main(String[] args) {
		Jogador P1 = new Jogador("P1");
		P1.InserirEmbarcacao("A1", new Couracado());
		Jogador P2 = new Jogador("P2");
		P2.tabuleiroAlvo = P1.tabuleiro;
		P2.Atirar(new Coordenada("A5"));
		System.out.println("P1: ");
		P1.tabuleiro.ExibeTabuleiro();
		System.out.println("P2: ");
		P2.tabuleiroAlvo.ExibeTabuleiro();
	}
}
