package Model;

public class Submarino extends Embarcacao{
	public Submarino() {
		super(1);
	}

	@Override
	public boolean Posicionar(Tabuleiro tabuleiro, Coordenada coordenada, String orientacao) {
		tabuleiro.setCasa(coordenada, this.getTipo());
		if (tabuleiro.getCasa(coordenada) == 0)
			return false;
		return true;
	}
}
