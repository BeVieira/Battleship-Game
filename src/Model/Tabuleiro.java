package Model;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;
import View.ObservadorIf;

class Tabuleiro  implements Observadoif{
	private int[][] tabuleiro;
	private List<ObservadorIf> lst = new ArrayList<ObservadorIf>();

	public int getCasa(Coordenada coordenada) {
		return tabuleiro[coordenada.getLinha()][coordenada.getColuna()];
	}

	public void setCasa(Coordenada coordenada, int tipoEmbarcacao) {
		this.tabuleiro[coordenada.getLinha()][coordenada.getColuna()] = tipoEmbarcacao;
	}

	public Tabuleiro() {
		tabuleiro = new int[15][15];
	}
	public void ExibeTabuleiro() {
		System.out.println("  1 2 3 4 5 6 7 8 9 0 1 2 3 4 5");
		for (int i = 0; i < 15; i++) {
			System.out.print(String.format("%c ", 65+i));
			for (int j = 0; j < 15; j++) {
				System.out.print(tabuleiro[i][j] == 0 ? "- " : tabuleiro[i][j] + " ");
			}
			System.out.println();
		}
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
}
