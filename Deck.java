import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> standardDeck;
    
    public Deck() {
        standardDeck = new ArrayList<Card>();
        deckMaker();
        shuffle();
    }

    public void deckMaker() {
        Card card = new Card();
        for(int i=0; i<card.getSuits().length; i++) {
            for(int j=0; j<card.getKinds().length; j++) {
                Card newCard = new Card(card.getSuits()[i], card.getKinds()[j]);
                standardDeck.add(newCard);
            }
        }
    }

    public void shuffle() {
        int place1;
        int place2;
        Card temp = new Card();
        for(int i=0; i<100; i++) {
            Random shuffle = new Random();
            place1 = shuffle.nextInt(standardDeck.size());
            place2 = shuffle.nextInt(standardDeck.size());
            if(place1 == place2 && place1 == 0) {
                place2++;
            } else {
                if(place1 == place2 && place1 == 51) {
                    place2--;
                }
            }
            temp = standardDeck.remove(place1);
            standardDeck.add(place2, temp);
        }
    }

    public Card draw() {
        return standardDeck.remove(standardDeck.size()-1);
    }
}
