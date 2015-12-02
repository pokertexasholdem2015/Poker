package tp.poker.texas.holdem;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> karty;
	
	Deck() {
		karty = new ArrayList<Card>();
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				karty.add(new Card(i, j));
			}
		}
		
		Card pomoc = null;
		int idx1, idx2;
		Random generator = new Random();
		
		for(int i = 0; i < 100; i++) {
			idx1 = generator.nextInt(karty.size() - 1);
			idx2 = generator.nextInt(karty.size() - 1);
			
			pomoc = karty.get(idx1);
			karty.set(idx1, karty.get(idx2));
			karty.set(idx2, pomoc);
		}
	}
	
	public Card wezZTalii() {
		return karty.remove(0);
	}
	
	public void wrzucDoTalii(Card karta) {
		karty.add(karta);
	}
	
	public void pokazKarty() {
		for(int i = 0; i < karty.size(); i++) {
			System.out.println(i + " " + karty.get(i).nazwaKarty());
		}
	}
}