package Model;

public class main {

	public static void main(String[] args) {
		Jogador k = new Jogador("P1");
		k.InserirEmbarcacao("J4", new Destroyer());
		k.tabuleiro.ExibeTabuleiro();
	}

}
