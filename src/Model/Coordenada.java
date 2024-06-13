package Model;

class Coordenada {
	private int coluna;
	private int linha;

	// Será modificada quando tivermos o tabuleiro da View
	// xClick é a posição horizontal do clique do mouse (coluna)
	// yClick é a posição horizontal do clique do mouse (linha)
	// Para a 1ª iteração esses valores serão o valor exato da matriz (0 - 14)
	public Coordenada(int xClick, int yClick) {
		linha = yClick;
		coluna = xClick;
	}

	public int getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}
}
