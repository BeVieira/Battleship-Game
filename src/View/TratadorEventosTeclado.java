package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controller.Control;
import Controller.GhostController;
import Observer.Observer;
import Observer.Subject;

public class TratadorEventosTeclado extends KeyAdapter implements Subject{
	Control controle = Control.getController();
	GhostController ghost = GhostController.getController();
	
	private ArrayList<Observer> observadores;

	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_ESCAPE) && (ghost.posicionando == true)) {
			if (ghost.getValid() == true) {
				controle.adicionarEmbarcacao(ghost.getType());
				ghost.remove();
			}
			else {
				ghost.remove();
			}
		}
	}
	
	@Override
	public void registrarObservador(Observer observador) {
		observadores.add(observador);
	}

	@Override
	public void removerObservador(Observer observador) {
		observadores.remove(observador);
	}

	public void notificarObservadores() {
		for (Observer observador : observadores) {
			observador.atualizar();
		}
	}
}
