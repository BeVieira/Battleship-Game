package Model;

import java.util.ArrayList;

import View.ObservadorIf;

public class ModelFacade {
	static ModelFacade f = null;
    private Tabuleiro tabuleiro;
    private Jogador jogador;
    private Coordenada coordenada;
	
	private ModelFacade() {
		this.tabuleiro = new Tabuleiro();
		this.jogador = new Jogador();
		this.coordenada = new Coordenada(0,0);
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

    public void posicionarEmbarcacao(int tipo) {
    	Embarcacao embarcacao = jogador.retiraNavio(tipo);
        jogador.inserirEmbarcacao(this.coordenada, embarcacao);
    }

    public boolean isPosicaoValida(int tipo) {
        return jogador.getTabuleiro().validaPosicionar(this.coordenada, tipo);
    }
    
    public void definirCoordenada(int x, int y) {
    	this.coordenada.setColuna(x);
    	this.coordenada.setLinha(y);
    }
    
    public int[][] getTabuleiro(){
    	return jogador.getTabuleiro().getTabuleiroEstado();
    }
	
    public void registra(ObservadorIf observador) {
    	//fazer
    }
}