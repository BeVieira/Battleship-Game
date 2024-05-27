package Model;

public class ModelFacade {
	
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
		//Preciso remover o navio da lista de embarcações do jogador
		//Pensando em como fazer
	}
}
