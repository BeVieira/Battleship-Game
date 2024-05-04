package Model;

import java.util.ArrayList;

public class Jogador {
	private String nome;
	private ArrayList<Embarcacao> navios;
	protected Tabuleiro tabuleiro;
	protected Tabuleiro tabuleiroAlvo;

	public Jogador(String nome) {
		this.nome = nome;
		tabuleiro = new Tabuleiro();
		tabuleiroAlvo = new Tabuleiro();

		navios = new ArrayList<Embarcacao>();
		// 1 Couraçado
		navios.add(new Couracado());
		// 2 Cruzadores
		navios.add(new Cruzador());
		navios.add(new Cruzador());
		// 3 Destroyers
		navios.add(new Destroyer());
		navios.add(new Destroyer());
		navios.add(new Destroyer());
		// 4 Submarinos
		navios.add(new Submarino());
		navios.add(new Submarino());
		navios.add(new Submarino());
		navios.add(new Submarino());
		// 5 Hidroaviões
		navios.add(new Hidroaviao());
		navios.add(new Hidroaviao());
		navios.add(new Hidroaviao());
		navios.add(new Hidroaviao());
		navios.add(new Hidroaviao());
	}

	public ArrayList<Embarcacao> getNavios() {
		//todo 
		//tem que conseguir andar nos navios para poder escrever corretamente
		return navios;
	}
	//tem que ser feita uma atualiza qtd de nav
	public int getqtdnavios() {
		//todo
		//retornar qtd atualizade de navios
		return 15;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public Tabuleiro getTabuleiroAlvo() {
		return tabuleiroAlvo;
	}

	public void setTabuleiroAlvo(Tabuleiro tabuleiroAlvo) {
		this.tabuleiroAlvo = tabuleiroAlvo;
	}

	public void setNavios(ArrayList<Embarcacao> navios) {
		this.navios = navios;
	}

	// Controller fica responável por gerar a Coordenada
	public boolean InserirEmbarcacao(Coordenada coordenada, Embarcacao embarcacao) {
		return embarcacao.Posicionar(this.tabuleiro, coordenada);
	}

	// Retorna falso caso o tiro seja em uma casa atirada
	public boolean Atirar(Coordenada coordenada) {
		int casa = tabuleiroAlvo.getCasa(coordenada);
		if (casa == 0)
			tabuleiroAlvo.setCasa(coordenada, -100);
		else if (casa > 0)
			tabuleiroAlvo.setCasa(coordenada, -casa);
		else
			return false;
		return true;

	}
}
