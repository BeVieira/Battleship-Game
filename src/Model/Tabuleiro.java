package Model;
import java.util.ArrayList;

import Observer.Observer;
import Observer.Subject;

class Tabuleiro implements Subject{
	private int[][] tabuleiro;
	private ArrayList<Observer> observadores;
	
	public Tabuleiro() {
		tabuleiro = new int[15][15];
	}
	
	public int[][] getTabuleiroEstado() {
		return tabuleiro;
	}
	
	public void setTabuleiro(int[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public int getCasa(Coordenada coordenada) {
		return tabuleiro[coordenada.getLinha()][coordenada.getColuna()];
	}

	public void setCasa(Coordenada coordenada, int tipoEmbarcacao) {
		this.tabuleiro[coordenada.getLinha()][coordenada.getColuna()] = tipoEmbarcacao;
	}
	
	public void posicionarEmbarcacao(Embarcacao embarcacao, int orientacao) {
		int linha = embarcacao.getPosicao().getLinha();
		int coluna = embarcacao.getPosicao().getColuna();
		int tipo = embarcacao.getTipo();
		
		if (tipo == 3) {
			switch(orientacao) {
			case 1:
				System.out.println("case 0");
				tabuleiro[linha][coluna] = tipo;
				linha += 1;
				coluna -= 1;
				tabuleiro[linha][coluna] = tipo;
				coluna += 2;
				tabuleiro[linha][coluna] = tipo;
				break;
			case 2:
				System.out.println("case 1");
				tabuleiro[linha][coluna] = tipo;
				linha -= 1;
				coluna += 1;
				tabuleiro[linha][coluna] = tipo;
				linha += 2;
				tabuleiro[linha][coluna] = tipo;
				break;
				
			}
			
		}
		else {
			for (int i = 0; i < tipo; i++)
				tabuleiro[linha][coluna + i] = tipo;
			embarcacao.setPosicao(embarcacao.getPosicao());
		}
		//notificarObservadores();
	}
	
	public void removerEmbarcacao(Embarcacao embarcacao) {
		int linha = embarcacao.getPosicao().getLinha();
		int coluna = embarcacao.getPosicao().getColuna();
		int tipo = embarcacao.getTipo();
		int orientacao = embarcacao.getOrientacao();

		if (tipo == 3) {
			removerHidroaviao(embarcacao);
		} else {
			for (int i = 0; i < tipo; i++) {
				switch (orientacao) {
				case 1:
					tabuleiro[linha][coluna + i] = 0;
					break;
				case 2:
					tabuleiro[linha - i][coluna] = 0;
					break;
				case 3:
					tabuleiro[linha][coluna - i] = 0;
					break;
				case 4:
					tabuleiro[linha + i][coluna] = 0;
					break;
				}
			}
		}
	}
	
	public void girarEmbarcacao(Embarcacao embarcacao) {
		int tipo = embarcacao.getTipo();
		int orientacao = embarcacao.getOrientacao();
		int linha = embarcacao.getPosicao().getLinha();
		int coluna = embarcacao.getPosicao().getColuna();
		
		removerEmbarcacao(embarcacao);
		orientacao = alterarOrientacao(orientacao);
		if (tipo == 3) {
			girarHidroaviao(embarcacao);
		}
		else {
			for (int i = 0; i < tipo; i++) {
				switch (orientacao) {
				case 1:
					tabuleiro[linha][coluna + i] = tipo;
					break;
				case 2:
					tabuleiro[linha - i][coluna] = tipo;
					break;
				case 3:
					tabuleiro[linha][coluna - i] = tipo;
					break;
				case 4:
					tabuleiro[linha + i][coluna] = tipo;
					break;
				}
			}	
		}
		embarcacao.setOrientacao(orientacao);
		//notificarObservadores();
	}
	
	public boolean validaPosicionar(Coordenada coordenada, int tipo) {
		int linha = coordenada.getLinha();
        int coluna = coordenada.getColuna();
        
        if (coluna + tipo > 15)
        	return false;
        
        for (int i = 0; i < tipo; i++) {
        	if (!validaQuadrado(linha, coluna + i))
        		return false;
        }
        return true; 
	}
	
	public boolean validaPosicionarHidroaviao(Coordenada coordenada, int orientacao) { 
		int linha = coordenada.getLinha();
        int coluna = coordenada.getColuna();

		if (coluna == 0|| coluna == 14 || linha == 14){
			System.out.println("orientaçao valida " +orientacao);
			System.out.println("coluna valida " + coluna);
			if(coluna == 0 && orientacao == 1) {
				return true;
			}
			if(coluna == 14 && orientacao == 4) {
				return true;
			}
			return false;
		}
		//Verifica cabeça
		if (!validaQuadrado(linha, coluna))
			return false;
		
		//Verifica esquerda
		linha+=1;
		coluna=-1;
        if (!validaQuadrado(linha, coluna))
			return false;
        
        //Verifica direita
        coluna += 2;
        if (!validaQuadrado(linha, coluna))
			return false;
        
        return true; 
	}	

	private void girarHidroaviao(Embarcacao embarcacao) {
		int tipo = embarcacao.getTipo();
		int orientacao = embarcacao.getOrientacao();
		int linha = embarcacao.getPosicao().getLinha();
		int coluna = embarcacao.getPosicao().getColuna();
	
		
		switch (orientacao) {
		case 1:
			tabuleiro[linha + 1][coluna + 1] = tipo;
			break;
		case 2:
			tabuleiro[linha - 1][coluna + 1] = tipo;
			break;
		case 3:
			tabuleiro[linha - 1][coluna - 1] = tipo;
			break;
		case 4:
			tabuleiro[linha + 1][coluna - 1] = tipo;
			break;
		}

		
	}

	private void removerHidroaviao(Embarcacao embarcacao) {
		int orientacao = embarcacao.getOrientacao();
		int linha = embarcacao.getPosicao().getLinha();
		int coluna = embarcacao.getPosicao().getColuna();
		
		switch (orientacao) {
			case 1:
				tabuleiro[linha + 1][coluna - 1] = 0;
				break;
			case 2:
				tabuleiro[linha + 1][coluna + 1] = 0;
				break;
			case 3:
				tabuleiro[linha - 1][coluna + 1] = 0;
				break;
			case 4:
				tabuleiro[linha - 1][coluna - 1] = 0;
				break;
			}
	}
		
	private int alterarOrientacao(int orientacao) {
		return (orientacao % 4 + 1);
	}
	
	private boolean validaQuadrado(int linha, int coluna) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int novaLinha = linha + i;
				int novaColuna = coluna + j;
				if (novaLinha >= 0 && novaLinha <= 14 && novaColuna >= 0 && novaColuna <= 14)
					if (tabuleiro[novaLinha][novaColuna] != 0)
						return false;
			}
		}
		return true;
	}
	
	@Override
    public void registrarObservador(Observer observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(Observer observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for (Observer observador : observadores) {
            observador.atualizar();
        }
    }
	
	public void exibeTabuleiro() {
		System.out.println("  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4");
		for (int i = 0; i < 15; i++) {
			System.out.print(String.format("%d ", i>= 10 ? i-10 : i));
			for (int j = 0; j < 15; j++) {
				System.out.print(tabuleiro[i][j] == 0 ? "- " : tabuleiro[i][j] + " ");
			}
			System.out.println();
		}
	}
}
