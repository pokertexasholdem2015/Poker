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
		
		//tutaj beda zagrania gracza tzn check, bet, raise, call, fold albo all-in// EDIT: SA PONIZEJ
		while (flaga) {
			try {
				linia = dane_przychodzace.readLine().split(" ");
				if(!k_svr.moze_grac.get(Client_ID) || k_svr.etap == 1) continue;
				// CHECK
				if(linia[0].equals("check"))
				{
					if(k_svr.gracz_perm == Client_ID)
					{
						if(k_svr.etap == 2 || k_svr.etap == 4)
						{
							if(k_svr.stol.check == true)
							{
								k_svr.sendMessageToAll("MSG Gracz "+Client_ID+" czeka.");
								k_svr.nextStepGame(false);
							}
							else
								sendMessage("MSG Stawka zostala juz wniesiona. Komenda niedostepna.");
						}
						else
							sendMessage("MSG Komende mozna uzyc tylko podczas licytacji.");
					}
					else
						sendMessage("MSG Komenda niedostepna. Poczekaj na swoja kolej.");
				}
				
				// BET - pierwsza stawka [przyjmuje tylko jeden parametr]
				else if(linia[0].equals("bet"))
				{
					if(k_svr.gracz_perm == Client_ID)
					{
						if(k_svr.stol.check && (k_svr.etap == 2 || k_svr.etap == 4) && !k_svr.stol.ALLin)
						{
							try{
								if(k_svr.stol.wezZetony(k_svr.gracze[Client_ID], Integer.parseInt(linia[1]), 0))
								{
									k_svr.sendMessageToAll("MSG Gracz "+Client_ID+" stawia pierwsze stawke. Stawka "+linia[1]+".");
									k_svr.nextStepGame(false);
									System.out.println("Stawka na stole "+k_svr.stol.stawka);
								}
								else
									sendMessage("MSG Podana liczba jest wieksza od ilosci zetonow na Twoim koncie lub ujemna");
							}
							catch(NumberFormatException e)
							{
								sendMessage("MSG Zostal wprowadzony zly format liczby calkowitej.");
							}
						}
						else
							sendMessage("MSG Komenda niedostepna w tym etapie gry.");
					}
					else
						sendMessage("MSG Komenda niedostepna. Poczekaj na swoja kolej.");
				}
				
				else if(linia[0].equals("raise"))
				{
					if(k_svr.gracz_perm == Client_ID)
					{
						if(!k_svr.stol.check && (k_svr.etap == 2 || k_svr.etap == 4))
						{
							try
							{
								if(k_svr.stol.wezZetony(k_svr.gracze[Client_ID], Integer.parseInt(linia[1]), 2))
								{
									k_svr.sendMessageToAll("MSG Gracz "+Client_ID+" podbija do "+linia[1]+".");
									k_svr.nextStepGame(false);
								}
								else
									sendMessage("MSG Komenda niedostepna w tym etapie gry.");
							}
							catch(NumberFormatException e)
							{
								sendMessage("MSG Zostal wprowadzony zly format liczby calkowitej.");
							}
						}
						else
							sendMessage("MSG Komenda niedostepna w tym etapie gry.");
					}
					else
						sendMessage("MSG Komenda niedostepna. Poczekaj na swoja kolej.");
				}
				
				else if(linia[0].equals("call"))
				{
					if(k_svr.gracz_perm == Client_ID)
					{
						if(!k_svr.stol.check && (k_svr.etap == 2 || k_svr.etap == 4))
						{
							if(k_svr.stol.wezZetony(k_svr.gracze[Client_ID], 0, 1))
							{
								k_svr.sendMessageToAll("MSG Gracz "+Client_ID+" wyrownuje.");
								k_svr.nextStepGame(false);
							}
							else
								sendMessage("MSG Komenda niedostepna w tym etapie gry.");
						}
						else
							sendMessage("MSG Komenda niedostepna w tym etapie gry.");
					}
					else
						sendMessage("MSG Komenda niedostepna. Poczekaj na swoja kolej.");
				}
				
				else if(linia[0].equals("fold"))
				{
					if(k_svr.gracz_perm == Client_ID)
					{
						if((k_svr.etap == 2 || k_svr.etap == 4))
						{
							k_svr.sendMessageToAll("MSG Gracz "+Client_ID+" wycofuje sie z gry.");
							k_svr.moze_grac.clear(Client_ID);
							k_svr.nextStepGame(false);
						}
						else
							sendMessage("MSG Komenda niedostepna w tym etapie gry.");
					}
					else
						sendMessage("MSG Komenda niedostepna. Poczekaj na swoja kolej.");
				}
				
				else if(linia[0].equals("allin"))
				{
					if(k_svr.gracz_perm == Client_ID)
					{
						if(!k_svr.stol.check && (k_svr.etap == 2 || k_svr.etap == 4))
						{
							if(k_svr.stol.wezZetony(k_svr.gracze[Client_ID], 0, 3))
							{
								k_svr.sendMessageToAll("MSG Gracz "+Client_ID+" wchodzi ALL IN.");
								k_svr.nextStepGame(false);
							}
							else
								sendMessage("MSG Komenda niedostepna w tym etapie gry.");
						}
						else
							sendMessage("MSG Komenda niedostepna w tym etapie gry.");
					}
					else
						sendMessage("MSG Komenda niedostepna. Poczekaj na swoja kolej.");
				}
				
				else if(linia[0].equals("zamienkarty"))
				{
					if(k_svr.gracz_perm == Client_ID)
					{
						if(k_svr.etap == 3)
						{
							try{
								if(Integer.parseInt(linia[1]) < 5 && Integer.parseInt(linia[1]) > 0)
								{
									for(int i = 0; i < Integer.parseInt(linia[1]); i++)
									{
										k_svr.stol.zamienKarte(k_svr.gracze[Client_ID], Integer.parseInt(linia[i+2]));
									}
									k_svr.sendMessageToAll("MSG Gracz "+Client_ID+" zamienil karty.");
									k_svr.nextStepGame(false);
								}
								else if(Integer.parseInt(linia[1]) == 0)
								{
									k_svr.sendMessageToAll("MSG Gracz "+Client_ID+" nie zamienil kart.");
									k_svr.nextStepGame(false);
								}
							}
							catch(NumberFormatException e)
							{
								sendMessage("MSG Niewlasciwy format liczby.");
							}
						}
						else
							sendMessage("MSG Komenda niedostepna w tym etapie gry.");
					}
					else
						sendMessage("MSG Komenda niedostepna. Poczekaj na swoja kolej.");
				}
				
				else if(linia[0].equals("pokazkarty"))
				{
					if(k_svr.etap >= 2 && k_svr.etap <= 4)
					{
						karty_send = "KRT";
						for(int i=0; i < 5; i++)
						{
							karty_send += " "+k_svr.gracze[Client_ID].pokazKarte(i);
						}
						sendMessage(karty_send);
					}
					else
						sendMessage("MSG Komenda niedostepna.");
				}
				else if(linia[0].equals("pokazzetony"))
				{
					if(k_svr.etap >= 2 && k_svr.etap <= 4)
					{
						karty_send = "ZET "+k_svr.gracze[Client_ID].Zetony();
						sendMessage(karty_send);
					}
					else
						sendMessage("MSG Komenda niedostepna.");
				}
				else{
					sendMessage("MSG Nie ma takiego polecenia.");
				}
								
			} 
			catch (IOException | NullPointerException e) {
				System.out.println ("Odczytanie klienta nie powiodlo sie");
				disconnectClient();
			} 
		}
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
		
		@SuppressWarnings("deprecation")
		public void disconnectClient() {
			try {
				System.out.println("Rozlaczenie Gracza "+Client_ID);
				k_svr.sendMessageToAll("MSG Gracz "+Client_ID+" zostal rozlaczony.");
				k_svr.gracze[Client_ID] = null;
				k_svr.moze_grac.clear(Client_ID);
				if(Client_ID == k_svr.gracz_perm)
					k_svr.nextStepGame(true);
				k_svr.klient[Client_ID] = null;
				socket_klient.close();
				t.stop();
			}
			catch(IOException e){
				System.out.println ("Rozlaczenie klienta "+socket_klient.getRemoteSocketAddress()+" niepowiadlo sie");
			}
		}
}