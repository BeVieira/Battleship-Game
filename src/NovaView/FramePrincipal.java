package NovaView;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

class FramePrincipal extends JFrame {
	final int LARG_DEFAULT=850;
	final int ALT_DEFAULT=500;
	
	public FramePrincipal() {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // encerra o programa quando clica no x
		setResizable(false);
		getContentPane().add(new PainelPosicionamento());
		setTitle("Batalha Naval");
	}
	
	public static void main(String args[]) {
		new FramePrincipal().setVisible(true);
	}
}
