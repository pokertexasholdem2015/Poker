package tp.poker.texas.holdem;



public abstract class Player {
	public boolean DealerButton; 
	public boolean SmallBlind;
	public boolean BigBlind;
	public Card reka[] = new Card[7];
	int uklad = 0;
	private int zetony = 0;
	
	public abstract int BotBetStrategy(Table stol);
	
	
	
	

	
	public void zniszczKarty()
	{
		for(int i=0; i < 7; i++)
			reka[i] = null;
	}
	
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
	
	public String pokazKarte(int miejsce){
		return reka[miejsce%2].getKolor()+"/"+reka[miejsce%2].getNumFig();
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
	/////////////////////////////
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
	
	private boolean czyKolorSame() {
		if (reka[0].getKolor() == reka[1].getKolor())
			if (reka[1].getKolor() == reka[2].getKolor())
				if (reka[2].getKolor() == reka[3].getKolor())
					if (reka[3].getKolor() == reka[4].getKolor())
						return true;
		return false;
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
			
			public int rangaUkladu() {
				int ts = 0;
				int kolejne = 0;
				for (int i = 0; i < 4; i++) {
					if (reka[i] != null && reka[i + 1] != null) {
						if (reka[i].getNumFig() == reka[i + 1].getNumFig()) {
							if (uklad == 8 && ts == 0) {
								uklad = 7;
								ts++;
								continue;
							}
							if (uklad == 6 && ts == 1 || uklad == 7 && ts == 1) {
								uklad = 3;
								break;
							}
							if (ts == 0 && uklad != 4) {
								uklad = 8;
								ts++;
								continue;
							}
							if (ts == 0 && uklad == 4) {
								ts++;
								continue;
							}
							if (ts == 1 && uklad != 4) {
								uklad = 6;
								ts++;
								continue;
							}
							if (ts == 1 && uklad == 4) {
								ts++;
								continue;
							}
							if (ts == 2) {
								uklad = 2;
								break;
							}
						} else if (reka[i].getNumFig() != reka[i + 1].getNumFig()) {
							if (reka[i].getNumFig() + 1 == reka[i + 1].getNumFig()) {
								kolejne++;
								if (kolejne == 4)
									if (czyKolorSame())
										uklad = 1;
									else
										uklad = 5;
								continue;
							}
							if (uklad == 8 && ts == 1) {
								ts--;
								continue;
							}
							if (uklad == 6 && ts == 2) {
								ts--;
								continue;
							}
							if (ts == 0 || uklad == 6 && ts == 1) {
								if (czyKolorSame() && uklad != 4) {
									uklad = 4;
									ts = 0;
									continue;
								}
							}
						}
					}
				}
				if (uklad == 0)
					uklad = 9;
				return uklad;
			}

}
