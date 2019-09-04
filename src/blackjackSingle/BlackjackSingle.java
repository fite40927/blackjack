package blackjackSingle;

import java.util.Scanner;
import java.text.DecimalFormat;
import resource.Deck;

public class BlackjackSingle {
	public static boolean roundOver, enoughCards, gameOver, userStand, programStand, blackjack;
	public static Deck deck;
	public static PlayerSingle user, program;
	public static int deckIndex, numDecks;
	public String hs;
	public Scanner scan = new Scanner(System.in);
	public double insurance;
	public DecimalFormat cents = new DecimalFormat("#.00");
	
	
	public BlackjackSingle(int NUMDecks) {
		gameOver = false;
		roundOver = false;
		user = new PlayerSingle();
		program = new PlayerSingle();
		deckIndex = 0;
		userStand = false;
		programStand = false;
		blackjack = false;
		enoughCards = true;
		numDecks = NUMDecks;
		deck = new Deck(numDecks);
	}
	public void play() {
		System.out.print("\nEnter any key to start:\t");
		scan.next();		
		System.out.println();
		
		deck.createDeck(); sleep(900);
		deck.shuffle(); sleep(900);
		
		System.out.println("\n__________________________________________________");
		while(!gameOver) {
			if(enoughCards) {
				System.out.println("\nYou currently have $" + cents.format(user.getPoints()));
				
				double a = -2;
				System.out.println("How much would you like to bet?");
				while(a < 0 || a > user.getPoints()) {
					System.out.print("BET:\t"); 
					a = scan.nextDouble();
				}
				user.gamble(a);
				
				System.out.println("\nDealing..."); sleep(150);
				user.hit(); program.hit(); sleep(750);
				user.hit(); program.hit(); sleep(750);
				System.out.print("\t\t\t\t\t");
				System.out.println("FACE-UP CARD:\n\t\t\t\t\t" + program.getCard(0).getFace() + "\t" + program.getCard(0).getSuit());
				
				sleep(750);
				
				if("Ace".equals(program.getCard(0).getFace())) {
					System.out.print("Would you like to take insurance? (y/n):\t");
					if("y".equals(scan.next())) 
						insurance = user.getBet()*.5;
					if(program.getHand() == 21) user.gamble(-user.getBet());
					else user.gamble(insurance);
					
					sleep(750);
				}
				
				if(program.getHand() == 21 || user.getHand() == 21) { 
					System.out.println("\nBLACKJACK");
					blackjack = true;
				}
				else {				
					System.out.print("\nWould you like to double down?(y/n):\t");
					if("y".equals(scan.next())) {
						user.hit();
						user.gamble(user.getBet());
						userStand = true;
						if(user.getHand() > 21)	System.out.println("BUST");
					}
				}
			}
			else { gameOver = true; roundOver = false;}
			
			if(blackjack == true) roundOver = true;
			
			while(!roundOver) {
				if(program.getHand() < 17) program.hit();
				else programStand = true;
				
				if(user.getHand()<22 && !userStand) {
					sleep(750);
					int b = user.getHand();
					System.out.println("\nYOUR HAND VALUE:\t" + b);
					sleep(1000);	
					
					System.out.print("Hit, up bet, or stand? (h/b/s):\t");
					hs = scan.next();
					
					if("h".equals(hs)) user.hit();
					else if("b".equals(hs)) {
						System.out.println("\tYour current bet is $" + cents.format(user.getBet()));
						System.out.println("\tYou currently have $" + cents.format(user.getPoints() - user.getBet()));
						double d = -1;
						System.out.println("\tHow much MORE would you like to bet?");
						while(d < 0 || d > user.getPoints()-user.getBet()) {
							System.out.print("\tIncrease bet by:\t");
							d = scan.nextDouble();
						}
						user.gamble(d);
						System.out.println("\tNEW BET:\t" + user.getBet() + "\n");
						sleep(1000); user.hit();
					}
					else userStand = true;
					
					if(user.getHand() > 21) {
						System.out.println("BUST");
						userStand = true;
					}
				}
				else userStand = true;
				
				if(programStand == true && userStand == true) {
					sleep(500);
					System.out.println("\nDealer's hand:");
					sleep(550);
					for(int i = 0; program.getCard(i).getFaceVal() != 0; i++)
						System.out.println("\t" + program.getCard(i).getFace() + "\t" + program.getCard(i).getSuit());
					System.out.println("SCORE:\t" + program.getHand());
					sleep(1700);
					System.out.println("\nROUND OVER"); roundOver = true;
				}
				else;
			}
			sleep(850);
			if(enoughCards) {
				System.out.println("\nUSER:\t" + user.getHand());
				System.out.println("DEALER:\t" + program.getHand());
				sleep(1500);
				
				double add = user.getBet();
				System.out.println();
				if(blackjack == true) {
					if(user.getHand() > program.getHand()) {
						add *= 1.5;
						System.out.println("You have won $" + cents.format(add));
						user.addPoints(add);
					}
					else {
						System.out.println("The dealer has won.");
						user.addPoints(-add);
						System.out.println("You have lost $" + cents.format(add));
					}
				}
				else if(user.getHand() < 22) {
					if(program.getHand() < user.getHand() || program.getHand() > 21) {
						System.out.println("You have won $" + cents.format(add));
						user.addPoints(add);
					}
					else if(program.getHand() == user.getHand()) 
						System.out.println("Push. No winner.");
					else {
						System.out.println("The dealer has won.");
						user.addPoints(-add);
						System.out.println("You have lost $" + cents.format(add));
					}
				}
				else {
					if(program.getHand() > 21)
						System.out.println("Push. No winner.");
					else {
						System.out.println("The dealer has won.");
						user.addPoints(-add);
						System.out.println("You have lost $" + cents.format(add));
					}
				}
				
				user.resetHand();
				program.resetHand();
				roundOver = false;
				userStand = false; programStand = false;
				blackjack = false;
				
				System.out.println(); sleep(2000);
				if(user.getPoints() > 0) {
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
		System.out.println("You have $" + cents.format(user.getPoints()));
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