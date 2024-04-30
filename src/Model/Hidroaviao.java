package Model;

public class Hidroaviao extends Embarcacao{
	public Hidroaviao() {
		super(3);
	}

	@Override
	public boolean Posicionar(Tabuleiro tabuleiro, Coordenada coordenada) {
		tabuleiro.setCasa(coordenada, this.getTipo());
		coordenada.setLinha(coordenada.getLinha()+1);
		coordenada.setColuna(coordenada.getColuna()-1);
		tabuleiro.setCasa(coordenada, this.getTipo());
		coordenada.setColuna(coordenada.getColuna()+2);
		tabuleiro.setCasa(coordenada, this.getTipo());
		return true;
	}
}
