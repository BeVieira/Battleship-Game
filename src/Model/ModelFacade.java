package Model;

import java.util.ArrayList;

import View.ObservadorIf;

public class ModelFacade {
	static ModelFacade f = null;
	//jogador 1
    private Tabuleiro tabuleiroj1;
    private Tabuleiro tabuleiroj2;
    private Jogador jogador1;
    private Jogador jogador2;
    private Coordenada coordenada;
    
    public void inicializaJogadores(String nome1, String nome2) {
    	tabuleiroj1 = new Tabuleiro();
    	jogador1 = new Jogador();
    	jogador1.setNome(nome1);
    	jogador1.setTabuleiro(tabuleiroj1);
    	jogador1.setTabuleiroAlvo(tabuleiroj2);
    	
    	tabuleiroj2 = new Tabuleiro();
    	jogador2 = new Jogador();
    	jogador2.setNome(nome2);
    	jogador2.setTabuleiro(tabuleiroj2);
    	jogador2.setTabuleiroAlvo(tabuleiroj1);
    	
    }
    public String getnomej1() {
    	return jogador1.getNome();
    }
    public String getnomej2() {
    	return jogador2.getNome();
    }
	
	
	private ModelFacade() {
		this.tabuleiroj1 = new Tabuleiro();
		this.jogador1 = new Jogador();
		this.coordenada = new Coordenada(0,0);
		jogador1.setTabuleiro(tabuleiroj1);
	}
	
	public static ModelFacade getFacade() {
		if(f==null)
			f=new ModelFacade();
		return f;	
	}

    public ArrayList<Embarcacao> getEmbarcacoes() {
        return jogador1.getNavios();
    }

    public void posicionarEmbarcacao(int tipo) {
    	Embarcacao embarcacao = jogador1.retiraNavio(tipo);
        jogador1.inserirEmbarcacao(this.coordenada, embarcacao);
    }

    public boolean isPosicaoValida(int tipo) {
        return jogador1.getTabuleiro().validaPosicionar(this.coordenada, tipo);
    }
    
    public void definirCoordenada(int x, int y) {
    	this.coordenada.setColuna(x);
    	this.coordenada.setLinha(y);
    }
    
    public int[][] getTabuleiro(){
    	return jogador1.getTabuleiro().getTabuleiroEstado();
    }
	
    public void registra(ObservadorIf observador) {
    	//fazer
    }
}