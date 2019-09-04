package resource;

public class Deck {
	public Card deck[];
	public int numCards, numDecks;
	
	public Deck(int decksUsed) {
		numDecks = decksUsed;
		numCards = numDecks*52;
		deck = new Card[numCards];
	}
	public void createDeck() {
		System.out.println("Your deck is being created...");
		int cardNum = 0;
		for(int a = 0; a < numDecks; a++) {
			for(int i = 1; i < 5; i++) {
				for(int x = 1; x < 14; x++) {
					deck[cardNum] = new Card(x, i);
					deck[cardNum].setFace();
					deck[cardNum].setSuit();
					cardNum++;
				}
			}
		}
	}
	public void shuffle() {
		Card placeCard = new Card(0, 0);
		int a, b, times;
		times = (int)(Math.random()*1000+100);
		
		System.out.println("Your deck is being shuffled " + times + " times...");
		
		for(int i = 0; i < times; i++) {
			for(int z = 0; z < deck.length/2; z++) {
				a = (int)(Math.random()*52);
				b = (int)(Math.random()*52);
				placeCard = deck[a];
				deck[a] = deck[b];
				deck[b] = placeCard;
			}
		}
	}
	public Card deal(int startIndex) {
		Card placeCard = new Card(0, 0);
		placeCard = deck[startIndex];
		return placeCard;
	}
	public Card[] getDeck() {
		return deck;
	}
}
