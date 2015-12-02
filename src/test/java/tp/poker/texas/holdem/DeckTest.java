package tp.poker.texas.holdem;

import org.junit.Test;

import junit.framework.TestCase;

public class DeckTest extends TestCase {
	private Deck talia;
	
	public void setUp() {
		talia = new Deck();
	}
	
	public void tearDown() {
		talia = null;
	}
	
	//Test czy obiekt klasy Deck jest tworzony.
	@Test
	public void testTalii() {
		assertNotNull(talia);
	}
	
	//Test wyciagniecia 4 kart z talii i wyswietlenie talii po wyciagnieciu tych kart.
	@Test
	public void testWezZTalii() {
		talia.pokazKarty();
		for(int i = 0; i < 4; i++) {
			talia.wezZTalii();
		}
		talia.pokazKarty();
	}
		
	//Test metody, ktora wrzuca do talii karte.
	@Test
	public void testMtdWrzucDoTalii() {
		Card karta1, karta2, karta3;
		karta1 = new Card(1, 0);
		karta2 = new Card(2, 11);
		karta3 = new Card(3, 5);
		for(int i = 0; i < 52; i++) {
			talia.wezZTalii();
		}
		talia.wrzucDoTalii(karta1);
		talia.wrzucDoTalii(karta2);
		talia.wrzucDoTalii(karta3);
		talia.pokazKarty();
	}
	
	//Test metody, ktora pokazuje potasowane karty.
	@Test
	public void testMtdPokazKarty() {
		talia.pokazKarty();
	}
}