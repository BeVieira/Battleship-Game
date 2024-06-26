package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.Control;
import Observer.Observer;

public class Ataque extends JFrame implements Observer,ActionListener {
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem saveItem;
	
	Control controle;
	boolean bloqueado = true;
	boolean atirou = false;
	int tiros;
    final Color ciano = new Color(173, 216, 230);
    final Color verdeClaro = new Color(144, 238, 144);
    final Color verdeEscuro = new Color(0, 100, 0);
    
    JLabel aviso, tiroResult;
    JButton b;
    
	public Ataque() {
		controle = Control.getController();
		setSize(870, 550);
		setTitle("Batalha Naval");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null); 
		addMouseListener(new TratadorMouse());
		controle.registrarObservador(this);
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("Arquivo");
		saveItem = new JMenuItem("Salvar jogo");
		fileMenu.add(saveItem);
		
		saveItem.addActionListener(this);

		menuBar.add(fileMenu);
		setJMenuBar(menuBar);

		
		b = new JButton("Avançar");
		setLayout(null);
		b.addActionListener(this);
		b.setBounds(330, 400, 150, 40);
		b.setVisible(true);
		b.setFocusable(false);
		add(b);
		
		aviso = new JLabel();
		aviso.setBounds(250, 375, 500, 20);
		add(aviso);
		
		tiroResult = new JLabel();
		tiroResult.setBounds(250, 355, 300, 20);
		tiroResult.setForeground(Color.red);
		add(tiroResult);
		setVisible(true);
	}
    
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
			
			if ((x > xInicial) && (x < (xInicial+300))) {
				if ((y > yInicial) && (y < (yInicial+300))) {
					int indexX = (x - xInicial)/20;
					int indexY = (y - yInicial)/20;
					
					controle.atirar(turno, indexX, indexY);
					controle.afundarEmbarcacoes();
					verificarVitoria();
					atirou = true;
					if (controle.getTiro() >= 0) tiros--;
				}
				if (tiros == 0) {
					controle.trocaTurno();
					bloqueado = true;
					repaint();
				}
			}
		}
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
					case -2:
					case -3:
					case -4:
					case -5:
						g.setColor(Color.BLACK);
						break;
					case -10:
					case -20:
					case -30:
					case -40:
					case -50:
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
		super.paint(g);
		
		desenhaTabuleiro(g, 1);
		desenhaTabuleiro(g, 2);
		b.repaint();
		
		String str;
		if (atirou == true) {
			tiroResult.setVisible(true);

			switch (controle.getTiro()) {
				case 1:
					str = "Acertou um submarino";
					break;
				case 2:
					str = "Acertou um destroyer";
					break;
				case 3:
					str = "Acertou um hidroaviao";
					break;
				case 4:
					str = "Acertou um cruzador";
					break;
				case 5:
					str = "Acertou um couracado";
					break;
				default:
					str = "Acertou água";
					break;
			}
		}
		else {
			str = "";
			tiroResult.setVisible(false);
		}
		tiroResult.setText(str);
		tiroResult.repaint();
		
		str = "";
		if (bloqueado == true) str = "Visao bloqueda, " + controle.getNomeAtual() + " deve clicar para desbloquear visao";
		else {
			str = "Tiros restantes: " + tiros;
		}
		aviso.setText(str);
		aviso.repaint();

		g.drawString("Tabuleiro do " + controle.getNome(1), 140, 75);
		g.drawString("Tabuleiro do " + controle.getNome(2), 600, 75);
	}

	private void verificarVitoria() {
	    if (controle.indicaVencedor()) {
	        JOptionPane.showMessageDialog(this, controle.getNomeAtual() + " venceu a partida!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
	        int resposta = JOptionPane.showConfirmDialog(this, "Desejam continuar jogando?", "Continuar?", JOptionPane.YES_NO_OPTION);
	        if (resposta == JOptionPane.YES_OPTION) {
	            controle.recomecarJogo();
	            dispose();
	        } else {
	            System.exit(0);
	        }
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveItem && (bloqueado || tiros == 3)) {
			saveItem.setVisible(true);
			try {
				controle.criarArquivo();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("Avançar")) {
			if (bloqueado) {
				tiros = 3;
				bloqueado = false;
				atirou = false;
			}
			repaint();
		}	
	}

	@Override
	public void atualizar() {
		repaint();
	}
}