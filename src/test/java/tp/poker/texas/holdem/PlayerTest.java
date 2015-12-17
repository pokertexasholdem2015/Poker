package tp.poker.texas.holdem;
import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	private Human gracze[];
	private Card karta;
	
	protected void setUp() throws Exception {
		karta = new Card(1,1);	
		gracze = new Human[2];
	}
	
	protected void tearDown() {
		karta = null;
		gracze = null;
	}
	
	@Test
	public void testOdbioruKarty(){
		//gracz.zniszczKarty();
		gracze[0].odbierzKarte(karta);
		//gracz.pokazKarty();
//		assertEquals(, "dwojka trefl");
	}
}