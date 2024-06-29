package Model;

import java.util.ArrayList;

public class ModelFacade{
	static ModelFacade f = null;
	private Tabuleiro tabuleiroJ1, tabuleiroJ2;
	private Jogador jogador1, jogador2;
	private Coordenada coordenada;
	private Embarcacao posicionando;

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
	
	public int getEmbarcacaoNum(int tipo, int turno) {
		// Se tipo = 0 retorna o total
		ArrayList<Embarcacao> navios = getEmbarcacoes(turno);
		if (tipo == 0) return navios.size();
		int result = 0;
		for (int i = 0; i < navios.size(); i++) {
			if (navios.get(i).getTipo() == tipo) result++;
		}
		return result;
	}

	public void posicionarEmbarcacao(int tipo, int turno) {
		if (turno == 1) {
			Embarcacao embarcacao = jogador1.retiraNavio(tipo);
			embarcacao.setPosicao(posicionando.getPosicao());
			embarcacao.setOrientacao(posicionando.getOrientacao());
			jogador1.inserirEmbarcacao(this.coordenada, embarcacao);	
		}
		else {
			Embarcacao embarcacao = jogador2.retiraNavio(tipo);
			embarcacao.setPosicao(posicionando.getPosicao());
			embarcacao.setOrientacao(posicionando.getOrientacao());
			jogador2.inserirEmbarcacao(this.coordenada, embarcacao);	
		}
	}
	
	public boolean definirGhost(int tipo, int turno) {
		posicionando = new Embarcacao(tipo);
		posicionando.setPosicao(coordenada);
		return isPosicaoValida(tipo, turno);
	}
	
	public int[] getGhostPosition() {
		int coord[] = {posicionando.getPosicao().getColuna(), posicionando.getPosicao().getLinha()};
		return coord;
	}
	
	public int getGhostOrientation() {
		return posicionando.getOrientacao();
	}
	
	public int getGhostType() {
		return posicionando.getTipo();
	}
	
	public boolean setGhostOrientation(int orientacao, int turno) {
		posicionando.setOrientacao(orientacao);
		return this.isPosicaoValida(posicionando.getTipo(), turno);
	}

	public boolean isPosicaoValida(int tipo, int turno) {
		int orientacao = posicionando.getOrientacao();
		if (turno == 1) {
			if(tipo == 3) {
				return jogador1.getTabuleiro().validaPosicionarHidroaviao(this.coordenada,orientacao);
			}
			return jogador1.getTabuleiro().validaPosicionar(this.coordenada, tipo, orientacao);
		}
		else {
			if(tipo == 3) {
				return jogador2.getTabuleiro().validaPosicionarHidroaviao(this.coordenada,orientacao);
			}
			return jogador2.getTabuleiro().validaPosicionar(this.coordenada, tipo, orientacao);
		}
	}

	public void definirCoordenada(int x, int y) {
		this.coordenada.setColuna(x);
		this.coordenada.setLinha(y);
	}
	
	public int getCasa (int turno, int coluna, int linha) {
		Coordenada coordenada = new Coordenada(coluna,linha);
		if (turno == 1)
			return jogador1.getTabuleiro().getCasa(coordenada);
		return jogador2.getTabuleiro().getCasa(coordenada);
	}
	public void setCasa (int turno, int coluna, int linha, int valor) {
		Coordenada coordenada = new Coordenada(coluna,linha);
		if (turno == 1)
			jogador1.getTabuleiro().setCasa(coordenada,valor);
		else
		jogador2.getTabuleiro().setCasa(coordenada,valor);;
	}

	public String getNome(int turno) {
		if (turno == 1)
			return jogador1.getNome();
		return jogador2.getNome();
	}
	
	public int atirar(int turno) {
		if (turno == 1)
			return jogador1.realizarTiro(coordenada);
		return jogador2.realizarTiro(coordenada);
	}

}