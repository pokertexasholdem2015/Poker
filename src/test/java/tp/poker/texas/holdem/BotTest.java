import org.junit.BeforeClass;
import org.junit.Test;
import tp.poker.texas.holdem.Bot;
import junit.framework.TestCase;

public class BotTest extends TestCase {
	private Table stol;
	private Player gracze;
	private Card karta1, karta2,karta3,karta4,karta5; 
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		// ustawianie kart do sprawdzenia reakcji na uklad = kolor
		karta1 = new Card(0, 0);
		karta2 = new Card(0, 1);
		karta3 = new Card(0, 2);
		karta4 = new Card(0, 6);
		karta5 = new Card(0, 4);
		// gracze w tym bot 
		Player gracze[] = new Player[1];
		gracze[0] = new Bot();
		// odebranie 5 kart przez bota do tablicy reki
		gracze[0].odbierzKarte(karta1);
		gracze[0].odbierzKarte(karta2);
		gracze[0].odbierzKarte(karta3);
		gracze[0].odbierzKarte(karta4);
		gracze[0].odbierzKarte(karta5);
		
	}
	
	@Test
	
	public void  TestLicytacji() {
		//zwraca 2 dla licytacji przy ukladzie kolor 
		assertEquals(2, gracze[0].BotBetStrategy(stol));
	}
	
	
}
