package tp.poker.texas.holdem;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TableTest {
	Table stol;
	Player gracze[];
	Card karta;
	
	
	public void setUp() {
		//gracze = new Player[2];
		stol = new Table(gracze = new Player[2]);
		karta = new Card(1, 1);
		gracze[0].DealerButton = true;
		gracze[1].SmallBlind = true;
	}
	
	public void tearDown() {
		stol = null;
	}
	
	@Test
	public void testUnsetButtons() {
		stol.UnsetButtons(gracze[0]);
		assertEquals(false, gracze[0].DealerButton);
		
		//stol.UnsetButtons(gracz);
	}
	
	@Test
	public void testOdbierzKarte() {
		gracze[0].odbierzKarte(karta);
	}
}