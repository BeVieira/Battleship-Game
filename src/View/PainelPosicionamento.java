package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Control;
import Controller.GhostController;
import Observer.Observer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;


public class PainelPosicionamento extends JPanel implements Observer, ActionListener{
	private Control controle;
	private GhostController ghost;
	JButton bt;
    final int lado = 15;
    final int tamanhoCasa = 20;
    final int xInicial = 500;
    final int yInicial = 50;
    final int tamanhoTabuleiro = lado * tamanhoCasa;
    boolean criadoAtaque = false;
    
    int hidro = 5;
    int sub = 4;
    int destroyer = 3;
    int cruzador = 2;
    int couracado = 1;
    
    final Color ciano = new Color(173, 216, 230);
    final Color verdeClaro = new Color(144, 238, 144);
    final Color verdeEscuro = new Color(0, 100, 0);
    
    public PainelPosicionamento() {
    	controle = Control.getController();
    	controle.registrarObservador(this);
    	ghost = GhostController.getController();
    	ghost.registrarObservador(this);
    	
    	bt = new JButton("Proximo jogador");
    	bt.addActionListener(this);
    	bt.setVisible(true);
    	bt.setFocusable(false);	
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenhaTabuleiro(g);
        atualizarTab(g);
        atualizaGhost(g);
        desenhaNavios(g);
        if(controle.getEmbarcacaoNum(0, controle.getTurno()) == 0) {
        	bt.setEnabled(true);
        }
    }

    private void desenhaTabuleiro(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(xInicial, yInicial, tamanhoTabuleiro, tamanhoTabuleiro);

        g.setColor(Color.BLACK);

        //Linhas verticais
        for (int i = 0; i <= lado; i++) {
            int x = xInicial + i * tamanhoCasa;
            g.drawLine(x, yInicial, x, yInicial + tamanhoTabuleiro);
        }

        //Linhas horizontais
        for (int i = 0; i <= lado; i++) {
            int y = yInicial + i * tamanhoCasa;
            g.drawLine(xInicial, y, xInicial + tamanhoTabuleiro, y);
        }

        //Letras de A a O
        for (int i = 0; i < lado; i++) {
            int x = xInicial - 15;
            int y = yInicial + i * tamanhoCasa + tamanhoCasa / 2 + 5;
            g.drawString(String.format("%c", 'A' + i), x, y);
        }

        //Números de 1 a 15
        for (int i = 0; i < lado; i++) {
            int x = xInicial + i * tamanhoCasa + tamanhoCasa / 2 - 5;
            int y = yInicial - 10;
            g.drawString(String.format("%d", i + 1), x, y);
        }
    }

    public void atualizarTab(Graphics g) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                int tipo = controle.getCasa(j, i, controle.getTurno());
                int x = xInicial + j * 20;
                int y = yInicial + i * 20;
                
