package Model;

import java.util.ArrayList;
import java.util.Iterator;

import View.ObservadorIf;

class Jogador {
	private String nome;
	private ArrayList<Embarcacao> navios;
	private Tabuleiro tabuleiro;
	private Tabuleiro tabuleiroAlvo;

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
		return navios;
	}
	
	public int getQtdNavios() {
		return this.getNavios().size();
	}

	public void removeNavio(int tipoNavio) {
	    Iterator<Embarcacao> iterator = this.navios.iterator();
	    while (iterator.hasNext()) {
	        Embarcacao e = iterator.next();
	        if (e.getTipo() == tipoNavio) {
	            iterator.remove();
	            break;
	        }
	    }
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
	
	public void registra(ObservadorIf observador) {
		this.tabuleiro.add(observador);
		this.tabuleiroAlvo.add(observador);
		
	}
}
