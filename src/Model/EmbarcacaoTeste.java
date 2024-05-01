package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmbarcacaoTeste {

	@Test
	public void PosicionarTest() {

		//Caso couraçado
		Tabuleiro tabuleiro = new Tabuleiro();
		int gabarito = 5;

		Embarcacao couracado = new Couracado();
		couracado.Posicionar(tabuleiro, new Coordenada("A5"));
		
		assertEquals("Posição 1 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A5")));
		assertEquals("Posição 2 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A6")));
		assertEquals("Posição 3 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A7")));
		assertEquals("Posição 4 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A8")));
		assertEquals("Posição 5 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A9")));
		
		//Caso Cruzador
		gabarito = 4;
		Embarcacao cruzador = new Cruzador();
		cruzador.Posicionar(tabuleiro, new Coordenada("A5"));
		
		assertEquals("Posição 1 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A5")));
		assertEquals("Posição 2 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A6")));
		assertEquals("Posição 3 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A7")));
		assertEquals("Posição 4 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A8")));
		
		//Caso Hidroavião
		gabarito = 3;
		Embarcacao hidroaviao = new Hidroaviao();
		hidroaviao.Posicionar(tabuleiro, new Coordenada("A5"));
		
		assertEquals("Posição superior inválida", gabarito,tabuleiro.getCasa(new Coordenada("A5")));
		assertEquals("Posição esquerda inválida",gabarito,tabuleiro.getCasa(new Coordenada("B4")));
		assertEquals("Posição direita inválida",gabarito,tabuleiro.getCasa(new Coordenada("B6")));
		
		//Caso Destroyer
		tabuleiro = new Tabuleiro();
		gabarito = 2;
		Embarcacao destroyer = new Destroyer();
		destroyer.Posicionar(tabuleiro, new Coordenada("A5"));
		
		assertEquals("Posição 1 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A5")));
		assertEquals("Posição 2 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A6")));		
		
		//Caso Submarino
		tabuleiro = new Tabuleiro();
		gabarito = 1;
		Embarcacao submarino = new Submarino();
		submarino.Posicionar(tabuleiro, new Coordenada("A5"));
		
		assertEquals("Posição 1 inválida",gabarito,tabuleiro.getCasa(new Coordenada("A5")));	
	}

	public void validaPosicao() {
		
	}
}
