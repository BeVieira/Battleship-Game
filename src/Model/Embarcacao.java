package Model;

public class Embarcacao {
	private int tipo;

	public Embarcacao(int tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}

	public boolean ValidaPosicionar(Tabuleiro t, Coordenada c) {
	        int linha = c.getLinha();
	        int coluna = c.getColuna();
	        Coordenada novaC = new Coordenada(0,0);
	        if (coluna + this.getTipo() > 15)
	        	return false;
	        for (int n = 0; n < this.getTipo(); n++) {
	        	for (int i = -1; i <= 1; i++) {
	                	for (int j = -1; j <= 1; j++) {
	                    		int novaLinha = linha + i;
	                    		int novaColuna = coluna + j;
	                    		if (novaLinha >= 0 && novaLinha <= 14 && novaColuna >= 0 && novaColuna <= 14) {
			                        novaC.setLinha(novaLinha);
			                        novaC.setColuna(novaColuna);
			                    	if (t.getCasa(novaC) != 0)
			                            return false;
	                    		}
	                	}
	        	linha += n;
	        	}
	        }
	        return true; 
    	}

	public boolean Posicionar(Tabuleiro tabuleiro, Coordenada coordenada) {
		int colunaAux = coordenada.getColuna();
		int tamanho = this.getTipo();
		if (ValidaPosicionar(tabuleiro, coordenada)) {
			for (int i = 0; i < tamanho; i++) {
				coordenada.setColuna(colunaAux + i);
				tabuleiro.setCasa(coordenada, this.getTipo());
			}
			return true;
		}
		return false;
	}

}
