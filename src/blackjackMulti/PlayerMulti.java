package blackjackMulti;

//version 1.8
import resource.Card;

public class PlayerMulti {
	public int handValue, handIndex;
	double points, bet;
	public Card hand[];
	public String name;
	public boolean stand, locked;
	
	public PlayerMulti(String setName) {
		stand = false;
		locked = false;
		handValue = 0;
		handIndex = 0;
		points = 500.00;
		bet = 0.0;
		name = setName;
		hand = new Card[12];
		for(int i = 0; i < 12; i ++) {
			hand[i] = new Card(0, 0);
		}
	}
	public Card hit() {
		if(!locked) {
			if(BlackjackMulti.getDeckIndex() < BlackjackMulti.getBlackjackDeck().getDeck().length){
				Card c = BlackjackMulti.getBlackjackDeck().deal(BlackjackMulti.getDeckIndex());
				hand[handIndex] = c;
				handValue += hand[handIndex].getFaceVal();
				
				for(int i = 0; i < handIndex+1; i ++) {
					if(hand[i].getFaceVal() == 1) {
						if(handValue+10 < 22) {
							hand[i].setFaceVal(11);
							handValue += 10;
						}
					}
					if(handValue > 21 && hand[i].getFaceVal() == 11) {
						hand[i].setFaceVal(1);
						handValue -= 10;
					}
				}
				BlackjackMulti.addDeckIndex(); handIndex++;
				return hand[handIndex-1];
			}
			else {
				BlackjackMulti.enoughCards = false;
				BlackjackMulti.roundOver = true;
				return new Card(0, 0);
			}
		}
		else {
			System.out.println("LOCKED");
			Card b = new Card(0, 0);
			return b;
		}
		
	}
	public Card getCard(int ind) {
		return hand[ind];
	}
	public int getHandValue() {
		return handValue;
	}
	public Card[] getHand() {
		return hand;
	}
	public void resetHand() {
		handValue = 0;
		handIndex = 0;
		bet = 0;
		stand = false;
		for(int i = 0; i < 12; i ++) {
			hand[i] = new Card(0, 0);
		}
	}
	public double getPoints() {
		return points;
	}
	public void addPoints(double x) {
		if(!locked) {
		points += x;
		}
		else {
			System.out.println("LOCKED");
		}
	}
	public void gamble(double x) {
		if(!locked) {
			bet += x;
		}
		else {
			System.out.println("LOCKED");
		}
	}
	public double getBet() {
		return bet;
	}
	public int getHandIndex() {
		if(!locked)
			return handIndex;
		else {
			System.out.println("LOCKED");
			return 0;
		}
	}
	public String getName() {
		return name;
	}
	public void setPosition(boolean position) {
		stand = position;
	}
	public boolean getPosition() {
		return stand;
	}
	public void lock() {
		locked = true;
	}
}