package tp.poker.texas.holdem;

import java.util.ArrayList;
import java.util.BitSet;


public class Table {
	Deck talia;
	private int zetony = 0;
	int stawka = 0;
	int small;
	int big;
	public Card reka[] = new Card[5];
	boolean check = true;
	boolean ALLin = false;

	
	Table(Player gracze[], int s, int b)
	{
		this.small=s;
		this.big=b;
		
		talia = new Deck();
		
		
		for(int i=0; i < 1; i++)
		{
			for(int y = 0; y < gracze.length; y++)
			{
				if(gracze[y] != null)
					pobierzKarte(gracze[y]);
			}
		}
	}
	

	
	
 public void UnsetButtons(Player gracz){
	 if(gracz.DealerButton==true){
		 gracz.DealerButton=false;
	 }
	 if(gracz.SmallBlind==true){
		 gracz.SmallBlind=false;
	 }
 }
 
	
	public void PassingDealerButton(Player gracze[]){
		
			for(int y = 0; y < gracze.length; y++)
			{
				if(gracze[y].DealerButton==true){
					UnsetButtons(gracze[y]);
					UnsetButtons(gracze[y+1]);
					SetBigBlind(gracze[y+1]);
					SetSmallBlind(gracze[y+2]);
					break;
				}
				else if(gracze[gracze.length-1].DealerButton==true){
					UnsetButtons(gracze[gracze.length-1]);
					UnsetButtons(gracze[gracze.length]);
					SetBigBlind(gracze[gracze.length]);
					SetSmallBlind(gracze[0]);
					break;
					
				}
				else if(gracze[gracze.length].DealerButton==true){
					UnsetButtons(gracze[gracze.length]);
					UnsetButtons(gracze[0]);
					SetBigBlind(gracze[0]);
					SetSmallBlind(gracze[1]);
					break;
					
				}
					
				
					
			}
		
		
	}
	
	public void SetSmallBlind(Player gracz){
		gracz.SmallBlind=true;
	}
	
	
	public void SetBigBlind(Player gracz){
		
		gracz.DealerButton=true;
	}
	
	public void PoCalejRundzie()
	{
		for(int i=0; i < 5; i++)
			reka[i] = null;
	}
	
	public String pokazUklad(int uklad) {
		final String[] rodzUkla = { "", "Poker", "Kareta", "Ful", "Kolor",
				"Strit", "Trojka", "Dwie pary", "Para", "Najwyzsza karta" };
		return rodzUkla[uklad];
	}
	
	public ArrayList<String> pokazKarty(Player gracz) {
		ArrayList<String> kartygracza = new ArrayList<String>();
		String s;
		for (int i = 0; i < 4; i++) {
			if (gracz.reka[i] != null){
				s=(" "+i + " | " + reka[i].nazwaKarty());
				kartygracza.add(s);
				
			}
				
		}
		return kartygracza;
	}
	
	public void OstatniEtapKart(Player gracze[]){
		for(int i=0; i < 10; i++)
		{
			for(int y = 0; y < gracze.length; y++)
			{
				if(gracze[y] != null)
					
					for (int j = 0; j < 4; j++) {
						if (reka[j] != null) {
							gracze[y].odbierzKarte(reka[i]);
							break;
								
						}
						else{
							gracze[y].CardSort();
						}
					}
			}
		}
	}
	
	public int kartaNastol(Card karta){
		
		for (int i = 0; i < 4; i++) {
			if (reka[i] == null) {
				reka[i] = karta;
				break;
					
			}
		}
		if(karta != null)
		{
			talia.wrzucDoTalii(karta);
			return 0;
		}
		return 1;
	}
	
	public int getPula()
	{
		return zetony;
	}
	
	public void giveZetony(int zeto)
	{
		zetony = zeto;
	}
	
	public void oddajPuleWygr(Player gracz)
	{
		gracz.dajZetony(zetony);
		zetony = 0;
	}
	
	// jakim trybem nalezy wziac zetony od gracza, 0 - zwykly, 1 - wyrownanie, 2 - raise, 3 - allin, 4 - wpisowe
	public boolean wezZetony(Player gracz, int wartosc, int tryb)
	{
		if(ALLin == false)
		{
			if((tryb == 0 && check) || tryb == 1)
			{
				if(gracz.Zetony() < ((tryb == 0)?wartosc:stawka) || (wartosc <= 0 && tryb == 0)) return false;
				zetony += gracz.wezZetony(((tryb == 0)? wartosc: stawka));
				if(tryb == 0) stawka = wartosc;
				check = false;
				return true;
			}
			else if(tryb==2)
			{
				if(gracz.Zetony() < wartosc) return false;
				if(wartosc <= stawka) return false;
				stawka = wartosc;
				zetony += gracz.wezZetony(wartosc);
				return true;
			}
		}
		if(tryb == 3)
		{
			if(gracz.Zetony() == 0) return false;
			zetony += gracz.wezZetony(gracz.Zetony());
			ALLin = true;
			check = false;
			return true;
		}
		else if(tryb == 4)
		{
			if(gracz.Zetony() < wartosc) return false;
			zetony += gracz.wezZetony(wartosc);
			return true;
		}
		return false;
	}
	
	public int pobierzKarte(Player gracz) {
		if(gracz == null) return 0;
		Card karta = gracz.odbierzKarte(talia.wezZTalii());
		if(karta != null)
		{
			talia.wrzucDoTalii(karta);
			return 0;
		}
		return 1;
	}
	
	
	
	public BitSet compareAllPlayers(Player gracz[])
	{
		BitSet wygr = new BitSet(10);
		wygr.set(0, 10);
		int wygrywajacy = 0;
		while(gracz[wygrywajacy] == null)
		{
			wygrywajacy++;
		}
		for(int i = 1; i < gracz.length; i++)
		{
			if(gracz[i] == null) continue;
			if(gracz[wygrywajacy].porownajUklady(gracz[i]) == 1)
				wygr.clear(i);
			else if(gracz[wygrywajacy].porownajUklady(gracz[i]) == -1)
			{
				wygr.clear(wygrywajacy);
				wygrywajacy = i;
			}
		}
		if(wygr.length() > gracz.length)
		{
			wygr.clear(gracz.length, wygr.length());
		}
		return wygr;
	}
	
}
