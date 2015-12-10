package tp.poker.texas.holdem;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {

	private Player[] gracze;
	private Card karta;
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		karta = new Card(0,0);
		
		gracze = new Player[1];
		
	}
	
	@Test
	public void TestOdbioruKarty(){
		gracze[0].odbierzKarte(karta);
//		assertEquals(gracz.reka[0].nazwaKarty(), "dwojka trefl");
	}
}
