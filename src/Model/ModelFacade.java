package Model;

import java.util.ArrayList;

import View.ObservadorIf;

public class ModelFacade {
	static ModelFacade f = null;
    private Tabuleiro tabuleiro;
    private Jogador jogador;
	
	private ModelFacade() {
		this.tabuleiro = new Tabuleiro();
		this.jogador = new Jogador();
		jogador.setTabuleiro(tabuleiro);
	}
	
	public static ModelFacade getFacade() {
		if(f==null)
			f=new ModelFacade();
		return f;	
	}

    public ArrayList<Embarcacao> getEmbarcacoes() {
        return jogador.getNavios();
    }

    public void posicionarEmbarcacao(Coordenada coordenada, Embarcacao embarcacao) {
        jogador.inserirEmbarcacao(coordenada, embarcacao);
    }

    public boolean isPosicaoValida(Embarcacao embarcacao, Coordenada coordenada) {
        return jogador.getTabuleiro().validaPosicionar(coordenada, embarcacao.getTipo());
    }
	

}