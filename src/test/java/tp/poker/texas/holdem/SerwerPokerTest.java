package tp.poker.texas.holdem;

import java.net.ServerSocket;
import java.net.Socket;

import org.junit.BeforeClass;
import org.junit.Test;

import tp.poker.texas.holdem.SerwerPoker;
import junit.framework.TestCase;

public class SerwerPokerTest extends TestCase {
	//do serwera
	private ServerSocket socket_serwer;
	private KlientPoker klient[];
	private Player gracz[] = new Player[5]; 
	private Table stol;
	private String ileKlientow = "3";
	private String ileBotow = "0";
	private String iloscZetonow = "15";
	private String wysokoscWpisowego = "5";
	private SerwerPoker serwer;
	
	//do podlaczenia
	int ip, port;
	static Socket socket = null;
	

	@BeforeClass
	public final void setUpBeforeClass() throws Exception {
		// do serwera
		socket_serwer = null;
		klient = new KlientPoker[10];
		
	
		
		// do podlaczania
		//socket = new Socket(ip,port);
		
	}
	
	@Test
	public final void testMtdNextStepGame() {
		serwer = new SerwerPoker(3535);
		int x = serwer.nextStepGame(true);
	}
	
	
	@Test
	public void testPodlaczenia(){
		
	}
	
	//Uruchomienie serwera
	@Test(expected=NumberFormatException.class)
	public final void testMtdMain() {
		SerwerPoker.main(new String[] {ileKlientow, ileBotow, iloscZetonow, wysokoscWpisowego});
	}
}