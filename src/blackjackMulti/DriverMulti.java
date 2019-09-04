package blackjackMulti;

//version 1.8
import java.util.Scanner;
public class DriverMulti {
	public static void sleep(int ms) {
		try { Thread.sleep(ms); } catch(InterruptedException ex) { Thread.currentThread().interrupt();}
	}
	public static void main(String[] args) {
		int temp1, temp2;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("BLACKJACK\n"); sleep(1600);
		System.out.println("3:2 blackjack win ratio."); sleep(500);
		System.out.println("It is possible to count cards."); sleep(500);
		System.out.println("No burn."); sleep(500);
		sleep(1000);
		
		
		do {
			System.out.print("\n# of decks (10 max):\t");
			temp1 =  scan.nextInt();
		} while(temp1 < 1 || temp1 >10);
		do {
			System.out.print("# of players (6 max):\t");
			temp2 =  scan.nextInt();
		} while(temp2 < 1 || temp2 >6);
		
		BlackjackMulti game = new BlackjackMulti(temp2, temp1);
		game.play();
	}
}