package tp.poker.texas.holdem;

public abstract class Player {
	public Card reka[] = new Card[5];
	int uklad = 0;
	private int zetony = 0;
	public abstract int BotBetStrategy(Table stol);
	
	public Card odbierzKarte(Card karta)
	{
		for (int i = 0; i < 5; i++) {
			if (reka[i] == null) {
				reka[i] = karta;
				if(reka[4] != null)
				{
					//CardSort();
					//rangaUkladu();
				}
				return null;
			}
		}
		return karta;
	}

}
