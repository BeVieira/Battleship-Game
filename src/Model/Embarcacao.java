package Model;

public class Embarcacao {
	private int tipo;
	
	public Embarcacao(int tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}
	
	//TO-DO validar posiçao da embarcaçao (Override no Hidroaviao)
	public boolean Posicionar(Tabuleiro tabuleiro, Coordenada coordenada, String orientacao) {
		int colunaAux = coordenada.getColuna();
		for (int i = 0; i < this.getTipo(); i++) {
			coordenada.setColuna(colunaAux+i);
			tabuleiro.setCasa(coordenada, this.getTipo());
		}
		return true;
	}

}
