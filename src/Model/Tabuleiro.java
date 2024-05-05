package Model;

public class Tabuleiro {
	protected int[][] tabuleiro;

	public int getCasa(Coordenada coordenada) {
		return tabuleiro[coordenada.getLinha()][coordenada.getColuna()];
	}

	public void setCasa(Coordenada coordenada, int tipoEmbarcacao) {
		this.tabuleiro[coordenada.getLinha()][coordenada.getColuna()] = tipoEmbarcacao;
	}

	public Tabuleiro() {
		tabuleiro = new int[15][15];
	}

	// Para fins de teste
	/*public void ExibeTabuleiro() {
		System.out.println("  1 2 3 4 5 6 7 8 9 0 1 2 3 4 5");
		for (int i = 0; i < 15; i++) {
			System.out.print(String.format("%c ", 65 + i));
			for (int j = 0; j < 15; j++) {
				System.out.print(tabuleiro[i][j] == 0 ? "- " : tabuleiro[i][j] + " ");
			}
			System.out.println();
		}
	}*/

}
