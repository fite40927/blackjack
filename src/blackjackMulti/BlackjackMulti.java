package blackjackMulti;

import java.text.DecimalFormat;
import java.util.Scanner;
import resource.Deck;
import resource.Card;

public class BlackjackMulti {
	public static boolean roundOver, enoughCards, gameOver, blackjack;
	public static Deck deck;
	public PlayerMulti currentPlayer, players[], dealer;
	public static int deckIndex, numPlayers;
	public String hs;
	public Scanner scan = new Scanner(System.in);
	public DecimalFormat cents = new DecimalFormat("#.00");
	
	public BlackjackMulti(int NUMplayers, int numDecks) {
		gameOver = false;
		roundOver = false;
		deckIndex = 0;
		blackjack = false;
		enoughCards = true;
		
		numPlayers = NUMplayers;
		dealer = new PlayerMulti("Dealer");
		players = new PlayerMulti[numPlayers];
		System.out.println();
		for(int i = 0; i < numPlayers; i++) {
			System.out.print("Player " + (i+1) + " name:\t");
			String s = scan.nextLine();
			players[i] = new PlayerMulti(s);
		}
		
		deck = new Deck(numDecks);
	}
	public void play() {
		System.out.print("\nEnter any key to start:\t");
		scan.next();		
		System.out.println();
		
		deck.createDeck(); sleep(900);
		deck.shuffle(); sleep(900);
		while(!gameOver) {
			System.out.println("\n---------------------------------------\nNEW ROUND"); sleep(1000);
			if(enoughCards) {
				System.out.println("\nInitial Bets"); sleep(500);
				for(int i = 0; i<numPlayers; i++) {
					System.out.println();
					System.out.println(players[i].getName() + ":\t$" + cents.format(players[i].getPoints())); sleep(300);
					double a = -2.0;
					System.out.println("How much would you like to bet?");
					while(a < 0 || a > players[i].getPoints()) {
						System.out.print("BET:\t"); 
						a = scan.nextDouble();
					}
					players[i].gamble(a);
				}
				System.out.println("\n\nDealing...\n");
				for(int i = 0; i < numPlayers; i++)System.out.print(players[i].getName() + "\t\t\t\t"); sleep(850);
				System.out.println();
				for(int i = 0; i < 2; i++) {
					for(int j = 0; j<numPlayers; j++) {
						players[j].hit();
						System.out.print(players[j].getCard(i).getFace() + " of " + players[j].getCard(i).getSuit() + "\t\t\t");
						sleep(650);
					}
					System.out.println();
				}
				dealer.hit(); dealer.hit();
				System.out.println("\nFACE UP:\t" + dealer.getCard(0).getFace() + "\t" + dealer.getCard(0).getSuit());
				
				sleep(950);
				
				System.out.println();
				for(int i = 0; i<numPlayers; i++) {
					if(players[i].getHandValue() == 21) { 
						System.out.println("BLACKJACK");
						blackjack = true;
					}
				}
				
				sleep(750);
				
				if(!roundOver && !blackjack) {
					for(int j = 0; j<numPlayers; j++) {
						System.out.println("\n" + players[j].getName() + ": Would you like to double down? (y/n)");
						if("y".equals(scan.next())) {
							Card g = players[j].hit();
							System.out.println(g.getFace() + "\t" + g.getSuit());
							players[j].gamble(players[j].getBet());
							players[j].setPosition(true);
						}
					}
					System.out.println();		
				}
			}
			else { gameOver = true; roundOver = false;}
			
			if(blackjack == true) roundOver = true;
			
			System.out.println();
			while(!roundOver) {
				for(int i = 0; i <numPlayers; i++) {
					currentPlayer = players[i];
					if(currentPlayer.getHandValue()<22 && !currentPlayer.getPosition()) {
						System.out.println("-------------------");
						System.out.println("\nCURRENT PLAYER:\t" + currentPlayer.getName());
						System.out.println();
						sleep(1000);
						
						for(int c = 0; c < currentPlayer.getHandIndex(); c++) {
							Card x = currentPlayer.getCard(c);
							System.out.println(x.getFace() + "\t" + x.getSuit());
						}
						
						System.out.print("\nHit, up bet, or stand? (h/b/s):\t");
						hs = scan.next();
						
						if("h".equals(hs)) {
							Card place = currentPlayer.hit();
							System.out.println(place.getFace() + "\t" + place.getSuit());
						}
						else if("b".equals(hs)) {
							System.out.println("\tYour current bet is $" + cents.format(currentPlayer.getBet()));
							System.out.println("\tYou currently have $" + cents.format(currentPlayer.getPoints() - currentPlayer.getBet()));
							double d = -1.0;
							System.out.println("\tHow much MORE would you like to bet?");
							while(d < 0 || d > currentPlayer.getPoints()-currentPlayer.getBet()) {
								System.out.print("\tIncrease bet by:\t");
								d = scan.nextDouble();
							}
							currentPlayer.gamble(d);
							System.out.println("\tNEW BET:\t$" + cents.format(currentPlayer.getBet() + "\n"));
							sleep(1000); currentPlayer.hit();
						}
						else currentPlayer.setPosition(true);
						
						if(currentPlayer.getHandValue() > 21) {
							System.out.println("\nBUST");
							currentPlayer.setPosition(true);
						}
					}
					else currentPlayer.setPosition(true);
				}
				
				int a = 0;
				for(int i = 0; i <numPlayers; i++) {
					if(players[i].getPosition() == true)
						a++;
				}
				if(a == numPlayers) {
					System.out.println("\nDEALER:"); sleep(1250);
					System.out.println(dealer.getCard(0).getFace() + "\t" + dealer.getCard(0).getSuit()); sleep(550);
					System.out.println(dealer.getCard(1).getFace() + "\t" + dealer.getCard(1).getSuit()); sleep(550);
					while(dealer.getHandValue() < 17) {
						Card f = dealer.hit();
						System.out.println(f.getFace() + "\t" + f.getSuit());
						sleep(550);
					}
					System.out.println("\n\nROUND OVER\n\n");
					roundOver = true;
				}
			}
			sleep(850);
			if(enoughCards) {
				for(int i = 0; i < numPlayers; i++) {
					System.out.println(players[i].getName() + ":\t" + players[i].getHandValue());
				}
				System.out.println("DEALER:\t" + dealer.getHandValue());
				
				double add = 0;
				System.out.println();
				if(blackjack == true) {
					for(int i = 0; i<numPlayers; i++) {
						add = players[i].getBet();
						if(players[i].getHandValue() == 21 && dealer.getHandValue() != 21) {
							add *= 1.5;
							System.out.println(players[i].getName() + " has won $" + cents.format(add));
							players[i].addPoints(add);
						}
						else if(players[i].getHandValue() == 21 && dealer.getHandValue() == 21) {
							System.out.println(players[i].getName() + ":\tPUSH.");
						}
						else {
							System.out.println(players[i].getName() + " has lost $" + cents.format(add));
							players[i].addPoints(-add);
						}
					}
				}
				else {
					for(int i = 0; i < numPlayers; i++) {
						add = players[i].getBet();
						if(players[i].getHandValue() > 21) {
							if(dealer.getHandValue() > 21) System.out.println(players[i].getName() + ":\tPUSH.");
							else {
								System.out.println(players[i].getName() + " has lost $" + cents.format(add));
								players[i].addPoints(-add);
							}
						}
						else if(players[i].getHandValue() == dealer.getHandValue()){
							System.out.println(players[i].getName() + ":\tPUSH");
						}
						else {
							if(dealer.getHandValue() < 21) {
								if(players[i].getHandValue() > dealer.getHandValue()) {
									System.out.println(players[i].getName() + " has won $" + cents.format(add));
									players[i].addPoints(add);
								}
								else {
									System.out.println(players[i].getName() + " has lost $" + cents.format(add));
									players[i].addPoints(-add);
								}
							}
							else {
								System.out.println(players[i].getName() + " has won $" + cents.format(add));
								players[i].addPoints(add);
							}
						}
					}
				}
				roundOver = false;
				blackjack = false;
				
				for(int i = 0; i < numPlayers; i++) players[i].resetHand();
				
				System.out.println(); sleep(2000);
				int lockedPlayers = 0;
				for(int i = 0; i<numPlayers; i++) {
					if(players[i].getPoints() <= 0) {
						players[i].lock();
						lockedPlayers++;
					}
				}
				if(lockedPlayers < 2) {
					System.out.print("Keep playing? (y/n):\t");
					if("n".equals(scan.next())) gameOver = true;
				}
				else gameOver = true;
			}
			else {
				System.out.println("\n\nNOT ENOUGH CARDS");
				System.out.print("Reshuffle deck? (y/n):\t");
				if("y".equals(scan.next())) {
					deckIndex = 0;
					deck.shuffle();
					roundOver = false;
					enoughCards = true;
					System.out.println();
				}
				else {
					roundOver = true;
					gameOver = true;
				}
			}
		}
		System.out.println("\nGAME OVER"); sleep(1000);
		for(int i = 0; i < numPlayers; i++) {
			System.out.println(players[i].getName() + ":\t$" + cents.format(players[i].getPoints()));
		}
	}
	public static void addDeckIndex() {
		deckIndex++;
	}
	public static Deck getBlackjackDeck() {
		return deck;
	}
	public static int getDeckIndex() {
		return deckIndex;
	}
	public void sleep(int ms) {
		try { Thread.sleep(ms); } catch(InterruptedException ex) { Thread.currentThread().interrupt();}
	}
}