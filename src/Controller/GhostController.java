package Controller;

import java.util.ArrayList;

import Model.ModelFacade;
import Observer.Observer;
import Observer.Subject;

public class GhostController implements Subject {
	private static GhostController controle = null;
	private ModelFacade fachada;
	private ArrayList<Observer> observadores;
	public boolean posicionando = false;
	private boolean isValid = true;
	public int tipo;

	public static GhostController getController() {
		if (controle == null)
			controle = new GhostController();
		return controle;
	}

	private GhostController() {
		fachada = ModelFacade.getFacade();
		this.observadores = new ArrayList<>();
	}
	
	public boolean definirGhost(int tipo, int turno) {
		isValid = fachada.definirGhost(tipo, turno);
		notificarObservadores();
		return isValid;
	}
	
	public int[] getPosition() {
		return fachada.getGhostPosition();
	}
	
	public int getOrientation() {
		return fachada.getGhostOrientation();
	}
	
	public int getType() {
		return fachada.getGhostType();
	}
	
	public boolean getValid() {
		return isValid;
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

}
