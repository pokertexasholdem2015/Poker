package tp.poker.texas.holdem;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;


public class TableTest extends TestCase {
	private Human gracz;
	private Human gracz2;
	private Human gracz3;
	private Human gracz4;
	private Deck talia;
	private Card reka[];
	private Player[] gracze;
	private Table stol;
	private int zetony = 5;
	
	protected void setUp() throws Exception {
		gracz = new Human();
		gracz2 = new Human();
		gracz3 = new Human();
		gracz4 = new Human();
		gracze = new Player[4];
		talia = Deck.pobierzTalie();
		reka = new Card[5];
		stol = new Table(gracze);
	}
	
	protected void tearDown() {
		gracz = null;
		gracz2 = null;
		gracz3 = null;
		gracz4 = null;
		talia = null;
		reka = null;
	}
	
	
	@Test
	public void testMtdUnsetButtons() {
		gracz.DealerButton = true;
		gracz.SmallBlind = true;
		stol.UnsetButtons(gracz);
		boolean zmieniony = gracz.DealerButton;
		assertEquals(false, zmieniony);
		assertFalse(zmieniony);
	}
	
	@Test
	public void testMtdSetBigBlind() {
		gracz.BigBlind = false;
		stol.SetBigBlind(gracz);
		boolean zmienionyBigB = gracz.BigBlind;
		assertEquals(true, zmienionyBigB);
		assertTrue(zmienionyBigB);
	}
	
	@Test
	public void testMtdSetSmallBlind() {
		gracz.SmallBlind = false;
		stol.SetSmallBlind(gracz);
		boolean zmienionySmallB = gracz.SmallBlind;
		assertEquals(true, zmienionySmallB);
		assertTrue(zmienionySmallB);
	}
	
	@Test
	public void testMtdGetPula() {
		stol.giveZetony(zetony);
		assertEquals(5, stol.getPula());
	}
	
	@Test
	public void testMtdGiveZetony() {
		stol.giveZetony(zetony);
	}
	
	@Test
	public void testOddajPuleWygr() {
		stol.oddajPuleWygr(gracz);
	}
	
	@Test
	public void testMtdPorownajWszystGraczy() {
		// gracz ma uklad Trojka
		gracz.odbierzKarte(new Card(0, 9));
		gracz.odbierzKarte(new Card(1, 9));
		gracz.odbierzKarte(new Card(3, 9));
		gracz.odbierzKarte(new Card(2, 5));
		gracz.odbierzKarte(new Card(0, 6));
		
		// gracz2 ma uklad Strit
		gracz2.odbierzKarte(new Card(0, 2));
		gracz2.odbierzKarte(new Card(1, 3));
		gracz2.odbierzKarte(new Card(3, 4));
		gracz2.odbierzKarte(new Card(2, 5));
		gracz2.odbierzKarte(new Card(0, 6));
		
		// gracz3 ma uklad Poker
		gracz3.odbierzKarte(new Card(0, 0));
		gracz3.odbierzKarte(new Card(0, 1));
		gracz3.odbierzKarte(new Card(0, 2));
		gracz3.odbierzKarte(new Card(0, 3));
		gracz3.odbierzKarte(new Card(0, 4));
			
		// gracz4 ma uklad Dwie Pary
		gracz4.odbierzKarte(new Card(0, 8));
		gracz4.odbierzKarte(new Card(1, 8));
		gracz4.odbierzKarte(new Card(3, 4));
		gracz4.odbierzKarte(new Card(2, 4));
		gracz4.odbierzKarte(new Card(0, 11));
		
		gracze[0] = gracz;
		gracze[1] = gracz2;
		gracze[2] = gracz3;
		gracze[3] = gracz4;
		int najlepszy = stol.porownajWszystGraczy(gracze);
		
		assertEquals(2, najlepszy);
		assertSame(2, najlepszy);
		System.out.println("Najlepszy uklad ma gracz: " + najlepszy);
	}
	
	public void testMtdCompareAllPlayers() {
		// gracz ma uklad Trojka
		gracz.odbierzKarte(new Card(0, 9));
		gracz.odbierzKarte(new Card(1, 9));
		gracz.odbierzKarte(new Card(3, 9));
		gracz.odbierzKarte(new Card(2, 5));
		gracz.odbierzKarte(new Card(0, 6));
				
		// gracz2 ma uklad Strit
		gracz2.odbierzKarte(new Card(0, 2));
		gracz2.odbierzKarte(new Card(1, 3));
		gracz2.odbierzKarte(new Card(3, 4));
		gracz2.odbierzKarte(new Card(2, 5));
		gracz2.odbierzKarte(new Card(0, 6));
				
		// gracz3 ma uklad Poker
		gracz3.odbierzKarte(new Card(0, 0));
		gracz3.odbierzKarte(new Card(0, 1));
		gracz3.odbierzKarte(new Card(0, 2));
		gracz3.odbierzKarte(new Card(0, 3));
		gracz3.odbierzKarte(new Card(0, 4));
					
		// gracz4 ma uklad Dwie Pary
		gracz4.odbierzKarte(new Card(0, 8));
		gracz4.odbierzKarte(new Card(1, 8));
		gracz4.odbierzKarte(new Card(3, 4));
		gracz4.odbierzKarte(new Card(2, 4));
		gracz4.odbierzKarte(new Card(0, 11));
				
		gracze[0] = gracz;
		gracze[1] = gracz2;
		gracze[2] = gracz3;
		gracze[3] = gracz4;
		BitSet wygr = stol.compareAllPlayers(gracze);
		String wygrr = wygr.toString();
		
		assertEquals("{2}", wygrr);
		System.out.println(wygrr);
	}
}