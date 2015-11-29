package tp.poker.texas.holdem;

public class Card {

	private int kolor, numfig;
	
	private static String[] kolory = { "trefl", "karo", "kier", "pik" };
	private static String[] numfigy = {"dwojka", "trojka", "czworka",
			"piatka", "szostka", "siodemka", "osemka", "dziewiatka",
			"dziesiatka", "walet", "dama", "krol", "as" };

	Card(int kolor, int numfig)
	{
		this.kolor = kolor%4;
		this.numfig = numfig%12;
	}
	
	public String nazwaKarty()
	{
		return numfigy[numfig] + " " + kolory[kolor];
	}
	
	public int getKolor()
	{
		return kolor;
	}
	
	public int getNumFig()
	{
		return numfig;
	}
}