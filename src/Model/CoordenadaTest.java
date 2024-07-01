package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordenadaTest {
	
	@Test
	public void test() {
		Coordenada novaCoordenada = new Coordenada(3, 7);
	    assertEquals(3, novaCoordenada.getColuna());
	    assertEquals(7, novaCoordenada.getLinha());
	}
}
