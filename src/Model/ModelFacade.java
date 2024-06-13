package Model;

public class ModelFacade {
	
	public void ConfiguraJogo() {
		Jogador jogador1 = new Jogador("a");
		Jogador jogador2 = new Jogador("n");
		jogador1.tabuleiroAlvo = jogador2.tabuleiro;
		jogador2.tabuleiroAlvo = jogador1.tabuleiro;
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
	
	public void Registra(Observer o) {
		
	}
}
