package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmbarcacaoTest {

	@Test
	public void PosicionarTest() {

		// Caso couraçado
		Tabuleiro tabuleiro = new Tabuleiro();
		int gabarito = 5;

		Embarcacao couracado = new Couracado();
		couracado.setPosicao(new Coordenada(5, 0));
		tabuleiro.posicionarEmbarcacao(couracado, 1);
		assertEquals("Posição 1 inválida", gabarito, tabuleiro.getCasa(new Coordenada(5, 0)));
		assertEquals("Posição 2 inválida", gabarito, tabuleiro.getCasa(new Coordenada(6, 0)));
		assertEquals("Posição 3 inválida", gabarito, tabuleiro.getCasa(new Coordenada(7, 0)));
		assertEquals("Posição 4 inválida", gabarito, tabuleiro.getCasa(new Coordenada(8, 0)));
		assertEquals("Posição 5 inválida", gabarito, tabuleiro.getCasa(new Coordenada(9, 0)));

		// Caso Cruzador
		tabuleiro = new Tabuleiro();
		gabarito = 4;
		Embarcacao cruzador = new Cruzador();
		cruzador.setPosicao(new Coordenada(0, 5));
		tabuleiro.posicionarEmbarcacao(cruzador, 2);

		assertEquals("Posição 1 inválida", gabarito, tabuleiro.getCasa(new Coordenada(0, 5)));
		assertEquals("Posição 2 inválida", gabarito, tabuleiro.getCasa(new Coordenada(0, 4)));
		assertEquals("Posição 3 inválida", gabarito, tabuleiro.getCasa(new Coordenada(0, 3)));
		assertEquals("Posição 4 inválida", gabarito, tabuleiro.getCasa(new Coordenada(0, 2)));

		// Caso Hidroavião
		tabuleiro = new Tabuleiro();
		gabarito = 3;
		Embarcacao hidroaviao = new Hidroaviao();
		hidroaviao.setPosicao(new Coordenada(1, 1));
		tabuleiro.posicionarEmbarcacao(hidroaviao, 3);

		assertEquals("Posição superior inválida", gabarito, tabuleiro.getCasa(new Coordenada(1, 1)));
		assertEquals("Posição esquerda inválida", gabarito, tabuleiro.getCasa(new Coordenada(0, 0)));
		assertEquals("Posição direita inválida", gabarito, tabuleiro.getCasa(new Coordenada(2, 0)));

		// Caso Destroyer
		tabuleiro = new Tabuleiro();
		gabarito = 2;
		Embarcacao destroyer = new Destroyer();
		destroyer.setPosicao(new Coordenada(14,0));
		tabuleiro.posicionarEmbarcacao(destroyer, 4);

		assertEquals("Posição 1 inválida", gabarito, tabuleiro.getCasa(new Coordenada(14, 0)));
		assertEquals("Posição 2 inválida", gabarito, tabuleiro.getCasa(new Coordenada(14, 1)));

		// Caso Submarino
		tabuleiro = new Tabuleiro();
		gabarito = 1;
		Embarcacao submarino = new Submarino();
		submarino.setPosicao(new Coordenada(5, 0));
		tabuleiro.posicionarEmbarcacao(submarino, 1);

		assertEquals("Posição 1 inválida", gabarito, tabuleiro.getCasa(new Coordenada(5, 0)));
	}

	@Test
	public void ValidaPosicionarTest() {
		// Caso inserção em casa ja ocupada
		Tabuleiro tabuleiro = new Tabuleiro();
		Embarcacao sub = new Submarino();
		Embarcacao couracado = new Couracado();
		
		sub.setPosicao(new Coordenada(5, 0));
		tabuleiro.posicionarEmbarcacao(sub, 1);
		
		assertFalse(tabuleiro.validaPosicionar(new Coordenada(5, 0), 4, 1));

		// Inserção adjacente
		assertFalse(tabuleiro.validaPosicionar(new Coordenada(6, 0), 4, 1));

		// Validação no valor limite (A1 e O15)
		//Posicionamento em A1 (orientaçao permitida e não permitado na horizontal)
		assertTrue(tabuleiro.validaPosicionar(new Coordenada(0, 0), 4, 1));
		assertFalse(tabuleiro.validaPosicionar(new Coordenada(0, 0), 4, 3));
		
		//Posicionamento em O15 (orientaçao permitida e não permitado na vertical)
		assertTrue(tabuleiro.validaPosicionar(new Coordenada(14, 14), 4, 2));
		assertFalse(tabuleiro.validaPosicionar(new Coordenada(14, 14), 4, 4));
	}
}
