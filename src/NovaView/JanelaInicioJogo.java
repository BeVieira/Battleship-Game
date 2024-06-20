package NovaView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Exibe;

public class JanelaInicioJogo extends JFrame implements ActionListener {
	public final int LARG_DEFAULT = 400;
	public final int ALT_DEFAULT = 300;
	int larguraBotao = 200;
	int alturaBotao = 50;
	JPanel menu = new JPanel();
	JPanel defineJogador = new JPanel();
	JTextField nomeJ1;
   	JTextField nomeJ2;
	
	public JanelaInicioJogo() {
		CentralizaTela();
		Menu();
	}

	private void CentralizaTela() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int larguraTela = screenSize.width;
		int alturaTela = screenSize.height;
		int x = larguraTela / 2 - LARG_DEFAULT / 2;
		int y = alturaTela / 2 - ALT_DEFAULT / 2;
		setVisible(true);
		setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		setTitle("Batalha Naval");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	private void Menu() {
		int xPos = (LARG_DEFAULT / 2) - (larguraBotao / 2);
		int espacamentoVertical = (ALT_DEFAULT - 2 * alturaBotao) / 3;
		int yPos1 = espacamentoVertical;
		int yPos2 = 2 * espacamentoVertical + alturaBotao;
		
		JButton carregaJogo = new JButton("Carregar Jogo");
		JButton novoJogo = new JButton("Novo Jogo");
		menu.setLayout(null);
		
		menu.add(carregaJogo);
		menu.add(novoJogo);
		
		carregaJogo.setBounds(xPos, yPos1-17, larguraBotao, alturaBotao);
		novoJogo.setBounds(xPos, yPos2-17, larguraBotao, alturaBotao);
		
		carregaJogo.addActionListener(this);
		novoJogo.addActionListener(this);
		
		getContentPane().add(menu);
	}
	
	private void DefineJogador() {
		int xPos = (LARG_DEFAULT / 2) - (larguraBotao / 2);
		int larguraCaixaTexto = 175;
		int alturaCaixaTexto = 40;
		defineJogador.setLayout(null);
		
		JButton confirma = new JButton("Começar");
		defineJogador.add(confirma);
		confirma.setBounds(xPos, 200, larguraBotao, alturaBotao);
		confirma.addActionListener(this);
		getContentPane().add(confirma);
		
		
		JLabel indicaJ1 = new JLabel ("Jogador 1: ");
		JLabel indicaJ2 = new JLabel ("Jogador 2: ");

		defineJogador.add(indicaJ1);
		defineJogador.add(indicaJ2);
		
		indicaJ1.setBounds(60, 50, larguraCaixaTexto, alturaCaixaTexto);
		indicaJ2.setBounds(60, 125, larguraCaixaTexto, alturaCaixaTexto);
		
		getContentPane().add(indicaJ1);
		getContentPane().add(indicaJ2);
		
		nomeJ1 = new JTextField(20);
		nomeJ2 = new JTextField(20);
		
		defineJogador.add(nomeJ1);
		defineJogador.add(nomeJ2);
		
		nomeJ1.setBounds(137, 50, larguraCaixaTexto, alturaCaixaTexto);
		nomeJ2.setBounds(137, 125, larguraCaixaTexto, alturaCaixaTexto);
		
		getContentPane().add(nomeJ1);
		getContentPane().add(nomeJ2);
	}
	
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Carregar Jogo")) {
        	getContentPane().removeAll();
        	DefineJogador();
        	repaint();// Adicionar aqui a lógica para carregar o painel de carregamento de jogo
        } else if (e.getActionCommand().equals("Novo Jogo")) {
            getContentPane().removeAll(); 
            DefineJogador(); 
            repaint();
        } else if (e.getActionCommand().equals("Começar")) {
        	//Lógica para exportar os nomes do JTextField
        	getContentPane().add(new PainelPosicionamento(1));
        	dispose();
        }
    }
	
}
