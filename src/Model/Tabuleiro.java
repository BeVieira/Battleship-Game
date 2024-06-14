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
	
	public boolean Posicionar(Coordenada coordenada, int tipo) {
		int linha = coordenada.getLinha();
		int coluna = coordenada.getColuna();
		
		if (tipo == 3) {
			if (ValidaPosicionarHidroaviao(coordenada)) {
				tabuleiro[linha][coluna] = tipo;
				linha += 1;
				coluna -= 1;
				tabuleiro[linha][coluna] = tipo;
				coluna += 2;
				tabuleiro[linha][coluna] = tipo;
				return true;
				}
			return false;
			}
		
		if (ValidaPosicionar(coordenada, tipo)) {
			for (int i = 0; i < tipo; i++)
				tabuleiro[linha][coluna + i] = tipo;
			return true;
		}
		return false;
	}
		
	private boolean ValidaQuadrado(int linha, int coluna) {
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
	
	private boolean ValidaPosicionar(Coordenada coordenada, int tipo) {
		int linha = coordenada.getLinha();
        int coluna = coordenada.getColuna();
        
        if (coluna + tipo > 15)
        	return false;
        
        for (int i = 0; i < tipo; i++) {
        	if (!ValidaQuadrado(linha, coluna + i))
        		return false;
        }
        return true; 
	}
	
	private boolean ValidaPosicionarHidroaviao(Coordenada coordenada) { 
		int linha = coordenada.getLinha();
        int coluna = coordenada.getColuna();

		if (coluna == 0 || coluna == 14 || linha == 14)
			return false;
		
		//Verifica cabeça
		if (!ValidaQuadrado(linha, coluna))
			return false;
		
		//Verifica esquerda
		linha+=1;
		coluna=-1;
        if (!ValidaQuadrado(linha, coluna))
			return false;
        
        //Verifica direita
        coluna += 2;
        if (!ValidaQuadrado(linha, coluna))
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
	
	public void ExibeTabuleiro() {
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