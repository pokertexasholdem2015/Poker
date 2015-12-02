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
	
	@Test
	public void testMtdPokazKarty() {
		talia.pokazKarty();
	}
	
	//Test wyciagniecia 5 kart z talii.
	@Test
	public void testWezZTalii() {
		talia.pokazKarty();
		for(int i = 0; i < 4; i++) {
			talia.wezZTalii();
		}
		talia.pokazKarty();
	}
}