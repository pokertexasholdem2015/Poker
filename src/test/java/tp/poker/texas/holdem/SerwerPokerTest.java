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
	
	//do podlaczenia
	int ip, port;
	static Socket socket = null;
	

	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		// do serwera
		socket_serwer = null;
		klient = new KlientPoker[5];
		
	
		
		// do podlaczania
		//socket = new Socket(ip,port);
		
	}
	
	@Test
	public void testPodlaczenia(){
		
	}
	
	//Uruchomienie serwera
	@Test(expected=NumberFormatException.class)
	public void testMtdMain() {
		SerwerPoker.main(new String[] {"2", "0", "15", "5"});
	}
	
}
