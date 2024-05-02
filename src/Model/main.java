package Model;

public class main {
	public static void main(String[] args) {
		Jogador P1 = new Jogador("P1");
		//P1.tabuleiro.setCasa(new Coordenada(6,0), 1);
		P1.InserirEmbarcacao(new Coordenada(4,1), new Couracado());
		P1.InserirEmbarcacao(new Coordenada(4,1), new Cruzador());
		P1.tabuleiro.ExibeTabuleiro();
	}
}
