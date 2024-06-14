package Model;

import View.ObservadorIf;

public class ModelFacade {
	private Jogador jogador;
	private Coordenada coordenadaj1;
	static ModelFacade f = null;
	
	public static ModelFacade getFacade() {
		if(f==null)
			f=new ModelFacade();
		return f;	
	}
	
	public void registra(ObservadorIf observador) {
		jogador.registra(observador);
	}
	
	public int[][] getEstadoTabuleiro() {
		return jogador.getTabuleiro().getTabuleiroEstado();
	}
	
	public void criaJogador(String nome) {
		this.jogador = new Jogador(nome);
	}
	public void DefineNome(Jogador jogador, String nome) {
		jogador.setNome(nome);
	}
	
	public void PosicionarEmbarcacao(Jogador jogador, Embarcacao navio, Coordenada casa) {
		jogador.InserirEmbarcacao(casa, navio);	
	}
	
	public void RealizaTiro(Jogador jogador, Coordenada casa) {
		jogador.Atirar(casa);
	}
	
	public void RemoveEmbarcacao(Jogador jogador, Embarcacao navio) {
		jogador.removeNavio(navio.getTipo());
	}
	
	public int getColuna() {
		return coordenadaj1.getColuna();
	}
	
	public void setLinha(int linha) {
		coordenadaj1.setLinha(linha);
	}
	
	public void setColuna(int coluna) {
		coordenadaj1.setColuna(coluna);
	}
}