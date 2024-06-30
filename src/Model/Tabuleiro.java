package Model;

import java.util.ArrayList;

import Observer.Observer;
import Observer.Subject;

class Tabuleiro implements Subject {
	private int[][] tabuleiro;
	private ArrayList<Observer> observadores;
	private int embarcacoesAfundadas;

	public Tabuleiro() {
		tabuleiro = new int[15][15];
		embarcacoesAfundadas = 0;
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

	public int getEmbarcacoesAfundadas() {
		return embarcacoesAfundadas;
	}

	public void posicionarEmbarcacao(Embarcacao embarcacao, int orientacao) {
		int linha = embarcacao.getPosicao().getLinha();
		int coluna = embarcacao.getPosicao().getColuna();
		int tipo = embarcacao.getTipo();

		if (tipo == 3) {
			tabuleiro[linha][coluna] = tipo;
			switch (orientacao) {
			case 1:
				linha += 1;
				coluna -= 1;
				tabuleiro[linha][coluna] = tipo;
				coluna += 2;
				tabuleiro[linha][coluna] = tipo;
				break;

			case 2:
				linha -= 1;
				coluna += 1;
				tabuleiro[linha][coluna] = tipo;
				linha += 2;
				tabuleiro[linha][coluna] = tipo;
				break;

			case 3:
				linha -= 1;
				coluna += 1;
				tabuleiro[linha][coluna] = tipo;
				coluna -= 2;
				tabuleiro[linha][coluna] = tipo;
				break;

			case 4:
				linha -= 1;
				coluna -= 1;
				tabuleiro[linha][coluna] = tipo;
				linha += 2;
				tabuleiro[linha][coluna] = tipo;
				break;
			}

		} else {
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
		embarcacao.setPosicao(embarcacao.getPosicao());
		// notificarObservadores();
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
		} else {
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
		// notificarObservadores();
	}

	public boolean validaPosicionar(Coordenada coordenada, int tipo, int orientacao) {
		int linha = coordenada.getLinha();
		int coluna = coordenada.getColuna();

		switch (orientacao) {
		case 1:
			if (coluna + tipo > 15)
				return false;
			for (int i = 0; i < tipo; i++) {
				if (!validaQuadrado(linha, coluna + i))
					return false;
			}
			break;
		case 2:
			if (linha - tipo < -1)
				return false;
			for (int i = 0; i < tipo; i++) {
				if (!validaQuadrado(linha - i, coluna))
					return false;
			}
			break;
		case 3:
			if (coluna - tipo < -1)
				return false;
			for (int i = 0; i < tipo; i++) {
				if (!validaQuadrado(linha, coluna - i))
					return false;
			}
			break;
		case 4:
			if (linha + tipo > 15)
				return false;
			for (int i = 0; i < tipo; i++) {
				if (!validaQuadrado(linha + i, coluna))
					return false;
			}
			break;
		}
		return true;
	}

	public boolean validaPosicionarHidroaviao(Coordenada coordenada, int orientacao) {
		int linha = coordenada.getLinha();
		int coluna = coordenada.getColuna();
		switch (orientacao) {
		case 1:
			if (coluna == 0 || coluna == 14 || linha == 14)
				return false;
			break;
		case 2:
			if (linha == 0 || linha == 14 || coluna == 14)
				return false;
			break;
		case 3:
			if (linha == 0 || coluna == 0 || coluna == 14)
				return false;
			break;
		case 4:
			if (linha == 0 || coluna == 0 || linha == 14)
				return false;
			break;
		}

		// Valida colisão
		// Verifica cabeça
		if (!validaQuadrado(linha, coluna))
			return false;
		switch (orientacao) {

		case 1:
			// Esquerda embaixo
			linha += 1;
			coluna -= 1;
			if (!validaQuadrado(linha, coluna))
				return false;
			// Direita embaixo
			coluna += 2;
			if (!validaQuadrado(linha, coluna))
				return false;

			break;
		case 2:
			// Direita em cima
			linha -= 1;
			coluna += 1;
			if (!validaQuadrado(linha, coluna))
				return false;
			// Direita embaixo
			linha += 2;
			if (!validaQuadrado(linha, coluna))
				return false;

			break;
		case 3:
			// Esquerda em cima
			linha -= 1;
			coluna -= 1;
			if (!validaQuadrado(linha, coluna))
				return false;
			// Direita em cima
			coluna += 2;
			if (!validaQuadrado(linha, coluna))
				return false;

			break;
		case 4:
			// Esquerda em cima
			linha -= 1;
			coluna -= 1;
			if (!validaQuadrado(linha, coluna))
				return false;
			// Direita embaixo
			linha += 2;
			if (!validaQuadrado(linha, coluna))
				return false;

			break;
		}
		return true;
	}

	public void afundarEmbarcacoes() {
		boolean vertical;
		int tipo;

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				tipo = tabuleiro[i][j];
				if (tipo < 0 && tipo > -100) {
					if (tipo == -10) {
						tabuleiro[i][j] = -1;
						embarcacoesAfundadas++;
					}
					else if (tipo == -30) {
						if (verificarSeAfundada(i,j)) {
							afundarEmbarcacao(i, j);
							embarcacoesAfundadas++;
						}
					}
					else if (tipo < -10) {
						vertical = indicaVertical(i, j, tipo);
						if (verificarSeAfundada(i,j, vertical, tipo)) {
							afundarEmbarcacao(i, j, vertical, tipo);
							embarcacoesAfundadas++;
						}
					}
				}
			}
		}
	}

	private boolean indicaVertical(int i, int j, int tipo) {
		return (i + 1 <= 14 && tabuleiro[i + 1][j] != 0);
	}

	private void afundarEmbarcacao(int i, int j, boolean vertical, int tipo) {
		int tam = (tipo*-1)/10;
		for (int n = 0; n < tam; n++) {
			if (vertical) {
				tabuleiro[i + n][j] = tipo / 10;				
			}
			else {				
				tabuleiro[i][j + n] = tipo / 10;
			}
		}
	}

	private boolean verificarSeAfundada(int i, int j, boolean vertical, int tipo) {
		int afundou = 0;
		int tam = (tipo*-1)/10;
		for (int n = 0; n < tam; n++) {
				if (vertical) {
					if ((i + n <= 14) && tabuleiro[i + n][j] == tipo) {
		                afundou++;
					}
				}
				else {				
					if ((j + n <= 14) && tabuleiro[i][j + n] == tipo) {
		                afundou++;
					}
				}		
		}
		return (afundou == tam);
	}
	
	private void afundarEmbarcacao(int i, int j) {
		for (int a = -1; a <= 1; a++) {
			for (int b = -1; b <= 1; b++) {
				if ((i + a >= 0 && i + a <= 14) && (j + b >= 0 && j + b <= 14))
					if (tabuleiro[i+a][j+b] == -30)
						tabuleiro[i+a][j+b] = -3;
			}
		}
	}

	private boolean verificarSeAfundada(int i, int j) {
		int afundou = 0;
		for (int a = -1; a <= 1; a++) {
			for (int b = -1; b <= 1; b++) {
				if ((i + a >= 0 && i + a <= 14) && (j + b >= 0 && j + b <= 14))
					if (tabuleiro[i+a][j+b] == -30)
						afundou++;
			}
		}
		return (afundou == 3);
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
			System.out.print(String.format("%d ", i >= 10 ? i - 10 : i));
			for (int j = 0; j < 15; j++) {
				System.out.print(tabuleiro[i][j] == 0 ? "- " : tabuleiro[i][j] + " ");
			}
			System.out.println();
		}
	}
}
