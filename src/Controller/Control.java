package Controller;

import Model.ModelFacade;
import NovaView.JanelaInicioJogo;
import View.ObservadorIf;

public class Control {
	private String nomej1;
	private String nomej2;
	// singleton
	private static Control controller = null;
	private ModelFacade facade;

	public static Control getController() {
		if (controller == null)
			controller = new Control();
		return controller;
	}

	Control() {

	}

	public void registra(ObservadorIf observador) {
		facade.registra(observador);
	}

	public void passanome1(String nome1) {
		nomej1 = nome1;
	}

	public void passanome2(String nome2) {
		nomej2 = nome2;
	}

	public String setnome2() {
		return nomej2;
	}

	public String setnome1() {
		return nomej1;
	}

}
