package tp.poker.texas.holdem;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;


public class TableTest extends TestCase {
	private Table stol;
	private Player gracze[];
	private Human gracz;
	private Card karta;
	private int zetony = 5;
	
	
	protected void setUp() {
		gracze = new Player[1];
		stol = new Table(gracze);	
		karta = new Card(1, 1);
		gracz = new Human();
		
		//gracze[1].SmallBlind = true;
	}
	
	protected void tearDown() {
		gracze = null;
		stol = null;
		karta = null;
	}
	
	@Test
	public void testMtdPorownajWszystGraczy() {
		
	}
	
	
	
	
	
	@Test
	public void testMtdUnsetButtons() {
		//gracze[0].DealerButton = true;
		assertNotNull(stol);
		//gracze[0].DealerButton = true;
		//stol.UnsetButtons(gracze[0]);
		//assertEquals(false, gracze[0].DealerButton);
		
		//stol.UnsetButtons(gracz);
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
	
}