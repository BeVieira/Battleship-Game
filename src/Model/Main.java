package Model;

public class Main {

	public static void main(String[] args) {
		Tabuleiro t = new Tabuleiro();
		Couracado x = new Couracado();
		Jogador j = new Jogador();	
		j.setTabuleiro(t);
		j.inserirEmbarcacao(new Coordenada(7,7), x);
		j.getTabuleiro().exibeTabuleiro();
		j.girarEmbarcacao(x);
		j.getTabuleiro().exibeTabuleiro();
		j.girarEmbarcacao(x);
		j.getTabuleiro().exibeTabuleiro();
		j.girarEmbarcacao(x);
		j.getTabuleiro().exibeTabuleiro();
		j.girarEmbarcacao(x);
		j.getTabuleiro().exibeTabuleiro();
	}

}
