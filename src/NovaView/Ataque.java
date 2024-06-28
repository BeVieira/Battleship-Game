package NovaView;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import Controller.Control;

public class Ataque extends JFrame implements ActionListener {
	Control controle;
	boolean bloqueado = true;
	int tiros = 3;
	
	private class TratadorMouse extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e){
			int turno = controle.getTurno();
			int x = e.getX();
			int y = e.getY();
			int xInicial, yInicial;
			if (turno == 1) {
				xInicial = 40;
				yInicial = 100;
			}
			else {
				xInicial = 500;
				yInicial = 100;
			}
			
			int outroJogador = (turno % 2) + 1;
			if ((x >= xInicial) && (x <= (xInicial+300))) {
				if ((y >= yInicial) && (y <= (yInicial+300))) {
					int indexX = (x - xInicial)/20;
					int indexY = (y - yInicial)/20;
					
					int casa = controle.getCasa(indexX,indexY, outroJogador);
					
					if (casa == 0) {
						System.out.println("casa 0");
						controle.setCasa(indexX, indexY, -100, outroJogador);
					}
					System.out.println("casa: "+casa);
				}
			}
		}
	}
	
	public Ataque() {
		controle = Control.getController();
		setVisible(true);
		setSize(870, 550);
		setTitle("Batalha Naval");
		setDefaultCloseOperation(EXIT_ON_CLOSE);// encerra o programa quando clica no x
		setResizable(false);
		setLocationRelativeTo(null); // faz ir pro meio da tela ao abrir
		addMouseListener(new TratadorMouse());
	}
	
	public void desenhaTabuleiro(Graphics g, int jogador) {
		int xInicial, yInicial;
		
		
		if (jogador == 1) {
			xInicial = 40;
			yInicial = 100;
		}
		else {
			xInicial = 500;
			yInicial = 100;
			
		}
		int x1, x2, y1, y2;
		x1 = xInicial;
		y1 = yInicial;
		x2 = x1 + 300;
		y2 = y1 + 300;
		
		if ((bloqueado == false) && (controle.getTurno() == jogador)) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					int casa = controle.getCasa(j, i, jogador);
					switch (casa) {
						default:
							g.setColor(Color.WHITE);
							break;
						case 1:
							g.setColor(Color.YELLOW);
							break;
						case 2:
							g.setColor(Color.CYAN);
							break;
						case 3:
							g.setColor(Color.GREEN);
							break;
						case 4:
							g.setColor(Color.red);
							break;
						case 5:
							g.setColor(Color.orange);
							break;
					}
					g.fillRect(x1 + (j*20), y1 + (i*20), 20, 20);
				}
			}
		}
		
		// desenha quadradao
		if ((bloqueado == true) || (controle.getTurno() != jogador)) {
			g.setColor(Color.cyan);
			g.fillRect(xInicial, yInicial, 300, 300);
		}
		
		
		g.setColor(Color.black);
		
		// faz linha em pé
		x1 = xInicial;
		y1 = yInicial;
		for (int i = 0; i < 16; i++) {
			g.drawLine(x1, y1, x1, y2);
			x1 += 20;
		}
		
		x1 = xInicial;
		y1 = yInicial;
		// faz linha deitada
		for (int i = 0; i < 16; i++) {
			g.drawLine(x1, y1, x2, y1);
			y1 += 20;
		}
		
		x1 = xInicial;
		y1 = yInicial;
		x2 = x1 - 15;
		y2 = y1 + 15;
		// desenha letra
		for (int i = 0; i < 15; i++) {
			g.drawString(String.format("%c ", 65 + i), x2, y2);
			y2 += 20;
		}
		
		x1 = xInicial;
		y1 = yInicial;
		x2 = x1 +5;
		y2 = y1 -5;
		// desenha numeros
		for (int i = 0; i < 15; i++) {
			g.drawString(String.format("%d ", i + 1), x2, y2);
			x2 += 20;
		}
		
	}

	public void paint(Graphics g) {
		desenhaTabuleiro(g, 1);
		desenhaTabuleiro(g, 2);
		
		JButton b = new JButton("Começar Jogo");
		setLayout(null);
		b.setBounds(330, 430, 150, 40);
		b.setVisible(true);
		add(b);
		b.addActionListener(this);

		g.drawString("Visao bloqueda, " + controle.getNome() + " deve clicar para desbloquear visao", 250, 450);
		g.drawString("tabuleiro do " + controle.getNome1(), 40, 65);
		g.drawString("tabuleiro do " + controle.getNome2(), 500, 65);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("bloqueado: " + bloqueado);
		if (bloqueado == true) {
			System.out.println("turno: "+controle.getTurno());
			bloqueado = false;
			repaint();
		}
		else {
			controle.trocaTurno();
			bloqueado = true;
			repaint();
		}
		//dispose(); terminar implementaçao
	}
}