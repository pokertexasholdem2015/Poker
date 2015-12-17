package tp.poker.texas.holdem;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.BitSet;
import java.util.Random;


public class SerwerPoker {
	ServerSocket socket_serwer = null;
	KlientPoker klient[] = new KlientPoker[10];
	Deck deck = new Deck();
	Card reka[] = new Card[7];
	
	Player gracze[] = new Player[10];
	BitSet moze_grac = new BitSet(10);
	Table stol;
	//Table stol = new Table(gracze[], small, big);
	int graczDealer;
	
	// parametry gry
	static short ileKlientow=0;
	//short ilePolaczonych = 0;
	static short ileBotow=0;
	static int wysokoscWpisowego=0;
	static int iloscZetonow=0;
	int zetonyKasyna=0;
	
	int etap=1;
	int start_game = 0;
	int gracz_perm = 0;
	BitSet winners = new BitSet(10);
	
	// kostruktor serwera
	SerwerPoker(int port){
		try {
			socket_serwer = new ServerSocket(port);
			System.out.println("Czekam na klientow...");
			listenSocket();
		}
		catch(IOException e) {
			System.out.println ("Nie mozna nasluchiwac portu " + port); 
			System.exit (-1);
		}
	}
	
	// funkcja czekajaca na polaczenie wszystkich graczy i zaczynaj�ca gre
	public void listenSocket() {
		int clients = 0;
		moze_grac.clear();
		while (clients < ileKlientow) {
            try {
            	
                klient[clients] = new KlientPoker(socket_serwer.accept(), this);
                klient[clients].Client_ID = (short)clients;
                
                gracze[clients] = new Human();
                gracze[clients].dajZetony(iloscZetonow);
                moze_grac.set(clients);
                
                sendMessageToAll("MSG Nowy gracz (ID: " + clients + ") dolaczyl do gry");
				clients++;
            } 
            catch (IOException e) {
    			System.out.println("Accept failed"); 
    		}
		}
		for(int i = 0; i < ileBotow; i++) {
			gracze[ileKlientow + i] = new Bot();
			gracze[ileKlientow + i].dajZetony(iloscZetonow);
			moze_grac.set(ileKlientow + i);
		}
		
		nextStepGame(false);
		
	}
	
	// zakonczenie dzialania serwera
	protected void finalize() {
		try {
			// rozlaczyc wszystkich klientow
			socket_serwer.close();
		} 
		catch(IOException e) {
			System.out.println("Zamkniecie nie przebieglo pomyslnie."); 
			System.exit(-1);
		}
	}
	
