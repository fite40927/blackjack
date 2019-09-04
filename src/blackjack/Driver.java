package blackjack;

/*
version 1.0: twenty-one: cards can be dealt; hand kept track of; money kept track of
version 1.1: jack, queen, king cards given values of 10; betting enabled
version 1.2: aces can be 11s or 1s; blackjack enabled
version 1.3: user chooses number of decks; random number of shuffles; formatting
version 1.4: player class moved to separate file; created driver class
version 1.5: resolve undefined array issue; formatting
version 1.6: fix BUST BUST error; fix NOT ENOUGH CARDS error
version 1.7: fix ace value error
version 1.8: formatting; adjusted to follow standard playing rules
version 2.0: create multiplayer class
version 2.1: new driver class; single player and multiplayer options
version 2.2: make it easier for players to see/remember their hand before taking action (multi)
version 2.3: fix resetHand-stand error (multi); formatting: Queen of Diamonds tab, sleep
version 2.4: fix win/loss errors (mutli)
version 2.5: add doubling down; add insurance; you can bet cents
version 2.6: can't bet fractions of cents; add dealer in multiplayer
>>>>>>>> version 2.7: clean up code; formatting: sleep, clarity <<<<<<<<
version 3.0: applet?
*/

import blackjackMulti.BlackjackMulti;
import blackjackSingle.BlackjackSingle;
import java.util.Scanner;

public class Driver {
	public static void sleep(int ms) {
		try { Thread.sleep(ms); } catch(InterruptedException ex) { Thread.currentThread().interrupt();}
	}
	public static void main(String[] args) {
		int temp1, temp2;
		Scanner scan = new Scanner(System.in);
		System.out.println("BLACKJACK\n"); sleep(1600);
		System.out.println("3:2 pay."); sleep(500);
		System.out.println("It's possible to count cards."); sleep(500);
		System.out.println("No burn."); sleep(500);
		
		System.out.println("\n1. Single Player \t2. Multiplayer");
		int choice = scan.nextInt();
		System.out.println("_______________________________________________________________________________");
		
		if(choice == 2) {
			System.out.println("MULTIPLAYER MODE\n");
			do {
				System.out.print("# of decks (10 max):\t");
				temp1 =  scan.nextInt();
			} while(temp1 < 1 || temp1 >10);
			do {
				System.out.print("# of players (6 max):\t");
				temp2 =  scan.nextInt();
			} while(temp2 < 2 || temp2 >6);
			
			BlackjackMulti game = new BlackjackMulti(temp2, temp1);
			game.play();
		}
		else {
			System.out.println("SINGLE PLAYER MODE\nDealer hits on 16 and below");
			sleep(1000);
			do{
				System.out.print("\n# of decks (10 max):\t");
				temp1 =  scan.nextInt();
			} while(temp1 < 1 || temp1 > 10);
			
			BlackjackSingle game = new BlackjackSingle(temp1);
			game.play();
		}
	}
}