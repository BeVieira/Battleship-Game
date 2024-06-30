package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJogador {

	@Test
	public void test() {
		Jogador j = new Jogador();
		Coordenada c = new Coordenada(1, 1);
		assertTrue("Erro no tiro inicial", j.realizarTiro(c) > 0);
		assertFalse("Tiro invalido nao tratado", j.realizarTiro(c) < 0);
	}

}
