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
	public boolean ValidaPosicionar(Tabuleiro t, Coordenada c) {
		int tam = this.getTipo();
		
		Coordenada aux = new Coordenada(c.getColuna(), c.getLinha());
		int coluna_aux = aux.getColuna();
		int linha_aux = aux.getLinha();
		
		//testar a coordenada anterior
		aux.setColuna(coluna_aux-1);
		if (t.getCasa(aux) == 0) {
			//se a anterior tiver ok entra aqui e vai validar a parte toda de cima
			aux.setLinha(linha_aux-1);
			aux.setColuna(coluna_aux+1);
			//Voltei a coordenada original
			for (int i = 0; i < tam; i++) {
				if (t.getCasa(aux) != 0)
					return false;
				aux.setColuna(coluna_aux+i);
			}
		}
			//parte de cima ok testando a de baixo agr
			aux.setColuna(coluna_aux);
			aux.setLinha(linha_aux+1);
			if (t.getCasa(aux) == 0) {
				for (int i = 0; i < tam;  i++) {
					if (t.getCasa(aux) != 0)
						return false;
					aux.setColuna(coluna_aux+i);
				}
			}
			return true;
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
