package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.Control;
import Observer.Observer;

public class Ataque extends JFrame implements Observer,ActionListener {
	Control controle;
	boolean bloqueado = true;
	int tiros;
    final Color ciano = new Color(173, 216, 230);
    final Color verdeClaro = new Color(144, 238, 144);
    final Color verdeEscuro = new Color(0, 100, 0);

    JLabel aviso;
    JButton b;
    
	private class TratadorMouse extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e){
			if (tiros <= 0) return;
			int turno = controle.getTurno();
			int x = e.getX();
			int y = e.getY();
			int xInicial, yInicial;
			if (turno == 1) {
				xInicial = 500;
			}
			else {
				xInicial = 40;
			}
			yInicial = 100;
			
			if ((x >= xInicial) && (x <= (xInicial+300))) {
				if ((y >= yInicial) && (y <= (yInicial+300))) {
					int indexX = (x - xInicial)/20;
					int indexY = (y - yInicial)/20;
					
					controle.atirar(turno, indexX, indexY);
					System.out.println("casa: "+controle.getTiro());
				}
				tiros--;
				if (tiros == 0) {
					controle.trocaTurno();
					bloqueado = true;
					repaint();
				}
			}
		}
	}
	
	public Ataque() {
		controle = Control.getController();
		controle.registrarObservador(this);
		setVisible(true);
		setSize(870, 550);
		setTitle("Batalha Naval");
		setDefaultCloseOperation(EXIT_ON_CLOSE);// encerra o programa quando clica no x
		setResizable(false);
		setLocationRelativeTo(null); // faz ir pro meio da tela ao abrir
		addMouseListener(new TratadorMouse());
		
		b = new JButton("Avançar");
		setLayout(null);
		b.addActionListener(this);
		b.setBounds(330, 430, 150, 40);
		b.setVisible(true);
		b.setFocusable(false);
		add(b);
		
		aviso = new JLabel();
		aviso.setBounds(250, 390, 300, 40);
		add(aviso);
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
		
		if ((!bloqueado) && (controle.getTurno() == jogador)) {
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
					}
					g.fillRect(x1 + (j*20), y1 + (i*20), 20, 20);
				}
			}
		}
		
		// desenha quadradao
		if ((bloqueado) || (controle.getTurno() != jogador)) {
			g.setColor(Color.cyan);
			g.fillRect(xInicial, yInicial, 300, 300);
		}
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				int casa = controle.getCasa(j, i, jogador);
				if (casa < 0) {
					switch (casa) {
						case -1:
							g.setColor(Color.orange);
							break;
						case -2:
							g.setColor(Color.RED);
							break;
						case -100:
							g.setColor(Color.GRAY);
							break;
					}
					g.fillRect(x1 + (j*20), y1 + (i*20), 20, 20);
				}
			}
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
		//super.paint(g);
		
		desenhaTabuleiro(g, 1);
		desenhaTabuleiro(g, 2);
		
		String str;
		if (bloqueado == true) str = "Visao bloqueda, " + controle.getNome() + " deve clicar para desbloquear visao";
		else str = "Tiros restantes: " + tiros;
		aviso.setText(str);
		
		b.repaint();
		aviso.repaint();
		
		
		g.drawString("tabuleiro do " + controle.getNome(1), 40, 65);
		g.drawString("tabuleiro do " + controle.getNome(2), 500, 65);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("bloqueado: " + bloqueado);
		if (bloqueado == true) {
			tiros = 3;
			bloqueado = false;
		}
		else {
			controle.trocaTurno();
			tiros = 0;
			bloqueado = true;
			
		}
		repaint();
		System.out.println("turno: "+controle.getTurno());
	}

	@Override
	public void atualizar() {
		repaint();
	}
}