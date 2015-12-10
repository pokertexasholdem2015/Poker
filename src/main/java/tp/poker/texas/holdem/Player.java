package tp.poker.texas.holdem;

import Card;
import Player;

public abstract class Player {
	public Card reka[] = new Card[7];
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
	
	public void CardSort() {
		Card pomoc = null;
		for (int i = 0; i < 7; i++) {
			if (i + 1 < 7)
				if (reka[i] != null && reka[i + 1] != null) {
					if (reka[i].getNumFig() > reka[i + 1].getNumFig()) {
						pomoc = reka[i];
						reka[i] = reka[i + 1];
						reka[i + 1] = pomoc;
						CardSort();
						break;
					}
				}
		}
	}
	
	// --------------------------------------------
			public int porownajUklady(Player gracz) {
				
				int najwKarta1[] = new int[2];
				int najwKarta2[] = new int[2];
				
				if (uklad < gracz.uklad)
					return 1;
				else if (uklad > gracz.uklad)
					return -1;
				else if (uklad == gracz.uklad) {
					if (uklad == 1) {
						if (reka[4].getNumFig() > gracz.reka[4].getNumFig())
							return 1;
						else if (reka[4].getNumFig() < gracz.reka[4].getNumFig())
							return -1;
					} else if (uklad == 2 || uklad == 3 || uklad == 6) {
						if (reka[2].getNumFig() > gracz.reka[2].getNumFig())
							return 1;
						else if (reka[2].getNumFig() < gracz.reka[2].getNumFig())
							return -1;
					}
					else if (uklad == 4) {
						for (int i = 4; i >= 0; i--) {
							if (reka[i].getNumFig() > gracz.reka[i].getNumFig())
								return 1;
							else if (reka[i].getNumFig() < gracz.reka[i].getNumFig())
								return -1;
						}
					} else if (uklad == 5) {
						if (reka[4].getNumFig() > gracz.reka[4].getNumFig())
							return 1;
						else if (reka[4].getNumFig() < gracz.reka[4].getNumFig())
							return -1;
					} else if (uklad == 7) {
						if (reka[1].getNumFig() > gracz.reka[1].getNumFig())
							return 1;
						else if (reka[1].getNumFig() < gracz.reka[1].getNumFig())
							return -1;
						else if (reka[3].getNumFig() > gracz.reka[3].getNumFig())
							return 1;
						else if (reka[3].getNumFig() < gracz.reka[3].getNumFig())
							return -1;
					} else if (uklad == 8) {
						
						for(int i=0; i < 4; i++)
						{
							if(reka[i].getNumFig() == reka[i+1].getNumFig())
							{
								najwKarta1[0]=reka[i].getNumFig();
								najwKarta1[1] = i;
							}
							if(gracz.reka[i].getNumFig() == gracz.reka[i+1].getNumFig())
							{
								najwKarta2[0]=gracz.reka[i].getNumFig();
								najwKarta2[1] = i;
							}
						}
						
						if(najwKarta1[0] > najwKarta2[0])
							return 1;
						else if(najwKarta1[0] < najwKarta2[0])
							return -1;
						else if(najwKarta1[0] == najwKarta2[0])
						{
							if(najwKarta1[1] == najwKarta2[1])
								return 0;
							else if(najwKarta1[1] < najwKarta2[1])
								return -1;
							else if(najwKarta1[1] > najwKarta2[1])
								return 1;
						}
						
						

					} else if (uklad == 9) {
						if (reka[4].getNumFig() > gracz.reka[4].getNumFig())
							return 1;
						else if (reka[4].getNumFig() < gracz.reka[4].getNumFig())
							return -1;
					}
				}
				return 0;
			}

			// --------------------------------------------

}
