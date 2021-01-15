public class Card {
    private final String [] suits = {"Spade", "Clovers", "Hearts", "Diamonds"};
    private final String [] kinds = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private boolean faceUp;
    private String suit;
    private String kind;

    public Card() {
        suit = "";
        kind = "";
    }
    public Card(String newSuit, String newKind) {
        suit = newSuit;
        kind = newKind;
        faceUp = false;
    }

    public String [] getSuits() {
        return suits;
    }
    public String [] getKinds() {
        return kinds;
    }
    public String getSuit() {
        return suit;
    }
    public String getKind() {
        if(faceUp == false) {
            return "0";
        } else {
            return kind;
        }
    }
    public boolean getFaceUp() {
        return faceUp;
    }

    public void flip() {
        if(faceUp == false) {
            faceUp = true;
        } else {
            faceUp = false;
        }
    }

    public String toString() {
        if(faceUp == false) {
            return "The card is face down.";
        } else {
            return kind + " of " + suit;
        }
    }
}