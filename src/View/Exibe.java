package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exibe extends JFrame{
	
	public  Exibe() {
		
		setVisible(true);
		setSize(850, 500);
		setTitle("Batalha Naval");
		setDefaultCloseOperation(EXIT_ON_CLOSE); //encerra o programa quando clica no x
		setResizable(false);
		setLocationRelativeTo(null); // faz ir pro meio da tela ao abrir

		


	}
	
	
	public void paint(Graphics g) {
		//desenha hidroaviao verde
		int x_inicial = 30;
		int x_inicial_cima = 50;
		
		g.setColor(Color.green);
		for(int i = 0; i< 5;i++) {
			g.fillRect(x_inicial_cima, 80,20, 20);
			x_inicial_cima+=80;
		}
		//desenha parte debaixo do hidro
		for(int i = 0; i< 10;i++) {
			g.fillRect(x_inicial, 100,20, 20);
			x_inicial+=40;
		}
		//desenha submarinos
		g.setColor(Color.red);
		int y = 150;
		int x = 30;
		for(int i = 0; i< 4;i++) {
			g.fillRect(x, y,20, 20);
			x+=40;
		}
		//desenha destroyer
		g.setColor(Color.BLUE);
		x=30;
		y =200;
		for(int i = 0; i< 3;i++) {
			g.fillRect(x, y,20, 20);
			x+=20;
			g.fillRect(x, y,20, 20);
			x+=40;
		}
		//desenha cruzadores
		g.setColor(Color.MAGENTA);
		x=30;
		y =240;
		for(int i = 0; i< 2;i++) {
			for(int j = 0; j< 4;j++) {
				g.fillRect(x, y,20, 20);
				x+=20;
			}
			//g.fillRect(x, y,20, 20);
			x+=30;
		}
		//desenha couraçado
		g.setColor(Color.red);
		x=30;
		y =280;
		for(int i = 0; i < 5;i++) {
			g.fillRect(x, y,20, 20);
			x+=20;
		}
		//desenha tabuleiro
		g.setColor(Color.WHITE);
		//desenha quadradao
		g.fillRect(500, 70,300, 300);
		g.setColor(Color.black);
		int x1 = 500;
		int x2 = 500;
		int y1 = 70;
		int y2 = 370;
		//faz linha em pé
		for(int i = 0; i< 16;i++) {
			g.drawLine(x1, y1, x2, y2);
			x1+= 20;
			x2+= 20;
		}
		//faz linha deitada
		x1 = 500;
		x2 = 800;
		y1 = 70;
		y2 = 70;
		//faz linha em pé
		for(int i = 0; i< 16;i++) {
			g.drawLine(x1, y1, x2, y2);
			y1+=20;
			y2+=20;
		}
		//desenha letra
		y2 = 85;
		x2 = 485;
		for(int i = 0; i<15;i++){
			g.drawString(String.format("%c ", 65 + i), x2, y2);
			y2+=20;
			
		}
		//desenha numeros
		y2 = 65;
		x2 = 505;
		for(int i = 0; i<15;i++){
			g.drawString(String.format("%d ", i), x2, y2);
			x2+=20;
			
		}
		//faz botao
		JButton b = new JButton("proximo jogador");
		setLayout(null);
		b.setBounds(300, 390,150, 40);
		b.setVisible(true);
		add(b);
		//pq o botao so aparece quando passa o mouse em cima? peguntar pro ivan

		
		
	}

}
