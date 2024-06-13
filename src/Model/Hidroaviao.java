package Model;

class Hidroaviao extends Embarcacao {
	public Hidroaviao() {
		super(3);
	}

	@Override
	public boolean Posicionar(Tabuleiro tabuleiro, Coordenada coordenada) {
		if (ValidaPosicionar(tabuleiro, coordenada)) {
			tabuleiro.setCasa(coordenada, this.getTipo());
			coordenada.setLinha(coordenada.getLinha() + 1);
			coordenada.setColuna(coordenada.getColuna() - 1);
			tabuleiro.setCasa(coordenada, this.getTipo());
			coordenada.setColuna(coordenada.getColuna() + 2);
			tabuleiro.setCasa(coordenada, this.getTipo());
			return true;
		}
		return false;
	}
	
	
	private boolean ValidaQuadrado(Tabuleiro t, Coordenada c) {
		int linha = c.getLinha();
        	int coluna = c.getColuna();
		for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int novaLinha = linha + i;
                int novaColuna = coluna + j;
                if (novaLinha >= 0 && novaLinha <= 14 && novaColuna >= 0 && novaColuna <= 14) {
                    Coordenada novaC = new Coordenada(0,0);
                    novaC.setLinha(novaLinha);
                    novaC.setColuna(novaColuna);
                	if (t.getCasa(novaC) != 0)
                        return false;
                }
            }
    	}
		return true;
	}
	
	public boolean ValidaPosicionar(Tabuleiro t, Coordenada c) { 
		Coordenada cAux = new Coordenada(c.getColuna(), c.getLinha());

		if (c.getColuna() == 0 || c.getColuna() == 14 || c.getLinha() == 14)
			return false;
		
		//Verifica cabeça
		if (!ValidaQuadrado(t,cAux))
			return false;
		
		//Verifica esquerda
		if (cAux.getLinha()+1 > 14 && cAux.getColuna()-1 < 0)
			return false;
	
		cAux.setLinha(cAux.getLinha()+1);
        cAux.setColuna(cAux.getColuna()-1);
        if (!ValidaQuadrado(t,cAux))
			return false;
        
        //Verifica direita
		if (cAux.getLinha()+1 > 14 && cAux.getColuna()-1 > 14)
			return false;
				
		cAux.setLinha(cAux.getLinha()+1);
        cAux.setColuna(cAux.getColuna()-1);
        if (!ValidaQuadrado(t,cAux))
			return false;
        
        return true; 
	}

}
