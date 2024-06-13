package Model;

import View.ObservadorIf;

public class ModelFacade {
	private Jogador jogador1;
	private Coordenada coordenadaj1;
	
	public void registra(ObservadorIf observador) {
		jogador1.registra(observador);
	}
	
	public void inicializaPartida() {
		jogador1 = new Jogador("");
		coordenadaj1 = new Coordenada(0,0);
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