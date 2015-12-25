package tp.poker.texas.holdem;

import org.junit.Test;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {
	private Human gracz;
	private Human gracz2;
	private Deck talia;
	private Card reka[];
	private Card kartaDwojkaTrefl;
	private int zetony;
	
	protected final void setUp() throws Exception {
		gracz = new Human();
		gracz2 = new Human();
		talia = Deck.pobierzTalie();
		reka = new Card[5];
		kartaDwojkaTrefl = new Card(0, 0);
		zetony = 50;
	}
	
	protected final void tearDown() {
		gracz = null;
		gracz2 = null;
		talia = null;
		reka = null;
		kartaDwojkaTrefl = null;
		zetony = 0;
	}
	
	
	@Test
	public final void testMtdOdbierzKarte() {
		//gracz.pokazKarty();
		//gracz.pokazKarte(0);
		assertEquals(gracz.odbierzKarte(kartaDwojkaTrefl), gracz.odbierzKarte(new Card(0, 0)));
		assertSame(gracz.odbierzKarte(kartaDwojkaTrefl), gracz.odbierzKarte(new Card(0, 0)));
	}
	
	@Test
	public final void testMtdZniszczKarty() {
		for (int i = 0; i < 5; i++) {
			reka[i] = gracz.odbierzKarte(talia.wezZTalii());	
		}
		System.out.println("Utworzone karty gracza to: ");
		gracz.pokazKarty();
		System.out.println("Teraz karty zostaną zniszczone!");
		gracz.zniszczKarty();
		System.out.println("Zatem karty gracza to: ");
		gracz.pokazKarty();
	}
	
	@Test
	public final void testMtdPokazKarty() {
		for (int i = 0; i < 5; i++) {
			reka[i] = gracz.odbierzKarte(talia.wezZTalii());	
		}
		gracz.pokazKarty();
	}
	
	@Test
	public final void testMtdPokazKarte() {
		gracz.odbierzKarte(new Card(0, 0));
		gracz.odbierzKarte(new Card(0, 1));
		assertEquals("0/0", gracz.pokazKarte(0));
		assertEquals("0/1", gracz.pokazKarte(1));
	}
	
	@Test
	public final void testMtdCardSort() {
/*		reka[0] = gracz.odbierzKarte(new Card(0, 3));
		reka[1] = gracz.odbierzKarte(new Card(0, 1));
		reka[2] = gracz.odbierzKarte(new Card(0, 4));
		reka[3] = gracz.odbierzKarte(new Card(0, 0));
		reka[4] = gracz.odbierzKarte(new Card(0, 2));*/
		for (int i = 0; i < 5; i++) {
			reka[i] = gracz.odbierzKarte(talia.wezZTalii());	
		}
		
		gracz.CardSort();
		gracz.pokazKarty();
	}
	
	@Test
	public final void testMtdCzyKolorSame() {
		reka[0] = gracz.odbierzKarte(new Card(0, 0));
		reka[1] = gracz.odbierzKarte(new Card(0, 1));
		reka[2] = gracz.odbierzKarte(new Card(0, 2));
		reka[3] = gracz.odbierzKarte(new Card(0, 3));
		reka[4] = gracz.odbierzKarte(new Card(0, 4));
		assertTrue(gracz.czyKolorSame());
		gracz.zniszczKarty();
		for (int i = 0; i < 3; i++) {
			reka[i] = gracz.odbierzKarte(talia.wezZTalii());	
		}
		reka[3] = gracz.odbierzKarte(new Card(1, 0));
		reka[4] = gracz.odbierzKarte(new Card(3, 0));
		assertFalse(gracz.czyKolorSame());
	}
	
	@Test
	public final void testMtdPokazUklad() {
		//Najwyższa karta: dowolny układ nienależąca do żadnej z powyższych kategorii
		gracz.odbierzKarte(new Card(0, 12));
		gracz.odbierzKarte(new Card(1, 11));
		gracz.odbierzKarte(new Card(2, 10));
		gracz.odbierzKarte(new Card(3, 9));
		gracz.odbierzKarte(new Card(1, 7));
		String najwyzszaKartaUklad;
		najwyzszaKartaUklad = gracz.pokazUklad();
		System.out.println(najwyzszaKartaUklad);
		
		assertEquals("Najwyzsza karta", najwyzszaKartaUklad);
		gracz.zniszczKarty();
		
		//Poker: pięć kolejnych kart w jednym kolorze
		gracz.odbierzKarte(new Card(0, 0));
		gracz.odbierzKarte(new Card(0, 1));
		gracz.odbierzKarte(new Card(0, 2));
		gracz.odbierzKarte(new Card(0, 3));
		gracz.odbierzKarte(new Card(0, 4));
		String pokerUklad;
		pokerUklad = gracz.pokazUklad();
		System.out.println(pokerUklad);
		
		assertEquals("Poker", pokerUklad);
		gracz.zniszczKarty();

		//Kareta: cztery karty o jednakowej wartości i jedna karta dodatkowa
		gracz.odbierzKarte(new Card(0, 12));
		gracz.odbierzKarte(new Card(1, 12));
		gracz.odbierzKarte(new Card(2, 12));
		gracz.odbierzKarte(new Card(3, 12));
		gracz.odbierzKarte(new Card(0, 8));
		String karetaUklad;
		karetaUklad = gracz.pokazUklad();
		System.out.println(karetaUklad);
		
		assertEquals("Kareta", karetaUklad);
		gracz.zniszczKarty();
		
		//Ful: trzy karty o jednakowej wartości i para (dwie karty o jednakowej wartości
		gracz.odbierzKarte(new Card(0, 12));
		gracz.odbierzKarte(new Card(1, 12));
		gracz.odbierzKarte(new Card(2, 12));
		gracz.odbierzKarte(new Card(3, 2));
		gracz.odbierzKarte(new Card(0, 2));
		String fulUklad;
		fulUklad = gracz.pokazUklad();
		System.out.println(fulUklad);
		
		assertEquals("Ful", fulUklad);
		gracz.zniszczKarty();
		
		//Kolor: pięć kart w jednym kolorze
		gracz.odbierzKarte(new Card(2, 12));
		gracz.odbierzKarte(new Card(2, 10));
		gracz.odbierzKarte(new Card(2, 1));
		gracz.odbierzKarte(new Card(2, 4));
		gracz.odbierzKarte(new Card(2, 7));
		String kolorUklad;
		kolorUklad = gracz.pokazUklad();
		System.out.println(kolorUklad);
		
		assertEquals("Kolor", kolorUklad);
		gracz.zniszczKarty();
		
		//Strit: pięć kolejnych kart
		gracz.odbierzKarte(new Card(0, 2));
		gracz.odbierzKarte(new Card(1, 3));
		gracz.odbierzKarte(new Card(3, 4));
		gracz.odbierzKarte(new Card(2, 5));
		gracz.odbierzKarte(new Card(0, 6));
		String stritUklad;
		stritUklad = gracz.pokazUklad();
		System.out.println(stritUklad);
		
		assertEquals("Strit", stritUklad);
		gracz.zniszczKarty();
		
		//Trójka: trzy karty o jednakowej wartości i dwie dodatkowe
		gracz.odbierzKarte(new Card(0, 9));
		gracz.odbierzKarte(new Card(1, 9));
		gracz.odbierzKarte(new Card(3, 9));
		gracz.odbierzKarte(new Card(2, 5));
		gracz.odbierzKarte(new Card(0, 6));
		String trojkaUklad;
		trojkaUklad = gracz.pokazUklad();
		System.out.println(trojkaUklad);
		
		assertEquals("Trojka", trojkaUklad);
		gracz.zniszczKarty();
		
		//Dwie pary: dwie karty o jednakowej wartości, dwie kolejne karty o innej jednakowej wartości i jedna karta dodatkowa
		gracz.odbierzKarte(new Card(0, 8));
		gracz.odbierzKarte(new Card(1, 8));
		gracz.odbierzKarte(new Card(3, 4));
		gracz.odbierzKarte(new Card(2, 4));
		gracz.odbierzKarte(new Card(0, 11));
		String dwieParyUklad;
		dwieParyUklad = gracz.pokazUklad();
		System.out.println(dwieParyUklad);
		
		assertEquals("Dwie pary", dwieParyUklad);
		gracz.zniszczKarty();
		
		//Para: dwie karty o jednakowej wartości i trzy karty dodatkowe o innych wartościach
		gracz.odbierzKarte(new Card(0, 8));
		gracz.odbierzKarte(new Card(1, 8));
		gracz.odbierzKarte(new Card(3, 4));
		gracz.odbierzKarte(new Card(2, 6));
		gracz.odbierzKarte(new Card(0, 11));
		String paraUklad;
		paraUklad = gracz.pokazUklad();
		System.out.println(paraUklad);
		
		assertEquals("Para", paraUklad);
		gracz.zniszczKarty();
	}
	
	@Test
	public final void testMtdPorownajUklady() {
		gracz.odbierzKarte(new Card(0, 0));
		gracz.odbierzKarte(new Card(0, 1));
		gracz.odbierzKarte(new Card(0, 2));
		gracz.odbierzKarte(new Card(0, 3));
		gracz.odbierzKarte(new Card(0, 4));
		
		gracz2.odbierzKarte(new Card(0, 9));
		gracz2.odbierzKarte(new Card(1, 9));
		gracz2.odbierzKarte(new Card(3, 9));
		gracz2.odbierzKarte(new Card(2, 5));
		gracz2.odbierzKarte(new Card(0, 6));
		
		int por;
		por = gracz.porownajUklady(gracz2);
		System.out.println(por);
		gracz.zniszczKarty();
		gracz2.zniszczKarty();
		
		gracz.odbierzKarte(new Card(0, 2));
		gracz.odbierzKarte(new Card(1, 3));
		gracz.odbierzKarte(new Card(3, 4));
		gracz.odbierzKarte(new Card(2, 5));
		gracz.odbierzKarte(new Card(0, 6));
		
		gracz2.odbierzKarte(new Card(0, 2));
		gracz2.odbierzKarte(new Card(1, 3));
		gracz2.odbierzKarte(new Card(3, 4));
		gracz2.odbierzKarte(new Card(2, 5));
		gracz2.odbierzKarte(new Card(1, 6));
		
		int por2;
		por2 = gracz.porownajUklady(gracz2);
		System.out.println(por2);
		
		gracz.odbierzKarte(new Card(0, 0));
		gracz.odbierzKarte(new Card(0, 1));
		gracz.odbierzKarte(new Card(0, 2));
		gracz.odbierzKarte(new Card(0, 3));
		gracz.odbierzKarte(new Card(0, 4));
		
		gracz2.odbierzKarte(new Card(0, 8));
		gracz2.odbierzKarte(new Card(1, 8));
		gracz2.odbierzKarte(new Card(3, 4));
		gracz2.odbierzKarte(new Card(2, 4));
		gracz2.odbierzKarte(new Card(0, 11));
		
		int por3;
		por3 = gracz.porownajUklady(gracz2);
		System.out.println(por3);
	}
	
	@Test
	public final void testMtdWezZetony() {
		gracz.dajZetony(zetony);
		int wzieteZetony = gracz.wezZetony(20);
		int zetonyPoOdebraniu20 = gracz.Zetony();
		assertEquals(20, wzieteZetony);
		assertEquals(30, zetonyPoOdebraniu20);
	}
	
	@Test
	public final void testMtdDajZetony() {
		gracz.dajZetony(zetony);
		int zetonyKtoreDostalGracz = gracz.Zetony();
		assertEquals(50, zetonyKtoreDostalGracz);
		
		zetony = -1;
		if (zetony < 0) {
			assertFalse(gracz.dajZetony(zetony));
		}
		zetony = 1;
		if (zetony > 0) {
			assertTrue(gracz.dajZetony(zetony));
		}
	}
	
	@Test
	public final void testMtdZetony() {
		gracz.dajZetony(zetony);
		assertEquals(50, gracz.Zetony());
	}
	
/*
	@Test
	public void testOdbioruKarty(){
		reka[0] = gracz.odbierzKarte(new Card(0, 0));
		reka[1] = gracz.odbierzKarte(new Card(0, 1));
		reka[2] = gracz.odbierzKarte(new Card(0, 2));
		reka[3] = gracz.odbierzKarte(new Card(0, 3));
		reka[4] = gracz.odbierzKarte(new Card(0, 4));
		for(int i = 0; i < 5; i++) {
			reka[i] = gracz.odbierzKarte(talia.wezZTalii());	
		}
		gracz.pokazKarty();
		int uklad = gracz.rangaUkladu();
		System.out.println(uklad + " " + gracz.porownajUklady(gracz));
		//assertEquals("dwojka trefl", gracze[0].odbierzKarte(karta));
	}*/
/*	
	@Test
	public void testMtdPorownajUklady() {
		gracze[0].porownajUklady(gracze[1]);
	}*/
/*
	@Test
	public void testMtdRangaUkladu() {
		Card reka[] = new Card[5];
		reka[0] = new Card(0, 0);
		reka[1] = new Card(1, 0);
		reka[2] = new Card(2, 0);
		reka[3] = new Card(3, 0);
		reka[4] = new Card(4, 0);
		
		gracze[0].rangaUkladu();
	}*/
}