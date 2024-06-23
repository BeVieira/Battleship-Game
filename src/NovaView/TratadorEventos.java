package NovaView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Controller.Control;
import Model.ModelFacade;

public class TratadorEventos extends MouseAdapter{
	int tipoNavio = 0;
	Control controle = Control.getController();
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		//Limites das navios
		if ((x >= 40) && (x <= 420) && (y >= 80) && (y <= 120)) {
			tipoNavio = 3;
		}
		else if ((x >= 40) && (x <= 180) && (y >= 150) && (y <= 170)) {
			tipoNavio = 1;
		}
		else if ((x >= 40) && (x <= 200) && (y >= 200) && (y <= 220)) {
			tipoNavio = 2;
		}
		else if ((x >= 40) && (x <= 220) && (y >= 240) && (y <= 260)) {
			tipoNavio = 4;
		}
		else if ((x >= 40) && (x <= 140) && (y >= 280) && (y <= 300)) {
			tipoNavio = 5;
		}

		if (tipoNavio != 0) {
			//Limites tabuleiro
			if ((x >= 510) && (x <= 810) && (y >= 80) && (y <= 380)) {
				int xIndex = (x - 510) / 20;
				int yIndex = (y - 80) / 20;
				controle.definirCoordenada(xIndex, yIndex);
				//boolean valido = .isPosicaoValida(tipoNavio); //Queria exportar isso para o painel 
				controle.adicionarEmbarcacao(tipoNavio);
				tipoNavio = 0;
				
			}
		}			
	}
}
