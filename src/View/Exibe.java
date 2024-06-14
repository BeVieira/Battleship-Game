package View;

import java.awt.BorderLayout;
import Model.ModelFacade;
import Model.Observadoif;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.management.modelmbean.ModelMBean;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.Control;

public class Exibe extends JFrame implements ActionListener, ObservadorIf {
	int navioMouse = 0;
	int numJogador;

	int hidros = 5, submarinos = 4, destroyers = 3, cruzadores = 2, couracados = 1;

	// Somente para fim de testes
	Model.Tabuleiro tabTeste = new Tabuleiro();

	public Exibe(int n) {
		numJogador = n;
		addMouseListener(new LerMouse());
		setVisible(true);
		setSize(850, 500);
		setTitle("Batalha Naval");
		setDefaultCloseOperation(EXIT_ON_CLOSE); // encerra o programa quando clica no x
		setResizable(false);
		setLocationRelativeTo(null); // faz ir pro meio da tela ao abrir
	}
	
	public void drawNavio(Graphics g, int x, int y, int len) {
		g.fillRect(x, y, 20 * len, 20);
	}

	public void drawHidro(Graphics g, int x, int y, int len) {

		// Desenha dois quadrados inferiores
		g.fillRect(x, y + 20, 20, 20);
		g.fillRect(x + 40, y + 20, 20, 20);

		// Desenha quadrado superior
		g.fillRect(x + 20, y, 20, 20);
	}

	private class LerMouse extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();

			if ((x >= 500) && (x <= 800) && (y >= 70) && (y <= 370)) {
				int indexX = (x - 500) / 20;
				int indexY = (y - 70) / 20;
				Model.Coordenada coord = new Model.Coordenada(indexX, indexY);
				if (navioMouse != 0) {
					// TODO: Quando facade estiver funcionando, substituir este trecho
					Model.Embarcacao emb;
					switch (navioMouse) {
						case 1:
							if (submarinos > 0) {
								emb = new Model.Submarino();
								if (emb.Posicionar(tabTeste, coord)) {
									submarinos += -1;
								}
							}
							break;
						case 2:
							if (destroyers > 0) {
								emb = new Model.Destroyer();
								if (emb.Posicionar(tabTeste, coord)) {
									destroyers += -1;
								}
							}
							break;
						case 3:
							if (hidros > 0) {
								emb = new Model.Hidroaviao();
								if (emb.Posicionar(tabTeste, coord)) {
									hidros += -1;
								}
							}
							break;
						case 4:
							if (cruzadores > 0) {
								emb = new Model.Cruzador();
								if (emb.Posicionar(tabTeste, coord)) {
									cruzadores += -1;
								}
							}
							break;
						case 5:
							if (couracados > 0) {
								emb = new Model.Couracado();
								if (emb.Posicionar(tabTeste, coord)) {
									couracados += -1;
								}
							}
							break;
					}
					navioMouse = 0;
					repaint();
				}
			}

