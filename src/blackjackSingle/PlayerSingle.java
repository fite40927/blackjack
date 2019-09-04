package blackjackSingle;

//version 1.8
import resource.Card;

public class PlayerSingle {
	public int handValue, handIndex;
	public double bet, points;
	public Card hand[];
	public PlayerSingle() {
		handValue = 0;
		handIndex = 0;
		points = 500.0;
		bet = 0.0;
		hand = new Card[12];
		for(int i = 0; i < 12; i ++) {
			hand[i] = new Card(0, 0);
		}
	}
	public Card hit() {
		if(BlackjackSingle.getDeckIndex() < BlackjackSingle.getBlackjackDeck().getDeck().length){
			Card c = BlackjackSingle.getBlackjackDeck().deal(BlackjackSingle.getDeckIndex());
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
			if(this == BlackjackSingle.user) 
				System.out.println(hand[handIndex].getFace() + "\t" + hand[handIndex].getSuit());
			BlackjackSingle.addDeckIndex(); handIndex++;
			return hand[handIndex];
		}
		else {
			BlackjackSingle.enoughCards = false;
			BlackjackSingle.roundOver = true;
			return new Card(0, 0);
		}
		
	}
	public Card getCard(int ind) {
		return hand[ind];
	}
	public int getHand() {
		return handValue;
	}
	public void resetHand() {
		handValue = 0;
		handIndex = 0;
		bet = 0.0;
		for(int i = 0; i < 12; i ++) {
			hand[i] = new Card(0, 0);
		}
	}
	public double getPoints() {
		return points;
	}
	public void addPoints(double x) {
		points += x;
	}
	public void gamble(double x) {
		bet += x;
	}
	public double getBet() {
		return bet;
	}
	public int getHandIndex() {
		return handIndex;
	}
}