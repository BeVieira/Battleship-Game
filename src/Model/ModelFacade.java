package Model;

import java.util.ArrayList;

public class ModelFacade{
	static ModelFacade f = null;
	private Tabuleiro tabuleiroJ1, tabuleiroJ2;
	private Jogador jogador1, jogador2;
	private Coordenada coordenada;

	private ModelFacade() {
		this.coordenada = new Coordenada(0, 0);
	}
	
	public void inicializaJogadores(String nomeJ1, String nomeJ2) {
		jogador1 = new Jogador();
		jogador2 = new Jogador();
		
		tabuleiroJ1 = new Tabuleiro();
		tabuleiroJ2 = new Tabuleiro();
		
		jogador1.setNome(nomeJ1);
		jogador2.setNome(nomeJ2);
		
		jogador1.setTabuleiro(tabuleiroJ1);
		jogador2.setTabuleiro(tabuleiroJ2);
		
		jogador1.setTabuleiroAlvo(tabuleiroJ2);
		jogador2.setTabuleiroAlvo(tabuleiroJ1);
	}

	public static ModelFacade getFacade() {
		if (f == null)
			f = new ModelFacade();
		return f;
	}

	public ArrayList<Embarcacao> getEmbarcacoes(int turno) {
		if (turno == 1)
			return jogador1.getNavios();
		return jogador2.getNavios();
	}

	public void posicionarEmbarcacao(int tipo, int turno) {
		if (turno == 1) {
			Embarcacao embarcacao = jogador1.retiraNavio(tipo);
			jogador1.inserirEmbarcacao(this.coordenada, embarcacao);	
		}
		else {
			Embarcacao embarcacao = jogador2.retiraNavio(tipo);
			jogador2.inserirEmbarcacao(this.coordenada, embarcacao);	
		}
	}

	public boolean isPosicaoValida(int tipo) {
		//Incompleto
		return jogador1.getTabuleiro().validaPosicionar(this.coordenada, tipo);
	}

	public void definirCoordenada(int x, int y) {
		//Incompleto
		this.coordenada.setColuna(x);
		this.coordenada.setLinha(y);
	}

	public int[][] getTabuleiro(int turno) {
		if (turno == 1)
			return jogador1.getTabuleiro().getTabuleiroEstado();
		return jogador2.getTabuleiro().getTabuleiroEstado();
	}

	public String getNome(int turno) {
		if (turno == 1)
			return jogador1.getNome();
		return jogador2.getNome();
	}

}