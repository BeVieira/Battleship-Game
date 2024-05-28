package Model;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		/*/main de teste
		Tabuleiro t = new Tabuleiro();
		//t.ExibeTabuleiro();
		Jogador p1 = new Jogador("p1");
		Coordenada c = new Coordenada(1,1);
		Embarcacao e = new Embarcacao(1);
		p1.InserirEmbarcacao(c, e);
		//p1.tabuleiro.ExibeTabuleiro();
		SalvarJogador savep1 = new SalvarJogador();
		savep1.Salva(p1);
		SalvarJogo save = new SalvarJogo();
		save.SalvaJogo(p1);*/
		Jogador j = new Jogador("A");
		System.out.println(j.getQtdNavios());
		j.removeNavio(1);
		System.out.println(j.getQtdNavios());
		ArrayList<Embarcacao> l = j.getNavios();
		for (int i = 0; i < l.size(); i++)
			System.out.println(l.get(i));
	}

}
