package Testes;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Coordenada;
import Model.Couracado;
import Model.Cruzador;
import Model.Destroyer;
import Model.Embarcacao;
import Model.Hidroaviao;
import Model.Submarino;
import Model.Tabuleiro;

public class EmbarcacaoTeste {

	@Test
	public void PosicionarTest() {

		// Caso couraçado
		Tabuleiro tabuleiro = new Tabuleiro();
		int gabarito = 5;

		Embarcacao couracado = new Couracado();
		couracado.Posicionar(tabuleiro, new Coordenada(5, 0));
		assertEquals("Posição 1 inválida", gabarito, tabuleiro.getCasa(new Coordenada(5, 0)));
		assertEquals("Posição 2 inválida", gabarito, tabuleiro.getCasa(new Coordenada(6, 0)));
		assertEquals("Posição 3 inválida", gabarito, tabuleiro.getCasa(new Coordenada(7, 0)));
		assertEquals("Posição 4 inválida", gabarito, tabuleiro.getCasa(new Coordenada(8, 0)));
		assertEquals("Posição 5 inválida", gabarito, tabuleiro.getCasa(new Coordenada(9, 0)));

		// Caso Cruzador
		tabuleiro = new Tabuleiro();
		gabarito = 4;
		Embarcacao cruzador = new Cruzador();
		cruzador.Posicionar(tabuleiro, new Coordenada(5, 0));

		assertEquals("Posição 1 inválida", gabarito, tabuleiro.getCasa(new Coordenada(5, 0)));
		assertEquals("Posição 2 inválida", gabarito, tabuleiro.getCasa(new Coordenada(6, 0)));
		assertEquals("Posição 3 inválida", gabarito, tabuleiro.getCasa(new Coordenada(7, 0)));
		assertEquals("Posição 4 inválida", gabarito, tabuleiro.getCasa(new Coordenada(8, 0)));

		// Caso Hidroavião
		tabuleiro = new Tabuleiro();
		gabarito = 3;
		Embarcacao hidroaviao = new Hidroaviao();
		hidroaviao.Posicionar(tabuleiro, new Coordenada(5, 0));

		assertEquals("Posição superior inválida", gabarito, tabuleiro.getCasa(new Coordenada(5, 0)));
		assertEquals("Posição esquerda inválida", gabarito, tabuleiro.getCasa(new Coordenada(4, 1)));
		assertEquals("Posição direita inválida", gabarito, tabuleiro.getCasa(new Coordenada(6, 1)));

		// Caso Destroyer
		tabuleiro = new Tabuleiro();
		gabarito = 2;
		Embarcacao destroyer = new Destroyer();
		destroyer.Posicionar(tabuleiro, new Coordenada(5, 0));

		assertEquals("Posição 1 inválida", gabarito, tabuleiro.getCasa(new Coordenada(5, 0)));
		assertEquals("Posição 2 inválida", gabarito, tabuleiro.getCasa(new Coordenada(6, 0)));

		// Caso Submarino
		tabuleiro = new Tabuleiro();
		gabarito = 1;
		Embarcacao submarino = new Submarino();
		submarino.Posicionar(tabuleiro, new Coordenada(5, 0));

		assertEquals("Posição 1 inválida", gabarito, tabuleiro.getCasa(new Coordenada(5, 0)));
	}

	@Test
	public void ValidaPosicionarTest() {
		// Caso inserção em casa ja ocupada
		Tabuleiro tabuleiro = new Tabuleiro();
		Embarcacao sub = new Submarino();
		Embarcacao couracado = new Couracado();

		sub.Posicionar(tabuleiro, new Coordenada(5, 0));
		couracado.Posicionar(tabuleiro, new Coordenada(5, 0));
		assertEquals("Embarcação inserida em cima de outra", sub.getTipo(), tabuleiro.getCasa(new Coordenada(5, 0)));

		// Inserção adjacente
		tabuleiro = new Tabuleiro();
		couracado.Posicionar(tabuleiro, new Coordenada(0, 0));
		sub.Posicionar(tabuleiro, new Coordenada(0, 1));
		assertEquals("Embarcação inserida adjacente a outra", tabuleiro.getCasa(new Coordenada(0, 1)), 0);

		// Validação no valor limite (A1 e O15)
		tabuleiro = new Tabuleiro();
		sub.Posicionar(tabuleiro, new Coordenada(0, 0));
		assertEquals("Erro ao inserir em A1", sub.getTipo(), tabuleiro.getCasa(new Coordenada(0, 0)));
		couracado.Posicionar(tabuleiro, new Coordenada(14, 14));
		//tabuleiro.ExibeTabuleiro();
		assertEquals("Inserção indevida em O15", 0, tabuleiro.getCasa(new Coordenada(14, 14)));
	}
}
