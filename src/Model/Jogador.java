package Model;

import java.util.ArrayList;
import java.util.Iterator;

class Jogador {
	private String nome;
	private ArrayList<Embarcacao> navios;
	private Tabuleiro tabuleiro;
	private Tabuleiro tabuleiroAlvo;

	public Jogador() {
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
	
	public void setNavios(ArrayList<Embarcacao> navios) {
		this.navios = navios;
	}

	public Embarcacao retiraNavio(int tipoNavio) {
	    Iterator<Embarcacao> iterator = this.navios.iterator();
	    while (iterator.hasNext()) {
	        Embarcacao e = iterator.next();
	        if (e.getTipo() == tipoNavio) {
	           iterator.remove();
	           return e;
	        }
	    }
	    return null;
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

	public void inserirEmbarcacao(Coordenada coordenada, Embarcacao embarcacao) {
		tabuleiro.posicionarEmbarcacao(embarcacao,embarcacao.getOrientacao());
	}

	/*public void girarEmbarcacao(Embarcacao embarcacao) {
		tabuleiro.girarEmbarcacao(embarcacao);
	}*/
	
	public int realizarTiro(Coordenada coordenada) {
		int casa = tabuleiroAlvo.getCasa(coordenada);
		if (casa > 0)
			tabuleiroAlvo.setCasa(coordenada, -10*casa);
		else if (casa == 0)
			tabuleiroAlvo.setCasa(coordenada, -100);
		return casa;
	}
}