            	switch (tipo) {
            	case 1:
            		g.setColor(Color.YELLOW);
            		break;
            	case 2:
            		g.setColor(ciano);
            		break;
            	case 3:
            		g.setColor(verdeClaro);
            		break;
            	case 4:
            		g.setColor(Color.ORANGE);
            		break;
            	case 5:
            		g.setColor(verdeEscuro);
            	    break;
            	default:
            		g.setColor(Color.WHITE);
            		break;
            	}
                g.fillRect(x, y, tamanhoCasa, tamanhoCasa);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, tamanhoCasa, tamanhoCasa);
            }
        }
    }

	private void atualizaGhost(Graphics g) {
    	if (ghost.posicionando == false) 
    		return;
    	int pos[] = ghost.getPosition();
    	
        int x = xInicial + pos[0] * 20;
        int y = yInicial + pos[1] * 20;
    	Color cor;
		if (ghost.getValid()) {
			cor = Color.green;
		}
		else {
			cor = Color.red;
		}
		
    	if (ghost.getType() == 3) {
    		desenhaGhostHidroaviao(g, x, y, cor);
    	}
    	else {
    		desenhaGhostNavio(g, x, y, ghost.getType(), cor);
    	}
    }
	
	public void desenhaGhostHidroaviao(Graphics g, int x, int y, Color cor) {
		switch (ghost.getOrientation()) {
		case 1:
			g.setColor(cor);
			g.fillRect(x, y, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, tamanhoCasa, tamanhoCasa);
			
			g.setColor(cor);
			g.fillRect(x-20, y+20, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x-20, y+20, tamanhoCasa, tamanhoCasa);
			
			g.setColor(cor);
			g.fillRect(x+20, y+20, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x+20, y+20, tamanhoCasa, tamanhoCasa);
			break;
		case 2:
			g.setColor(cor);
			g.fillRect(x, y, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, tamanhoCasa, tamanhoCasa);
			
			g.setColor(cor);
			g.fillRect(x+20, y-20, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x+20, y-20, tamanhoCasa, tamanhoCasa);
			
			g.setColor(cor);
			g.fillRect(x+20, y+20, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x+20, y+20, tamanhoCasa, tamanhoCasa);
			break;
		case 3:
			g.setColor(cor);
			g.fillRect(x, y, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, tamanhoCasa, tamanhoCasa);
			
			g.setColor(cor);
			g.fillRect(x-20, y-20, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x-20, y-20, tamanhoCasa, tamanhoCasa);
			
			g.setColor(cor);
			g.fillRect(x+20, y-20, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x+20, y-20, tamanhoCasa, tamanhoCasa);
			break;
		case 4:
			g.setColor(cor);
			g.fillRect(x, y, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, tamanhoCasa, tamanhoCasa);
			
			g.setColor(cor);
			g.fillRect(x-20, y+20, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x-20, y+20, tamanhoCasa, tamanhoCasa);
			
			g.setColor(cor);
			g.fillRect(x-20, y-20, tamanhoCasa, tamanhoCasa);
			g.setColor(Color.BLACK);
			g.drawRect(x-20, y-20, tamanhoCasa, tamanhoCasa);
			break;
	}
	}
	
	public void desenhaGhostNavio(Graphics g, int x, int y, int tipo, Color cor) {
		switch (ghost.getOrientation()) {
			case 1:
				for (int i = 0; i < tipo; i++) {
					g.setColor(cor);
					g.fillRect(x + (20*i), y, tamanhoCasa, tamanhoCasa);
					g.setColor(Color.BLACK);
					g.drawRect(x + (20*i), y, tamanhoCasa, tamanhoCasa);
				}
				break;
			case 2:
				for (int i = 0; i < tipo; i++) {
					g.setColor(cor);
					g.fillRect(x, y - (20*i), tamanhoCasa, tamanhoCasa);
					g.setColor(Color.BLACK);
					g.drawRect(x, y - (20*i), tamanhoCasa, tamanhoCasa);
				}
				break;
			case 3:
				for (int i = 0; i < tipo; i++) {
					g.setColor(cor);
					g.fillRect(x - (20*i), y, tamanhoCasa, tamanhoCasa);
					g.setColor(Color.BLACK);
					g.drawRect(x - (20*i), y, tamanhoCasa, tamanhoCasa);
				}
				break;
			case 4:
				for (int i = 0; i < tipo; i++) {
					g.setColor(cor);
					g.fillRect(x, y + (20*i), tamanhoCasa, tamanhoCasa);
					g.setColor(Color.BLACK);
					g.drawRect(x, y + (20*i), tamanhoCasa, tamanhoCasa);
				}
				break;
		}
	}
    
    @Override
    public void atualizar() {
        hidro = controle.getEmbarcacaoNum(3, controle.getTurno());
        sub = controle.getEmbarcacaoNum(1, controle.getTurno());
        destroyer = controle.getEmbarcacaoNum(2, controle.getTurno());
        cruzador = controle.getEmbarcacaoNum(4, controle.getTurno());
        couracado = controle.getEmbarcacaoNum(5, controle.getTurno());
        repaint();
    }
    
    private void desenhaNavios(Graphics g) {
        final int alinhamento = 30;
        int x = 30;
        int y = yInicial;

        //Hidroaviao
        for (int i = 0; i < hidro; i++, x+=4*tamanhoCasa) {
        	desenhaHidroaviao(g, x, y, verdeClaro);
        }
        
        //Submarino
        x = alinhamento;
        y += 70;
        for (int i = 0; i < sub; i++, x+=2*tamanhoCasa) {
            desenhaNavio(g, x, y, 1, Color.YELLOW);
        }
        
        //Destroyer
        x = alinhamento;
        y += 50;
        for (int i = 0; i < destroyer; i++, x+=3*tamanhoCasa) {
            desenhaNavio(g, x, y, 2, ciano);
        }
        
        //Cruzador
        x = alinhamento;
        y += 40;
        for (int i = 0; i < cruzador; i++, x+=5*tamanhoCasa) {
            desenhaNavio(g, x, y, 4, Color.ORANGE);
        }
        
        //Couracado
        x = alinhamento;
        y += 40;
        for (int i = 0; i < couracado; i++, x+=5*tamanhoCasa) {
        	desenhaNavio(g, x, y, 5, verdeEscuro);
        }
        
        bt.setBounds(300, 390, 150, 40);
    	
    	add(bt);
    	
        g.setColor(Color.BLACK);
        g.drawString(controle.getNomeAtual()  + " posicione suas armas", 270, 380);
        
    }

    private void desenhaNavio(Graphics g, int x, int y, int tam, Color cor) {
        g.setColor(cor);
        if (ghost.tipo == tam) g.setColor(Color.blue);
        g.fillRect(x, y, tam * tamanhoCasa , tamanhoCasa);
    }

    private void desenhaHidroaviao(Graphics g, int x, int y, Color cor) {

        g.setColor(cor);
        if (ghost.tipo == 3) g.setColor(Color.blue);
        g.fillRect(x + tamanhoCasa, y, tamanhoCasa, tamanhoCasa);
        g.fillRect(x, y + tamanhoCasa, tamanhoCasa, tamanhoCasa);
		g.fillRect(x + 2 * tamanhoCasa, y + tamanhoCasa, tamanhoCasa, tamanhoCasa);
    }

	
    @Override
	public void actionPerformed(ActionEvent e) {
    	if(controle.getEmbarcacaoNum(0, controle.getTurno()) == 0) {
	    	if(controle.getTurno() == 1) {
	    		controle.trocaTurno();
	            bt.setText("Começar");
	            ghost.notificarObservadores();
	    	}
	    	else {
	    		if (e.getActionCommand() == "Começar") {
    				controle.removerObservador(this);
    				controle.trocaTurno();
        			new Ataque();
        			javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
	    		}
	    	}
    	}
	}
}