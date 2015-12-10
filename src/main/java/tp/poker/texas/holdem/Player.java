package tp.poker.texas.holdem;

public abstract class Player {
	public Card reka[] = new Card[5];
	int uklad = 0;
	private int zetony = 0;
	
	//public abstract int BotBetStrategy(Table stol);
	
	public Card odbierzKarte(Card karta)
	{
		for (int i = 0; i < 4; i++) {
			if (reka[i] == null) {
				reka[i] = karta;
				break;
					
			}
		}
		return karta;
	}
	
	public void pokazKarty() {
		for (int i = 0; i < 4; i++) {
			if (reka[i] != null)
				System.out.println(i+1 + " | " + reka[i].nazwaKarty());
		}
	}

	//metody uzywane tylko przez Table
	public int wezZetony(int wartosc) {
		if(wartosc > zetony || wartosc < 0) return 0;
		zetony = zetony-wartosc;
		return wartosc;
	}
	
	public boolean dajZetony(int wartosc) {
		if(wartosc < 0) return false;
		zetony += wartosc;
		return true;
	}
	
	public int Zetony() {
		return zetony;
	}

}
