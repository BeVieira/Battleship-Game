package Controller;

import Model.ModelFacade;
import NovaView.JanelaInicioJogo;
import View.ObservadorIf;

public class Control {
	//singleton
	   private static Control controller = null;
	   private ModelFacade facade;
	   
	    public static Control getController()
	    {
	        if(controller == null)
	            controller = new Control();
	        return controller;
	    }
	    
	    Control(){
	    	new JanelaInicioJogo();
	    	facade = new ModelFacade();
	    	
	    }
	    
	    public void registra(ObservadorIf observador) {
	    facade.registra(observador);
	    }
	    
	    public int getColuna() {
	    	return facade.getColuna();
	    	
	    }
	    public void setLinha(int linha) {
			facade.setLinha(linha);
		}
	    
		public void setColuna(int coluna) {
			facade.setColuna(coluna);
		}

	    
}
