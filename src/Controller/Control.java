package Controller;

import Model.ModelFacade;
import Observer.Observer;
import Observer.Subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Control implements Subject {
	private static Control controle = null;
	private ModelFacade fachada;
	private ArrayList<Observer> observadores;
	private int turno = 0;

	public static Control getController() {
		if (controle == null)
			controle = new Control();
		return controle;
	}

	private Control() {
		fachada = ModelFacade.getFacade();
		this.observadores = new ArrayList<>();
	}

	public void comecar(String nomeJ1, String nomeJ2) {
		fachada.inicializaJogadores(nomeJ1, nomeJ2);
	}

	public String getNomeAtual() {
		return fachada.getNome(this.turno);
	}
	
	public String getNome(int turno) {
		return fachada.getNome(turno);
	}

	@Override
	public void registrarObservador(Observer observador) {
		observadores.add(observador);
	}

	@Override
	public void removerObservador(Observer observador) {
		observadores.remove(observador);
	}

	@Override
	public void notificarObservadores() {
		for (Observer observador : observadores) {
			observador.atualizar();
		}
	}
	
	public void adicionarEmbarcacao(int tipo) {
		fachada.posicionarEmbarcacao(tipo, this.turno);
		notificarObservadores();
	}

	public void trocaTurno() {
		if (this.turno == 0)
			this.turno = 1;
		else
			this.turno = this.turno % 2 + 1;	
	}

	public int getTurno() {
		return this.turno;
	}

	public void definirCoordenada(int xIndex, int yIndex) {
		fachada.definirCoordenada(xIndex, yIndex);
	}
	
	public int getCasa(int coluna, int linha, int jogador) {
		return fachada.getCasa(jogador, coluna, linha);
	}
	
	public void setCasa(int coluna, int linha, int valor, int jogador) {
		fachada.setCasa(jogador, coluna, linha, valor);
	}
	
	public int getEmbarcacaoNum(int tipo, int jogador) {
		return fachada.getEmbarcacaoNum(tipo, jogador);
	}
	
	public void salvarJogo(File arquivo) throws FileNotFoundException {
		fachada.salvarJogo(arquivo);
	}

}