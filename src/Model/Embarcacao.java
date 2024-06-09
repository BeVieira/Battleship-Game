package Model;

public class Embarcacao {
	private int tipo;

	public Embarcacao(int tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}

	public boolean ValidaCasa(Tabuleiro t, Coordenada c) {
		return t.getCasa(c) == 0;
	}

	public boolean ValidaLinha(Tabuleiro t, Coordenada c) {
		int colunaOriginal = c.getColuna();
		if (c.getLinha() + this.getTipo() > 14) {
			c.setColuna(colunaOriginal);
			return false;
		}
		for (int i = 0, colunaTemp = c.getColuna(); i < this.getTipo(); i++) {
			c.setColuna(colunaTemp + i);
			if (t.getCasa(c) != 0) {
				c.setColuna(colunaOriginal);
				return false;
			}
		}
		c.setColuna(colunaOriginal);
		return true;
	}

	public boolean ValidaPosicionar(Tabuleiro t, Coordenada c) {
		int tam = this.getTipo();
		Coordenada aux = new Coordenada(c.getColuna(), c.getLinha());
		int colunaOriginal = aux.getColuna();
		int colunaAnterior = colunaOriginal - 1;
		int colunaPosterior = colunaOriginal + tam;

		int linhaOriginal = aux.getLinha();
		int linhaAnterior = linhaOriginal - 1;
		int linhaSeguinte = linhaOriginal + 1;

		// Caso casas onde a embarcação será inserida estar ocupada
		for (int i = 0, colunaTemp = c.getColuna(); i < this.getTipo(); i++) {
			aux.setColuna(colunaTemp + i);
			if (t.getCasa(c) != 0)
				return false;
		}
		aux.setColuna(colunaOriginal);

		// Caso selecione casa ocupada (por tiro ou embarcação)
		if (t.getCasa(c) != 0)
			return false;

		// Caso adjacência
		// Linha A
		if (linhaOriginal == 0) {
			// Valida Abaixo
			aux.setLinha(linhaSeguinte);
			if (!ValidaLinha(t, aux))
				return false;
			aux.setColuna(colunaOriginal);
			aux.setLinha(linhaOriginal);

			// Coluna 1
			if (colunaOriginal == 0) {
				// Valida Depois
				aux.setColuna(colunaPosterior);
				if (!ValidaCasa(t, aux))
					return false;
			}

			// Coluna 15
			else if ((colunaOriginal + tam) >= 14) {
				// Valida Antes
				aux.setColuna(colunaAnterior);
				if (!ValidaCasa(t, aux))
					return false;
			} else {
				// Valida Antes
				aux.setColuna(colunaAnterior);
				if (!ValidaCasa(t, aux))
					return false;
				// Valida Depois
				aux.setColuna(colunaPosterior);
				if (!ValidaCasa(t, aux))
					return false;
			}
		}
		// Linha O
		else if (linhaOriginal == 14) {
			// Valida Acima
			aux.setLinha(linhaAnterior);
			if (!ValidaLinha(t, aux))
				return false;
			aux.setLinha(linhaOriginal);

			// Coluna 1
			if (colunaOriginal == 0) {
				// Valida Depois
				aux.setColuna(colunaPosterior);
				if (!ValidaCasa(t, aux))
					return false;
			}
			// Coluna 15
			else if ((colunaOriginal + tam) >= 14) {
				// Valida Antes
				aux.setColuna(colunaAnterior);
				if (!ValidaCasa(t, aux))
					return false;
			} else {
				// Valida Antes
				aux.setColuna(colunaAnterior);
				if (!ValidaCasa(t, aux))
					return false;
				// Valida Depois
				aux.setColuna(colunaPosterior);
				if (!ValidaCasa(t, aux))
					return false;
			}
		} else {
			// Valida Acima
			aux.setLinha(linhaAnterior);
			if (!ValidaLinha(t, aux))
				return false;
			aux.setLinha(linhaSeguinte);
			if (!ValidaLinha(t, aux))
				return false;
			aux.setLinha(linhaOriginal);
			// Valida Antes
			aux.setColuna(colunaAnterior);
			if (!ValidaCasa(t, aux))
				return false;
			// Valida Depois
			aux.setColuna(colunaPosterior);
			if (!ValidaCasa(t, aux))
				return false;
		}
		return true;
	}

	public boolean Posicionar(Tabuleiro tabuleiro, Coordenada coordenada) {
		int colunaAux = coordenada.getColuna();
		int tamanho = this.getTipo();
		if ((coordenada.getColuna()+tamanho)<=14) {
			if (ValidaPosicionar(tabuleiro, coordenada)) {
				for (int i = 0; i < tamanho; i++) {
					coordenada.setColuna(colunaAux + i);
					tabuleiro.setCasa(coordenada, this.getTipo());
				}
				return true;
			}
		}
		return false;
	}

}
