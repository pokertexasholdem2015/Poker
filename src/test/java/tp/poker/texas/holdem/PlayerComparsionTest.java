package tp.poker.texas.holdem;



import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerComparsionTest {
	
	 static Table stol = null; // 1,2,10,6,5
	 static Table stol2 = null;// 4,3,7,8,9
	 static Player gracze[];//= new Player[4];
	 static Player gracze2[];//= new Player[4];
	private static Card karty1[]; // kolor
	private static Card karty2[]; // full 2x as 3x krol
	private static Card karty3[]; // najwyzsza 
	private static Card karty4[]; // para 2
	private static Card karty5[]; // trojka trojek
	private static Card karty6[]; // 2pary
	private static Card karty7[]; // para 9
	private static Card karty8[]; // para Asow
	private static Card karty9[]; // para jopkow
	private static Card karty10[]; // full 10 na 5
	
	@BeforeClass
	
	public static void setUpBeforeClass()  {
		
	// Tablice Graczy //////
		
		Player gracze[] = new Player[4];
		Player gracze2[] = new Player[4];
		
		
	// Gracze w Tablicach
	try {
		for(int i=0; i<=4; i++){
		gracze[i] = new Human();
		}
		
		for(int i=0; i<=4; i++){
		gracze2[i] = new Human();
		}
	}
	catch( Exception e) {
		System.out.println("cos poszlo nie tak z tworzeniem Humanow");
	}
	//	Zestaw Kart na kolor //
		for(int i=0; i<5; i++){
			
			karty1[i] = new Card(3,i); // wypelnienie Pikami
		}
	// Zestaw Kart na Fulla asy na krolach
		
		karty2[0]= new Card(0,12); // wpisanie 2 asow 
		karty2[1]= new Card(1,12);
		karty2[2]= new Card(0,11);// wpisanie 3 kroli 
		karty2[3]= new Card(1,11);
		karty2[4]= new Card(2,11);
		
	// Zestaw Kart na Fulla 10 na 5
		
		karty10[0]= new Card(2,8); // wpisanie 2x 10 
		karty10[1]= new Card(3,8);
		karty10[2]= new Card(0,3);// wpisanie 3x5 
		karty10[3]= new Card(1,3);
		karty10[4]= new Card(2,3);	
	
	// Zestaw Kart na trojke trojek
	
		for(int i=0; i<3; i++){
			karty5[i]= new Card (i,1);
		}
		for(int i=3; i<5; i++){
			karty5[i]= new Card (i-2,i);
		}
		
	// Zestaw Kart na 2 pary
		karty6[0]= new Card(0,5);
		for(int i=1; i<3;i++){
			karty6[i]= new Card(i,6); //para osemek
		}
		for(int i=3; i<5;i++){
			karty6[i]= new Card(i-3,10); // para dam
		}
		
		
	// Zestaw Kart Najwyzsza Karta
		
			for(int i=0; i<5; i++){
			if (karty3[0]==null){
			karty3[0] = new Card(1,12); // przypisanie Asa
				}
			else karty3[i]=new Card(i-1,12-i); // wypelnienie innymi kartami o nizszej randze i innym kolorze
				}	
			
			// Specjalnie dla porownania kwestii spornych wewnatrz jednej rangi ukladu //
	// Zestaw kart na jedna pare (nedzna) (dwojki)
		karty4[0]= new Card(0,0);
		karty4[1]= new Card(1,0);
		for(int i=2; i<5; i++){
			karty4[i]= new Card(i-1,i); // wypelnienie reszta kart 
		}
		
	// Zestaw kart na jedna pare (srednia) ( dziewiatki) 
		karty4[0]= new Card(0,7);
		karty4[1]= new Card(1,7);
		for(int i=2; i<5; i++){
			karty4[i]= new Card(i-1,i); // wypelnienie reszta kart 
		}
	
	// Zestaw kart na jedna pare (mocniejsza) (Jopki)
		
	// Zestaw kart na najmocniejsza pare (Asy)
		karty4[0]= new Card(2,12); 
		karty4[1]= new Card(3,12);
		for(int i=2; i<5; i++){
			karty4[i]= new Card(i-1,i); // wypelnienie reszta kart 
		}
		
	
	// Przypisywanie kart graczom ///
		
			for(int y=0; y<5; y++){
				gracze[0].odbierzKarte(karty1[y]);
				gracze[0].CardSort();
			}
			
			for(int y=0; y<5; y++){
				gracze[1].odbierzKarte(karty2[y]);
				gracze[1].CardSort();
			}
			
			for(int y=0; y<5; y++){
				gracze[2].odbierzKarte(karty10[y]);
				gracze[2].CardSort();
			}
			
			for(int y=0; y<5; y++){
				gracze[3].odbierzKarte(karty5[y]);
				gracze[3].CardSort();
			}
		
			for(int y=0; y<5; y++){
				gracze[4].odbierzKarte(karty6[y]);
				gracze[4].CardSort();
			}
			
			for(int y=0; y<5; y++){
				gracze2[0].odbierzKarte(karty4[y]);
				gracze2[0].CardSort();
			}
			
			for(int y=0; y<5; y++){
				gracze2[1].odbierzKarte(karty3[y]);
				gracze2[1].CardSort();
			}
			
			for(int y=0; y<5; y++){
				gracze2[2].odbierzKarte(karty7[y]);
				gracze2[2].CardSort();
			}
			
			for(int y=0; y<5; y++){
				gracze2[3].odbierzKarte(karty8[y]);
				gracze2[3].CardSort();
			}
		
			for(int y=0; y<5; y++){
				gracze2[4].odbierzKarte(karty9[y]);
				gracze2[4].CardSort();
			}
		
		/// Tworzenie 2 Stolow do testow ////
			
		//Player gracze[] = new Player[4];
		//gracze = new Player[2];
		
		stol = new Table(gracze);
		stol2 = new Table(gracze2);
		
		/// Porownywanie ukladow ///
		
		//stol.compareAllPlayers(gracze);
		//stol2.compareAllPlayers(gracze2);
		
		
	}
	
	/// Test Wylania zwyciezce wsrod wysokich ukladow
	@Test
	public void wysokieuklady1(){
		
	//	stol.compareAllPlayers(gracze);
		
		
	}
	
	/// Test wylania zwyciezce wsrod wielu tych samych ukladow roznej rangi
	@Test
	public void tejSamejRangi(){
		
		//stol2.compareAllPlayers(gracze2);
		
		System.out.println(gracze2[0].reka[0]);
		System.out.println(stol2.compareAllPlayers(gracze2));
		//stol2.compareAllPlayers(gracze2).and(set);
		
		
	}


	
	
}
