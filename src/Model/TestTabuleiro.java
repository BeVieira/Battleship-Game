package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTabuleiro {
	
	@Test
	public void test() {
		Tabuleiro tab = new Tabuleiro();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; i++){
				assertEquals("Inicializacao incorreta",tab.tabuleiro[i][j],0);
			}
		}
	}

}
