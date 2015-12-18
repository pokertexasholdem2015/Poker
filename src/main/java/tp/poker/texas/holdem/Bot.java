package tp.poker.texas.holdem;

import java.util.Random;

public class Bot extends Player {
	private boolean Wakcji;
	
	public int BotBetStrategy(Table stol){
		Wakcji=true;
		int CzyAkcja=0;
		Random los = new Random();
		int ilePodbic=0;
		
		while(Wakcji=true){
			
			////// Zbieraj Karty //////
			/*
			for(int i=0; i<7;i++ ){
				if (stol.reka[6]!=null){ // od zera liczac 7 kart = 2 w rece i 5 na stole
					Wakcji=false;
					CardSort();
				}
				else{
					odbierzKarte(stol.reka[i]);
					// dodaj karte do reki player bota
				}
			}
			*/
			/////////Licytuj przed ostatnia karta///////////
			
			CzyAkcja= los.nextInt(3);
			
			/// Jezeli ktos wchodzi All in/////
			if(stol.ALLin){
				CzyAkcja= los.nextInt(9);
				if(CzyAkcja >= 6) // 40% szansy
				{
					stol.wezZetony(this, 0, 3); // wejdz allin
					return 4;
				}
				else
				{
					if(stol.check) return 3;
					 stol.wezZetony(this, 0, 1); //Wyrownanie 
					 return 1;
				}
				
			}
			/// Szansa ze sam wchodze Allin ///////
			else if(CzyAkcja ==1){
				CzyAkcja= 1+los.nextInt(10);
				if(CzyAkcja >= 3) 
				{
					if(stol.check) return 3;
					 stol.wezZetony(this, 0, 1); //Wyrownanie 
					 return 1;
				}
				else{ // 30% szans na all in
					stol.wezZetony(this, 0, 3); // wejdz allin
					return 4;
				}
				
				
			}
			/// Sam Podbijam slabo ///////
			else if(CzyAkcja==2){
				
				CzyAkcja= 1+los.nextInt(10);
				if(CzyAkcja >= 3) // 70% szansy
				{
					ilePodbic = 1+(10+los.nextInt(20));
					int ile=1+(int)ilePodbic*Zetony()/100;
					if(!stol.wezZetony(this, ile, 2)) //Podbicie
						stol.wezZetony(this, 0, 1);
					return 2;
				}
				
				else
				{
					if(stol.check) return 3;
					 stol.wezZetony(this, 0, 1); //Wyrownanie 
					 return 1;
				}
				
			}
			/// szansa tylko na Wyrownanie /////
			else if(CzyAkcja==3){
				if(stol.check) return 3;
				 stol.wezZetony(this, 0, 1); //Wyrownanie 
				 return 1;
				
			}
			/// Sam Podbijam agresywnie /////
			else if(CzyAkcja==0){
				CzyAkcja= 1+los.nextInt(10);
				if(CzyAkcja >= 3) // 70% szansy
				{
					ilePodbic = 1+(40+los.nextInt(60));
					int ile=1+(int)ilePodbic*Zetony()/100;
					if(!stol.wezZetony(this, ile, 2)) //Podbicie
						stol.wezZetony(this, 0, 1);
					return 2;
				}
				
				else
				{
					if(stol.check) return 3;
					 stol.wezZetony(this, 0, 1); //Wyrownanie 
					 return 1;
				}
			}
			
			
		}	
		/////// Po Zebraniu Kart ////
		
if (Wakcji=false){	
		if(stol.ALLin)
		{
			CzyAkcja = 100/uklad;
			if(CzyAkcja >= 20)
			{
				stol.wezZetony(this, 0, 3);
				return 4;
			}
			else
			{
				CzyAkcja = los.nextInt(2);
				if(CzyAkcja == 1)
					stol.wezZetony(this, 0, 3);
				else
					return 0;
			}
		}
		// Poker i kareta 
		else if(uklad<=2){
			 CzyAkcja = 1+los.nextInt(2);
			 
			if(CzyAkcja==2){
				stol.wezZetony(this, 0, 3); //Allin
				return 4;
			}
			else if(CzyAkcja==1){
				//AktualnyStan = Zetony();
				int ile=1+(int)0.7*Zetony();
				if(!stol.wezZetony(this, ile, 2)) //Podbicie
					stol.wezZetony(this, 0, 1);
				return 2;
			}
		}
		//kolor i full
		else if(uklad<=5 && uklad>=3){
			CzyAkcja = 1+los.nextInt(2);
			 
			if(CzyAkcja==1){
				if(stol.check) return 3;
				stol.wezZetony(this, 0, 1); //Wyrownanie
				return 1;
			}
			else if(CzyAkcja==2){
				//AktualnyStan = Zetony();
				ilePodbic = 1+(40+los.nextInt(60));
				int ile=1+(int)ilePodbic*Zetony()/100;
				if(!stol.wezZetony(this, ile, 2)) // Podbicie
					stol.wezZetony(this, 0, 1);
				return 2;
			}
		}
		//para
		else if(uklad==8){
			 CzyAkcja = 1+los.nextInt(7);
			 if(CzyAkcja<=4){
				 if(stol.check) return 3;
				 stol.wezZetony(this, 0, 1); //Wyrownanie 
				 return 1;
			 }
			 else if(CzyAkcja>4){
				//AktualnyStan = Zetony();
				ilePodbic = 1+(10+los.nextInt(20));
				int ile=1+(int)ilePodbic*Zetony()/100;
				if(!stol.wezZetony(this, ile, 2)) // Podbicie
					stol.wezZetony(this, 0, 1);
				return 2;
			}
		}
		//2pary
		else if(uklad==7){
			CzyAkcja = 1+los.nextInt(7);
			 if(CzyAkcja<4){
				 if(stol.check) return 3;
				 stol.wezZetony(this, 0, 1); //Wyrownanie 
				 return 1;
			 }
			 else if(CzyAkcja>=4){
				//AktualnyStan = Zetony();
				ilePodbic = 1+(20+los.nextInt(30));
				int ile=1+(int)ilePodbic*Zetony()/100;
				if(!stol.wezZetony(this, ile, 2)) // Podbicie
					stol.wezZetony(this, 0, 1);
				return 2;
			}
		}
		//trojka
		else if(uklad==6){
			CzyAkcja = 1+los.nextInt(5);
			 if(CzyAkcja<3){
				 if(stol.check) return 3;
				 stol.wezZetony(this, 0, 1); //Wyrownanie 
				 return 1;
			 }
			 else if(CzyAkcja>=3){
				//AktualnyStan = Zetony();
				ilePodbic = 1+(20+los.nextInt(50));
				int ile=1+(int)ilePodbic*Zetony()/100;
				if(!stol.wezZetony(this, ile, 2)) // Podbicie
					stol.wezZetony(this, 0, 1);
				return 2;
			}
		}
		//najwyzsza
		else if(uklad==9){
			if(stol.check) return 3;
			 stol.wezZetony(this, 0, 1); //Wyrownanie 
			 return 1;
			
		}
}


		return 0;
		
	}

}
