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
		//1 Couraçado
		navios.add(new Couracado());
		//2 Cruzadores
		navios.add(new Cruzador());
		navios.add(new Cruzador());
		//3 Destroyers
		navios.add(new Destroyer());
		navios.add(new Destroyer());
		navios.add(new Destroyer());
		//4 Submarinos
		navios.add(new Submarino());
		navios.add(new Submarino());
		navios.add(new Submarino());
		navios.add(new Submarino());
		//5 Hidroaviões
		navios.add(new Hidroaviao());
		navios.add(new Hidroaviao());
		navios.add(new Hidroaviao());
		navios.add(new Hidroaviao());
		navios.add(new Hidroaviao());
	}
	
	public ArrayList<Embarcacao> getNavios() {
		return navios;
	}

	//Alterar parâmetro entrada quando tivermos as coordenadas do clique da View
	public boolean InserirEmbarcacao(String entrada, Embarcacao embarcacao){
		Coordenada coordenada = new Coordenada(entrada);
		return embarcacao.Posicionar(this.tabuleiro, coordenada);
	}
	
	//Retorna falso caso o tiro seja em uma casa atirada
	public boolean Atirar(Coordenada coordenada){
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
