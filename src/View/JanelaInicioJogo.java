package View;

import java.awt.BorderLayout;
import Model.Jogador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JanelaInicioJogo extends JFrame {
	public final int LARG_DEFAULT = 400;
	public final int ALT_DEFAULT = 300;
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	int larguraTela = screenSize.width;
	int alturaTela = screenSize.height;
	int x = larguraTela / 2 - LARG_DEFAULT / 2;
	int y = alturaTela / 2 - ALT_DEFAULT / 2;

	public JanelaInicioJogo() {
		Menu();
	}

	private void CentralizaTela() {
		setVisible(true);
		setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		setTitle("Batalha Naval");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	private void Menu() {
		CentralizaTela();
		int larguraBotao = 200;
		int alturaBotao = 50;
		int xPos = (LARG_DEFAULT / 2) - (larguraBotao / 2);
		int espacamentoVertical = (ALT_DEFAULT - 2 * alturaBotao) / 3;
		int yPos1 = espacamentoVertical;
		int yPos2 = 2 * espacamentoVertical + alturaBotao;
		JButton carregaJogo = new JButton("Carregar Jogo");
		JButton novoJogo = new JButton("Novo Jogo");
		setLayout(null);
		carregaJogo.setBounds(xPos, yPos1-17, larguraBotao, alturaBotao);
		novoJogo.setBounds(xPos, yPos2-17, larguraBotao, alturaBotao);
		add(carregaJogo);
		add(novoJogo);
		carregaJogo.setVisible(true);
		novoJogo.setVisible(true);
	}
}
