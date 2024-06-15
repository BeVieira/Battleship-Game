package Model;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;
import View.ObservadorIf;

class Tabuleiro  implements Observadoif{
	private int[][] tabuleiro;
	private List<ObservadorIf> lst = new ArrayList<ObservadorIf>();
	
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

	
	public void posicionarEmbarcacao(Coordenada coordenada, Embarcacao embarcacao) {
		int linha = coordenada.getLinha();
		int coluna = coordenada.getColuna();
		int tipo = embarcacao.getTipo();
		
		if (tipo == 3) {
			tabuleiro[linha][coluna] = tipo;
			linha += 1;
			coluna -= 1;
			tabuleiro[linha][coluna] = tipo;
			coluna += 2;
			tabuleiro[linha][coluna] = tipo;
			embarcacao.setPosicao(coordenada);
		}
		else {
			for (int i = 0; i < tipo; i++)
				tabuleiro[linha][coluna + i] = tipo;
			embarcacao.setPosicao(coordenada);
		}
	}
	
	public void removerEmbarcacao(Embarcacao embarcacao) {
		int linha = embarcacao.getPosicao().getLinha();
		int coluna = embarcacao.getPosicao().getColuna();
		int tipo = embarcacao.getTipo();
		int orientacao = embarcacao.getOrientacao();

		if (tipo == 3) {
			removerHidroaviao(embarcacao);
		} else {
			switch (orientacao) {
				//Remocao da orientacao anterior
				case 1:
					for (int i = 0; i < tipo; i++)
						tabuleiro[linha + i][coluna] = 0;
					break;
				case 2:
					for (int i = 0; i < tipo; i++)
						tabuleiro[linha][coluna + i] = 0;
					break;
				case 3:
					for (int i = 0; i < tipo; i++)
						tabuleiro[linha - i][coluna] = 0;
					break;
				case 4:
					for (int i = 0; i < tipo; i++)
						tabuleiro[linha][coluna - i] = 0;
					break;
				}
		}
	}
	
	public void girarEmbarcacao(Embarcacao embarcacao) {
		int tipo = embarcacao.getTipo();
		int orientacao = embarcacao.getOrientacao();
		int linha = embarcacao.getPosicao().getLinha();
		int coluna = embarcacao.getPosicao().getColuna();
		
		removerEmbarcacao(embarcacao);
		
		if (tipo == 3) {
			girarHidroaviao(embarcacao);
		}
		else {
			switch (orientacao) {
			case 1:
				for (int i = 0; i < tipo; i++)
					tabuleiro[linha][coluna + i] = tipo;
				break;
			case 2:
				for (int i = 0; i < tipo; i++)
					tabuleiro[linha - i][coluna] = tipo;
				break;
			case 3:
				for (int i = 0; i < tipo; i++)
					tabuleiro[linha][coluna - i] = tipo;
				break;
			case 4:
				for (int i = 0; i < tipo; i++)
					tabuleiro[linha + i][coluna] = tipo;
				break;
			}	
		}
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
			//Remocao da orientacao anterior
			case 1:
				tabuleiro[linha - 1][coluna - 1] = 0;
				break;
			case 2:
				tabuleiro[linha + 1][coluna - 1] = 0;
				break;
			case 3:
				tabuleiro[linha + 1][coluna + 1] = 0;
				break;
			case 4:
				tabuleiro[linha - 1][coluna + 1] = 0;
				break;
			}
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
	
	public boolean validaPosicionarHidroaviao(Coordenada coordenada) { 
		int linha = coordenada.getLinha();
        int coluna = coordenada.getColuna();

		if (coluna == 0 || coluna == 14 || linha == 14)
			return false;
		
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

	@Override
	public void add(ObservadorIf observador) {
		lst.add(observador);
		
	}

	@Override
	public void get() {
		atualiza();
		
	}
	
	public void atualiza()
    {
        ListIterator<ObservadorIf> li = lst.listIterator();

        while(li.hasNext()) {
            li.next().notify(this);
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