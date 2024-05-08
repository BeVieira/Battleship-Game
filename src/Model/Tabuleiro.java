package Model;

public class Tabuleiro {
	protected int[][] tabuleiro;

	public int getCasa(Coordenada coordenada) {
		return tabuleiro[coordenada.getLinha()][coordenada.getColuna()];
	}

	public void setCasa(Coordenada coordenada, int tipoEmbarcacao) {
		this.tabuleiro[coordenada.getLinha()][coordenada.getColuna()] = tipoEmbarcacao;
	}

	public Tabuleiro() {
		tabuleiro = new int[15][15];
	}
}
