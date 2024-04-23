package Model;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Jogador k = new Jogador("P1");
		Scanner entrada; 
		for (Embarcacao navio: k.getNavios()) {
			entrada = new Scanner(System.in);
			System.out.print("Digite a posicao: ");
			String posicao = entrada.nextLine();
			k.InserirEmbarcacao(posicao, navio);
			k.tabuleiro.ExibeTabuleiro();
		}	
	}
}
