package tp.poker.texas.holdem;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.BitSet;

public class SerwerPoker {
	ServerSocket socket_serwer = null;
	KlientPoker klient[] = new KlientPoker[10];
	Deck deck = new Deck();
	Card reka[] = new Card[7];
	
	Player gracze[] = new Player[10];
	BitSet moze_grac = new BitSet(10);
	Table stol;
	//Table stol = new Table(gracze[], small, big);
	
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
	
// metoda niedokonczona, dlatego return 0 zeby nic sie nie czepial ze bledy
	public int nextStepGame(boolean przel){
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
		
		
	
		flop();
		turn();
		river();
	
		
		return 1;
	}
	


	private void river() {
		// TODO Auto-generated method stub
		
	}

	private void turn() {
				
	}

	private void flop() {
		deck.wezZTalii();
//		stol.kartaNastol(karta);
//		stol.kartaNastol(karta);
//		stol.kartaNastol(karta);
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