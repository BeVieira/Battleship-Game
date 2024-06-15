package Model;

import java.util.ArrayList;

class SalvarJogador{
	private String nome;
	private ArrayList<Embarcacao> navios;
	protected int[][] tabuleiro;
	protected int[][] tabuleiroAlvo;
	
	public void Salva(Jogador j) {
		this.nome = j.getNome();
		this.navios = j.getNavios();
		this.tabuleiro = j.getTabuleiro().getTabuleiroEstado();
		this.tabuleiroAlvo = j.getTabuleiroAlvo().getTabuleiroEstado();
	}
		
	
}
