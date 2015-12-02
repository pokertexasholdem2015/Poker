package tp.poker.texas.holdem;

import org.junit.Test;

import junit.framework.TestCase;

public class CardTest extends TestCase {
	@Test
	public void testKarty() {
		Card karta = new Card(2,2);
		assertNotNull(karta);
	}
}