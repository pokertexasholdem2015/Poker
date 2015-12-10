package tp.poker.texas.holdem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KlientPoker implements Runnable {
	// zmienne potrzebne do okreslenia klienta i wiadomosci in and out
	Socket socket_klient = null;
	BufferedReader dane_przychodzace = null;
	PrintWriter dane_wychodzace = null;
	SerwerPoker k_svr = null;
	Thread t;
		
	// parametry klienta
	short Client_ID;
	//int wygrane=0;
	String[] linia;
	String karty_send;
	boolean flaga=true;

	KlientPoker(Socket socket, SerwerPoker server) {
		socket_klient = socket;
	    k_svr = server;
	    t = new Thread(this);
	    t.start();
	}

	public void run() {	
		try {
			dane_przychodzace = new BufferedReader (new InputStreamReader (socket_klient.getInputStream()));
			dane_wychodzace = new PrintWriter (socket_klient.getOutputStream(), true);
		} 
		catch (IOException e) {
			System.out.println ("Accept failed"); 
		}
		
//tutaj beda zagrania gracza tzn check, bet, raise, call, fold albo all-in
	}
		
		public void sendMessage(String wiadomosc)
		{
			try{
				dane_wychodzace = new PrintWriter(socket_klient.getOutputStream(), true);
				dane_wychodzace.println(wiadomosc);
				System.out.println("INFO: Wiadomosc do klienta " + socket_klient.getRemoteSocketAddress() + " : " + wiadomosc);
			}
			catch(IOException e){
				System.out.println("ERROR: Wiadomosc do klienta " + socket_klient.getRemoteSocketAddress() + " nie zostala wyslana");
			}
		}
}