	public int nextStepGame(boolean przel) {
		/*lista wydarzen
		 * Sprawdzenie trybu
		 * wyslanie wiadomosci do klientow o trybie gry
		 * przyznanie Dealer buttona i smalla
		 * Po 2 karty ( jest w konstruktorze Table rozdanie kart graczom ew. mozna zamienic na metode w table)
		 * Wyswietlanie stringa kto jest na small i big (oprocz grafiki gui) 
		 * pierwsza licytacja z uwzglednieniem small i big 
		 * karta na stol
		 * druga licytacja z big small
		 * karta na stol
		 * i tak do ostatniej karty
		 * wtedy wywolanie Ostatniego etapu 
		 * Wylonienie zwyciezcy (sa metody w table )
		 * Przekazanie stawki 
		 * wynullowanie stolu tj kart i innych zmiennych 
		 */
		
		//zaklady obowiazkowe
		if(etap == 1) {
			stol = new Table(gracze);
			stol.giveZetony(zetonyKasyna);
			for(int i = 0; i < ileKlientow+ileBotow; i++) {
				if(gracze[i] != null)
					moze_grac.set(i);
			}
			for(int i=0; i < gracze.length; i++) {
				if(gracze[i] != null)
					if(!stol.wezZetony(gracze[i], wysokoscWpisowego, 4)) {
						moze_grac.clear(i);
					}
			}
		sendMessageToAll("MSG Wpisowe zostalo pobrane.");
		etap = 2;
		int liczbaGraczy = (ileKlientow + ileBotow);
		Random generator = new Random(); 
		graczDealer = generator.nextInt(liczbaGraczy);
		// przyznanie dealer buttona losowemu graczowi oraz przyznanie small i big blind
		gracze[graczDealer].DealerButton = true;
		gracze[graczDealer + 1].SmallBlind = true;
		gracze[graczDealer + 2].BigBlind = true;
		
		// rozdanie po 2 karty dla kazdego gracza z potasowanej wczesniej talii
		for(int i = 0; i < liczbaGraczy; i++) {
			//reka[0] = deck.wezZTalii();
			//reka[1] = deck.wezZTalii();
			//gracze[i].odbierzKarte(reka[0]);
			//gracze[i].odbierzKarte(reka[1]);
			// POPRAWIONE
			stol.pobierzKarte(gracze[i]);
			stol.pobierzKarte(gracze[i]);
		}
		
		start_game = Math.abs((int)System.currentTimeMillis())%ileKlientow;
		while(moze_grac.get(start_game) == false) {
			start_game = (start_game+1)%ileKlientow;
		}
		gracz_perm = start_game;
		sendMessageToAll("CMD START");
		sendMessageToAll("MSG Karty zostaly rozdane.");
		sendMessageToAll("MSG Gre zaczyna Gracz "+gracz_perm+".");
		klient[gracz_perm].sendMessage("MSG Zaczynasz gre.");
		return 1;
		}
		// pierwsza i druga runda licytacji
		else if(etap >= 2 && etap <= 4) {
			//gracze[graczDealer + 3]
			// teraz gracz na lewo od gracza z big blind czyli gracz gracze[graczDealer + 3] rozpoczyna pierwsza runde licytacji
			flop();
			// teraz gracz z small blind zaczyna druga runde licytacji
			turn();
			// teraz gracz z small blind rozpoczyna trzecia runde licytacji
			river();
			// teraz czwartą rundę rozpoczyna gracz z small blind
		}
		// wylonienie zwyciezcy
		else if(etap == 5) {
			// na podstawie rankingu ukladow wylaniany jest zwyciezca
			winners = stol.compareAllPlayers(gracze);
			winners.and(moze_grac);
			if(winners.cardinality() > 1)
			{
				sendMessageToAll("MSG ---------------//REMIS//---------------");
				sendMessageToAll("MSG Koniec gry");
				sendMessageToAll("MSG ");
				zetonyKasyna = stol.getPula();
				//return 1;
			}
			else
			{
				gracz_perm = winners.nextSetBit(0);
				sendMessageToAll("MSG ---------------//WYGRANA//---------------");
				sendMessageToAll("MSG Gracz "+gracz_perm+" wygral i zgarnia wygrana w wysokosci "+stol.getPula()+" !");
				stol.oddajPuleWygr(gracze[gracz_perm]);
				if(gracz_perm < ileKlientow)
					klient[gracz_perm].sendMessage("MSG Gratulacje ! Wygrales !");
			}
			gracz_perm = 0;
			etap = 1;
			
			for(int i = 0; i < ileKlientow+ileBotow; i++)
				if(gracze[i] != null)
					gracze[i].zniszczKarty();
			
			nextStepGame(false);
			return 1;
		}
		return 0;
	}
	

	// dokladana jest 5 karta
	private void river() {
		stol.kartaNastol(deck.wezZTalii());
		
	}

	// do trzech widocznych kart dokladana jest 1 karta
	private void turn() {
		stol.kartaNastol(deck.wezZTalii());
	}
	
	// z talii na stol wykladane sa 3 karty wspolne widoczne dla wszystkich graczy
	private void flop() {
		for(int i = 0; i < 3; i++) {
			stol.kartaNastol(deck.wezZTalii());
		}
		//sprawdzRankingGraczy();
	}

	

	public void sendMessageToAll(String mssg) {
		for(int i = 0; i < ileKlientow; i++)
		{
			if(klient[i] != null)
				klient[i].sendMessage(mssg);
		}
	}
	
	public static void main(String[] args) {
		
		if(args.length < 4)
			return;
		
		try {
			ileKlientow = (short)Integer.parseInt(args[0]);
			ileBotow = (short)Integer.parseInt(args[1]);
			
			if(ileKlientow + ileBotow < 2 || ileKlientow + ileBotow > 10) 
				return;
			
			iloscZetonow= Integer.parseInt(args[2]);
			wysokoscWpisowego = Integer.parseInt(args[3]);
			
			if(wysokoscWpisowego >= iloscZetonow)
				return;
			
			new SerwerPoker(3535);
		}
		catch(NumberFormatException e) {
			System.out.println("Poprawne parametry [ilosc prawdziwych graczy] [ile botow] [ile zetonow kazdy dostaje na starcie] [wysokosc wpisowego]");
		}
	}
}