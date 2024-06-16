package NovaView;

import javax.swing.JPanel;

import Model.ModelFacade;

import java.awt.Graphics;
import java.awt.Color;

public class PainelPosicionamento extends JPanel{
    final int lado = 15;
    final int tamanhoCasa = 20;
    final int xInicial = 500;
    final int yInicial = 50;
    final int tamanhoTabuleiro = lado * tamanhoCasa;
    ModelFacade facade;
    
    final Color ciano = new Color(173, 216, 230);
    final Color verdeClaro = new Color(144, 238, 144);
    final Color verdeEscuro = new Color(0, 100, 0);
    
    int numJogador;
    
    public PainelPosicionamento(int num) {
    	this.numJogador = num;
    	facade = ModelFacade.getFacade();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenhaTabuleiro(g);
        desenhaNavios(g);
        atualizaTabuleiro(g);
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

    private void atualizaTabuleiro(Graphics g) {
        int[][] tabuleiro = facade.getTabuleiro();
        
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                int tipo = tabuleiro[i][j];
                int x = xInicial + j * 20;
                int y = yInicial + i * 20;
                /* Lógica para pintar de vermelho se não posicionar
                if (!tratadorEventos.isValido()) {
                	g.setColor(Color.RED);
                }
                 */
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
                repaint();
            }
        }
    }

    
    private void desenhaNavios(Graphics g) {
        final int alinhamento = 30;
        int x = 30;
        int y = yInicial;

        //Hidroaviao
        for (int i = 0; i < 5; i++, x+=4*tamanhoCasa) {
        	desenhaHidroaviao(g, x, y);
        }
        
        //Submarino
        x = alinhamento;
        y += 70;
        for (int i = 0; i < 4; i++, x+=2*tamanhoCasa) {
            desenhaNavio(g, x, y, 1, Color.YELLOW);
        }
        
        //Destroyer
        x = alinhamento;
        y += 50;
        for (int i = 0; i < 3; i++, x+=3*tamanhoCasa) {
            desenhaNavio(g, x, y, 2, ciano);
        }
        
        //Cruzador
        x = alinhamento;
        y += 40;
        for (int i = 0; i < 2; i++, x+=5*tamanhoCasa) {
            desenhaNavio(g, x, y, 4, Color.ORANGE);
        }
        
        //Couracado
        x = alinhamento;
        y += 40;
        desenhaNavio(g, x, y, 5, verdeEscuro);
        
        g.setColor(Color.BLACK);
        g.drawString("Jogador " + numJogador + " posicione suas armas", 270, 380);
    }

    private void desenhaNavio(Graphics g, int x, int y, int tam, Color cor) {
        g.setColor(cor);
        g.fillRect(x, y, tam * tamanhoCasa , tamanhoCasa);
    }

    private void desenhaHidroaviao(Graphics g, int x, int y) {
        g.setColor(verdeClaro);
        g.fillRect(x + tamanhoCasa, y, tamanhoCasa, tamanhoCasa);
        g.fillRect(x, y + tamanhoCasa, tamanhoCasa, tamanhoCasa);
		g.fillRect(x + 2 * tamanhoCasa, y + tamanhoCasa, tamanhoCasa, tamanhoCasa);
    }
}
