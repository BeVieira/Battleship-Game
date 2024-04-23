package Model;

public class Destroyer extends Embarcacao{
	public Destroyer() {
		super(2);
	}

	@Override
	public boolean Posicionar(Tabuleiro tabuleiro, Coordenada coordenada, String orientacao) {
		for (int i = 0; i < this.getTipo(); i++) {
			coordenada.setxCoord(coordenada.getxCoord()+i);
			tabuleiro.setCasa(coordenada, this.getTipo());
		}
		return false;
	}
}
