package Model;

public class Hidroaviao extends Embarcacao {
	public Hidroaviao() {
		super(3);
	}

	@Override
	public boolean Posicionar(Tabuleiro tabuleiro, Coordenada coordenada) {
		tabuleiro.setCasa(coordenada, this.getTipo());
		coordenada.setLinha(coordenada.getLinha() + 1);
		coordenada.setColuna(coordenada.getColuna() - 1);
		tabuleiro.setCasa(coordenada, this.getTipo());
		coordenada.setColuna(coordenada.getColuna() + 2);
		tabuleiro.setCasa(coordenada, this.getTipo());
		return true;
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
	
	@Override 
	public boolean ValidaPosicionar(Tabuleiro t, Coordenada c) { 
		//Verifica cabeça
		if (!ValidaQuadrado(t,c))
			return false;
		
		//Verifica esquerda
		if (c.getLinha()+1 > 14 && c.getColuna()-1 < 0)
			return false;
		c.setLinha(c.getLinha()+1);
        	c.setColuna(c.getColuna()-1);
        	if (!ValidaQuadrado(t,c))
			return false;
        
        	//Verifica direita
		if (c.getLinha()+1 > 14 && c.getColuna()-1 > 14)
			return false;
	        c.setColuna(c.getColuna()+2);
	        if (!ValidaQuadrado(t,c))
			return false;
        
        	return true; 
	}

}
