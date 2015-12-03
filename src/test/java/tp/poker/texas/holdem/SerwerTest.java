
package tp.poker.texas.holdem;

import java.net.ServerSocket;
import java.net.Socket;

import org.junit.BeforeClass;
import org.junit.Test;

import tp.poker.texas.holdem.SerwerPoker;
import junit.framework.TestCase;

public class SerwerTest extends TestCase {
	//do serwera
	private ServerSocket socket_serwer;
	private SrvClient klient[];
	
	//do podlaczenia
	int ip,port;
	static Socket socket = null;
	

	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		// do serwera
		socket_serwer = null;
		SrvClient klient[] = new SrvClient[5]; // moze byc maksymalnie 5ciu klientow
		
		Player gracz[] = new Player[5]; 
		Table stol;
	
		
		// do podlaczania
		//socket = new Socket(ip,port);
		
	}
	
	@Test
	public void testPodlaczenia(){
		
	}
	
	
}
