package View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Controller.Control;
import Controller.GhostController;
import Observer.Observer;
import Observer.Subject;

public class TratadorEventos extends MouseAdapter implements Subject{
	Control controle = Control.getController();
	GhostController ghost = GhostController.getController();
	
	private ArrayList<Observer> observadores;
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (e.getButton() == MouseEvent.BUTTON3) {
			ghost.rotate();
		}
		else if (e.getButton() == MouseEvent.BUTTON1) {
			//Limites das navios
			if ((x >= 40) && (x <= 420) && (y >= 100) && (y <= 140)) {
				ghost.tipo = 3;
			}
			else if ((x >= 40) && (x <= 180) && (y >= 170) && (y <= 190)) {
				ghost.tipo = 1;
			}
			else if ((x >= 40) && (x <= 200) && (y >= 220) && (y <= 240)) {
				ghost.tipo = 2;
			}
			else if ((x >= 40) && (x <= 220) && (y >= 260) && (y <= 280)) {
				ghost.tipo = 4;
			}
			else if ((x >= 40) && (x <= 140) && (y >= 300) && (y <= 320)) {
				ghost.tipo = 5;
			}
			controle.notificarObservadores();
			if (ghost.tipo != 0) {
				//Limites tabuleiro
				if ((x > 510) && (x < 810) && (y > 100) && (y < 400)) {
					int xIndex = (x - 510) / 20;
					int yIndex = (y - 100) / 20;
					if(controle.getEmbarcacaoNum(ghost.tipo, controle.getTurno()) != 0) {
						controle.definirCoordenada(xIndex, yIndex);
						ghost.definirGhost(ghost.tipo, controle.getTurno());
					}
					
				}
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
