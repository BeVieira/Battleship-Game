package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveItem && (bloqueado || tiros == 3)) {
			try {
				criarArquivo();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
