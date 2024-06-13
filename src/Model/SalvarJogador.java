package Model;

import java.util.ArrayList;

class SalvarJogador{
	private String nome;
	private ArrayList<Embarcacao> navios;
	protected Tabuleiro tabuleiro;
	protected Tabuleiro tabuleiroAlvo;
	
	public void Salva(Jogador j) {
		this.nome = j.getNome();
		this.navios = j.getNavios();
		this.tabuleiro = j.getTabuleiro();
		this.tabuleiroAlvo = j.getTabuleiroAlvo();
	}
		
	
}
