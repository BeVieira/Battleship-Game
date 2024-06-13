package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import Model.Observer;

public class Tabuleiro implements Observable{
	private List<Observer> lst = new ArrayList<Observer>();
	
	private int[][] tabuleiro;

	public int getCasa(Coordenada coordenada) {
		return tabuleiro[coordenada.getLinha()][coordenada.getColuna()];
	}

	public void setCasa(Coordenada coordenada, int tipoEmbarcacao) {
		this.tabuleiro[coordenada.getLinha()][coordenada.getColuna()] = tipoEmbarcacao;
		atualiza();
	}

	public Tabuleiro() {
		tabuleiro = new int[15][15];
	}
	
	public void addObserver(Observer o) {
		lst.add(o);
	}
	
	public void removeObserver(Observer o) {
		lst.remove(o);
	}
	
	public Object get() {
		return new Tabuleiro();
	}
	
	private void atualiza() {
		ListIterator<Observer> lista = lst.listIterator();
		while (lista.hasNext())
			lista.next().notificar(this);
	}
}
