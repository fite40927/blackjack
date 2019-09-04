package blackjackSingle;

import java.util.Scanner;

//version 1.8

public class DriverSingle {
	public static void sleep(int ms) {
		try { Thread.sleep(ms); } catch(InterruptedException ex) { Thread.currentThread().interrupt();}
	}
	public static void main(String[] args) {
		int temp1;
		Scanner scan = new Scanner(System.in);
		System.out.println("BLACKJACK\n"); sleep(1600);
		System.out.println("3:2 pay."); sleep(500);
		System.out.println("It's possible to count cards."); sleep(500);
		System.out.println("No burn."); sleep(500);
		System.out.println("Dealer hits on 16 and below"); sleep(800);
		do{
			System.out.print("\n# of decks (10 max):\t");
			temp1 =  scan.nextInt();
		} while(temp1 < 1 || temp1 > 10);
		
		BlackjackSingle game = new BlackjackSingle(temp1);
		game.play();
	}
}