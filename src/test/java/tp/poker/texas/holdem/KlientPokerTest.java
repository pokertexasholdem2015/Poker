package tp.poker.texas.holdem;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Ignore;
import org.junit.Test;

public class KlientPokerTest {
	public Socket socket;
	public SerwerPoker server;
	private int port = 3535;
	public KlientPoker klient;
	private String serverAddress ="";
	
	
	public final void setUp() {
		socket = new Socket();	
	}
	
	public final void tearDown() {
		socket = null;
	}
	
	
	//Aby wykonac ten test musi byc uruchomiony serwer wlasnie poprzez wykonanie testu SerwerPokerTest
	//@Ignore 
	@Test
	public void testPodlaczeniaKlienta() throws UnknownHostException, IOException {
		socket = new Socket(serverAddress, port);	
	}
}