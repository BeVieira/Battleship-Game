package Model;

public abstract class Embarcacao {
	private int tipo;
	
	public Embarcacao(int tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}
	
	public abstract boolean Posicionar(Tabuleiro tabuleiro, Coordenada coordenada, String orientacao);

}
