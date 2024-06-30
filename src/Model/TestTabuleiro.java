package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTabuleiro {

	@Test
	public void test() {
		Tabuleiro tab = new Tabuleiro();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				Coordenada c = new Coordenada(j, i);
				assertEquals("Inicializacao incorreta", tab.getCasa(c), 0);
			}
		}
	}

}
