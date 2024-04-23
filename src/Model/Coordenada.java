package Model;

public class Coordenada {
	private int coluna;
	private int linha;
	
	public Coordenada(String coordenada) {
		linha = coordenada.substring(0,1).toUpperCase()coordenada.substring(0,1).toUpperCase().charAt(0)-65;
		coluna = Integer.parseInt(coordenada.substring(1,coordenada.length()))-1;
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