			// hidroaviao - tipo 3
			else if ((x >= 30) && (x <= 410) && (y >= 80) && (y <= 120)) {
				navioMouse = 3;
			}
			// submarino - tipo 1
			else if ((x >= 30) && (x <= 170) && (y >= 150) && (y <= 170)) {
				navioMouse = 1;
			}
			// destroyer - tipo 2
			else if ((x >= 30) && (x <= 190) && (y >= 200) && (y <= 220)) {
				navioMouse = 2;
			}
			// cruzador - tipo 4
			else if ((x >= 30) && (x <= 170) && (y >= 240) && (y <= 260)) {
				navioMouse = 4;
			}
			// couracado - tipo 5
			else if ((x >= 30) && (x <= 110) && (y >= 280) && (y <= 300)) {
				navioMouse = 5;
			}
		}
	}

	private void desenhaCasa(int x, int y, int tipo, Graphics g) {
		switch (tipo) {
			case 1:
				g.setColor(Color.red);
				g.fillRect(x, y, 20, 20);
				break;
			case 2:
				g.setColor(Color.blue);
				g.fillRect(x, y, 20, 20);
				break;
			case 3:
				g.setColor(Color.green);
				g.fillRect(x, y, 20, 20);
				break;
			case 4:
				g.setColor(Color.magenta);
				g.fillRect(x, y, 20, 20);
				break;
			case 5:
				g.setColor(Color.red);
				g.fillRect(x, y, 20, 20);
				break;
			default:
				break;
		}
	}

	public void paint(Graphics g) {
		super.paintComponents(g);
		int x = 30;
		int y = 80;

		// desenha hidroaviao
		for (int i = 0; i < hidros; i++) {
			g.setColor(Color.green);
			drawHidro(g, x, y, 0);
			x += 80;
		}

		// desenha submarinos
		x = 30;
		y = 150;

		for (int i = 0; i < submarinos; i++) {
			g.setColor(Color.red);
			drawNavio(g, x, y, 1);
			x += 40;
		}

		// desenha destroyer
		x = 30;
		y = 200;
		for (int i = 0; i < destroyers; i++) {
			g.setColor(Color.BLUE);
			drawNavio(g, x, y, 2);
			x += 60;
		}

		// desenha cruzadores
		x = 30;
		y = 240;
		for (int i = 0; i < cruzadores; i++) {
			g.setColor(Color.MAGENTA);
			drawNavio(g, x, y, 3);
			x += 80;
		}

		// desenha couraçado
		x = 30;
		y = 280;
		for (int i = 0; i < couracados; i++) {
			g.setColor(Color.red);
			drawNavio(g, x, y, 4);
			x += 100;
		}

		// desenha tabuleiro
		g.setColor(Color.WHITE);
		// desenha quadradao
		g.fillRect(500, 70, 300, 300);

		// percorre tabuleiro e desenha cada casa
		int tipo;
		for (int i = 0; i < 15; i++) {
			Control.getController().setLinha(i);
			for (int j = 0; j < 15; j++) {
				x = 500 + (20 * j);
				y = 70 + (20 * i);

				Control.getController().setColuna(j);
				tipo = tabTeste.getCasa(coords);
				desenhaCasa(x, y, tipo, g);
			}
		}

		g.setColor(Color.black);
		int x1 = 500;
		int x2 = 500;
		int y1 = 70;
		int y2 = 370;
		// faz linha em pé
		for (int i = 0; i < 16; i++) {
			g.drawLine(x1, y1, x2, y2);
			x1 += 20;
			x2 += 20;
		}
		// faz linha deitada
		x1 = 500;
		x2 = 800;
		y1 = 70;
		y2 = 70;
		// faz linha em pé
		for (int i = 0; i < 16; i++) {
			g.drawLine(x1, y1, x2, y2);
			y1 += 20;
			y2 += 20;
		}
		// desenha letra
		y2 = 85;
		x2 = 485;
		for (int i = 0; i < 15; i++) {
			g.drawString(String.format("%c ", 65 + i), x2, y2);
			y2 += 20;

		}
		// desenha numeros
		y2 = 65;
		x2 = 505;
		for (int i = 0; i < 15; i++) {
			g.drawString(String.format("%d ", i + 1), x2, y2);
			x2 += 20;

		}

		// faz botao
		String textoBotao;

		if (numJogador == 1) {
			textoBotao = "Próximo jogador";
		} else {
			textoBotao = "Tabuleiro Pronto";
		}
		JButton b = new JButton();
		setLayout(null);
		b.setBounds(300, 390, 150, 40);
		add(b);
		b.setVisible(true);
		b.setText(textoBotao);
		b.addActionListener(this);

		// como identificar o jogador de acordo com o nome que ele digitar
		g.drawString("Jogador " + numJogador + " selecione uma arma na lista", 270, 410);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Próximo jogador") {
			new Exibe(2);
			dispose();// fecha janela atual
		} else if (e.getActionCommand() == "Tabuleiro Pronto") {
			new Ataque();
			dispose();
		}
	}

	@Override
	public void notify(Observadoif observado) {
		//pinta tela de acordo com o tabuleiro mudando
		
		
		
	}

}
