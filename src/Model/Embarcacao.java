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
	public boolean validaAdjacente(Coordenada c) {
		int coluna = c.getColuna();
		int linha =  c.getLinha();
		String str = c.getString();
		Coordenada aux = new Coordenada(str);
		Tabuleiro t = new Tabuleiro();
		//to considerando que ser for diferente de 0 tem alguma embarcaçao na posiçao
		//lado direito do centro
		aux.setColuna(coluna+1);
		if(t.getCasa(aux) != 0) {
			return false;
		}
		//lado esquerdo do centro
		aux.setColuna(coluna-1);
		if(t.getCasa(aux) != 0) {
			return false;
		}
		//em baixo do centro
		aux.setLinha(linha-1);
		if(t.getCasa(aux) != 0) {
			return false;
		}
		//em cima do centro
		aux.setLinha(linha+1);
		if(t.getCasa(aux) != 0) {
			return false;
		}
		//em cima a direita
		aux.setColuna(coluna+1);
		aux.setLinha(linha-1);
		if(t.getCasa(aux) != 0) {
			return false;
		}
		//em cima a esquerda
		aux.setColuna(coluna-1);
		aux.setLinha(linha-1);
		if(t.getCasa(aux) != 0) {
			return false;
		}
		//em baixo a esquerda
		aux.setColuna(coluna-1);
		aux.setLinha(linha+1);
		if(t.getCasa(aux) != 0) {
			return false;
		}
		//em baixo a direita
		aux.setColuna(coluna+1);
		aux.setLinha(linha+1);
		if(t.getCasa(aux) != 0) {
			return false;
		}
		return true;
	}

	public boolean validaPosicao(Coordenada c) {
		Tabuleiro t = new Tabuleiro();
		if(t.getCasa(c) == 0 && validaAdjacente(c) ) {
			return true;
		}
		return false;
	}
	
	public boolean Posicionar(Tabuleiro tabuleiro, Coordenada coordenada) {
		int colunaAux = coordenada.getColuna();
		for (int i = 0; i < this.getTipo(); i++) {
			coordenada.setColuna(colunaAux+i);
			tabuleiro.setCasa(coordenada, this.getTipo());
		}
		return true;
	}

}
