package Model;

class Coordenada {
	private int coluna;
	private int linha;

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
