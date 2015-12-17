package tp.poker.texas.holdem;

import org.junit.Test;

import junit.framework.TestCase;

public class CardTest extends TestCase {
	private Card karta;
	
	protected void setUp() {
		karta = new Card(0, 0);
	}
	
	protected void tearDown() {
		karta = null;
	}
	
	
	//Test czy obiekt klasy Card jest tworzony.
	@Test
	public void testKarty() {
		assertNotNull(karta);
	}
	
	/* Testy metod klasy Card */
	
	//Test metody String nazwaKarty().
	@Test
	public void testMtdNazwaKarty() {
		assertEquals("dwojka trefl", karta.nazwaKarty());
	}
	
	//Test metody int getKolor()
	@Test
	public void testMtdGetKolor() {
		assertEquals(0, karta.getKolor());
	}
	
	//Test metody int getNumFig()
	@Test
	public void testMtdGetNumFig() {
		assertEquals(0, karta.getNumFig());
	}
}