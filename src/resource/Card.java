package resource;

public class Card {
	private int faceValue, suitValue;
	public String face, suit;
	public static Card deck[];
	
	public Card(int newFace, int newSuit){
        faceValue = newFace;
        suitValue = newSuit;
        face = null;
        suit = null;
   }
	public String getFace() {
		return face;
	}
	public int getFaceVal() {
		return faceValue;
	}
	public String getSuit() {
		return suit;
	}
	public void setFace() {
		if(faceValue == 1) {face = "Ace";}
		else if(faceValue == 11) { face = "Jack"; faceValue = 10;}
		else if(faceValue == 12) { face = "Queen"; faceValue = 10;}
		else if(faceValue == 13) { face = "King"; faceValue = 10;}
		else face = Integer.toString(faceValue);
	}
	public void setSuit() {
		if(suitValue == 1) suit = "Spades";
		else if(suitValue == 2) suit = "Hearts";
		else if(suitValue == 3) suit = "Clubs";
		else suit = "Diamonds";
	}
	public void setFaceVal(int i) {
		faceValue = i;
		
	}
}
