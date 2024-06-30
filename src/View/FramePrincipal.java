package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class FramePrincipal extends JFrame {
	final int LARG_DEFAULT=850;
	final int ALT_DEFAULT=500;
	
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
	}
	
}
