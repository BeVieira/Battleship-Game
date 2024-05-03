package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJogador {

	@Test
	public void test() {
		Jogador j = new Jogador("Teste");
		Coordenada c = new Coordenada(1, 1);
		assertTrue("Erro no tiro inicial", j.Atirar(c));
		assertFalse("Tiro invalido nao tratado", j.Atirar(c));
	}

}
