package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FramePrincipal extends JFrame implements ActionListener {
	final int LARG_DEFAULT=850;
	final int ALT_DEFAULT=500;
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem loadItem;
	
	public FramePrincipal() {
		setVisible(true);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		addMouseListener(new TratadorEventos());
		addKeyListener(new TratadorEventosTeclado());
		getContentPane().add(new PainelPosicionamento());
		setTitle("Batalha Naval");
		menuBar = new JMenuBar();
		fileMenu = new JMenu("Arquivo");
		loadItem = new JMenuItem("Carregar jogo");

		fileMenu.add(loadItem);
		loadItem.addActionListener(this);

		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
	}
	
	public void  carregarArquivo(File arquivo) throws FileNotFoundException {
		JFileChooser arq = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.txt","txt");
		arq.setFileFilter(filtro);
		int resposta = arq.showOpenDialog(new JDialog());
		if(resposta == JFileChooser.APPROVE_OPTION) {
			arquivo = arq.getSelectedFile();
			JOptionPane.showMessageDialog(null, "Arquivo selecionado com sucesso");
		}
		else if(resposta == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "cancelado");
		}
	}


	public void lerArquivo(File arquivo) throws FileNotFoundException {
	    Scanner input = new Scanner(arquivo);
	    
	    // Lê o nome do jogador 1
	    String nomeJogador1 = input.nextLine();
	    jogador1.setNome(nomeJogador1);

	    // Lê o tabuleiro do jogador 1
	    for (int i = 0; i < 15; i++) {
	        for (int k = 0; k < 15; k++) {
	            Coordenada t = new Coordenada(k, i);
	            char casa = input.next().charAt(0);
	            jogador1.getTabuleiro().setCasa(t, casa);
	        }
	    }

	    // Lê o nome do jogador 2
	    String nomeJogador2 = input.next();
	    jogador2.setNome(nomeJogador2);

	    // Lê o tabuleiro do jogador 2
	    for (int i = 0; i < 15; i++) {
	        for (int k = 0; k < 15; k++) {
	            Coordenada t = new Coordenada(k, i);
	            char casa = input.next().charAt(0);
	            jogador2.getTabuleiro().setCasa(t, casa);
	        }
	    }

	    input.close();
	}
	
	public void carregaJogo(File file) throws FileNotFoundException {
		//escolher arquivo
		carregarArquivo(file);
		//ler os dados do arquivo
		lerArquivo(file);
		//passar as informaçoes
		
		//ir pro ataque'
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadItem) {
			try {
				criarArquivo();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
