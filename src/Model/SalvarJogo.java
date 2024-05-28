package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class SalvarJogo {
	public ArrayList<Embarcacao> navios = new ArrayList<Embarcacao>();
	
	public void SalvaJogo(Jogador j) throws FileNotFoundException {
		navios = j.getNavios();
		PrintStream output = new PrintStream(new File("saida.txt"));
		output.println(j.getNome());
		
		//salva  navios
		for(int i = 0; i< j.getQtdNavios(); i++ ) {
			output.println(this.navios.get(i));
		}
		
		//salva tabuleiro
		output.println("tabuleiro");
		for(int i = 0; i<15; i++) {
			for(int k = 0; k<15; k++) {
				Coordenada t = new Coordenada(i,k);
				output.print(j.tabuleiro.getCasa(t));
				if(k == 14) {
					output.println();
				}
			}
		}
		
		//salva tabuleiro alvo
		output.println("tabuleiro alvo");
		for(int i = 0; i<15; i++) {
			for(int k = 0; k<15; k++) {
				Coordenada t = new Coordenada(i,k);
				output.print(j.tabuleiroAlvo.getCasa(t));
				if(k == 14) {
					output.println();
				}
				
			}
		}
		output.close();
	}

}
