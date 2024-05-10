package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Ataque extends JFrame implements ActionListener{
public  Ataque() {
		
		setVisible(true);
		setSize(870, 550);
		setTitle("Batalha Naval");
		setDefaultCloseOperation(EXIT_ON_CLOSE); //encerra o programa quando clica no x
		setResizable(false);
		setLocationRelativeTo(null); // faz ir pro meio da tela ao abrir

	}
	
	
	public void paint(Graphics g) {
		//desenha tabuleiro j2
		g.setColor(Color.cyan);
		//desenha quadradao
		g.fillRect(500, 100,300, 300);
		g.setColor(Color.black);
		int x1 = 500;
		int x2 = 500;
		int y1 = 100;
		int y2 = 400;
		//faz linha em pé
		for(int i = 0; i< 16;i++) {
			g.drawLine(x1, y1, x2, y2);
			x1+= 20;
			x2+= 20;
		}
		//faz linha deitada
		x1 = 500;
		x2 = 800;
		y1 = 100;
		y2 = 100;
		//faz linha em pé
		for(int i = 0; i< 16;i++) {
			g.drawLine(x1, y1, x2, y2);
			y1+=20;
			y2+=20;
		}
		//desenha letra
		y2 = 115;
		x2 = 485;
		for(int i = 0; i<15;i++){
			g.drawString(String.format("%c ", 65 + i), x2, y2);
			y2+=20;
			
		}
		//desenha numeros
		y2 = 95;
		x2 = 505;
		for(int i = 0; i<15;i++){
			g.drawString(String.format("%d ", i+1), x2, y2);
			x2+=20;
		}
		
		//tabuleiro 1
		g.setColor(Color.cyan);
		//desenha quadradao
		g.fillRect(40, 100,300, 300);
		g.setColor(Color.black);
		 x1 = 40;
		 x2 = 40;
		 y1 = 100;
		 y2 = 400;
		//faz linha em pé
		for(int i = 0; i< 16;i++) {
			g.drawLine(x1, y1, x2, y2);
			x1+= 20;
			x2+= 20;
		}
		//faz linha deitada
		x1 = 40;
		x2 = 340;
		y1 = 100;
		y2 = 100;
		//faz linha em pé
		for(int i = 0; i< 16;i++) {
			g.drawLine(x1, y1, x2, y2);
			y1+=20;
			y2+=20;
		}
		//desenha letra
		y2 = 115;
		x2 = 25;
		for(int i = 0; i<15;i++){
			g.drawString(String.format("%c ", 65 + i), x2, y2);
			y2+=20;
			
		}
		//desenha numeros
		y2 = 95;
		x2 = 45;
		for(int i = 0; i<15;i++){
			g.drawString(String.format("%d ", i+1), x2, y2);
			x2+=20;
		}
		JButton b = new JButton("Começar Jogo");
		setLayout(null);
		b.setBounds(330, 430,150, 40);
		b.setVisible(true);
		add(b);
		//pq o botao so aparece quando passa o mouse em cima? peguntar pro ivan
		b.addActionListener(this);
		//como identificar o jogador de acordo com o nome que ele digitar
		g.drawString("visao bloqueda Jogador.getnome() deve clicar para desbloquear visao", 300, 450);
		g.drawString("tabuleiro Jogador.getnome()", 40, 65);
		g.drawString("tabuleiro Jogador.getnome()", 500, 65);
		b.addActionListener(this);
}



	@Override
	public void actionPerformed(ActionEvent e) {
		
			dispose();//terminar implementaçao
		}
	}