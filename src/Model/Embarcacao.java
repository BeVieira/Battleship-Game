package Model;

import java.util.ArrayList;

class Embarcacao {
	private int tipo;
	private int orientacao;
	private Coordenada posicao;
	protected ArrayList<Coordenada> pontos;

	public Embarcacao(int tipo) {
		this.tipo = tipo;
		this.orientacao = 1;
	}

	public int getTipo() {
		return tipo;
	}

	public int getOrientacao() {
		return orientacao;
	}

	public void setOrientacao(int orientacao) {
		this.orientacao = orientacao;
	}

	public Coordenada getPosicao() {
		return posicao;
	}

	public void setPosicao(Coordenada posicao) {
		this.posicao = posicao;
	}
}
