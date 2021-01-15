import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        blackjack();
    }

    public static void blackjack() {
        Deck deck = new Deck();
        ArrayList<Card> dealer = new ArrayList<Card>();
        ArrayList<Card> player = new ArrayList<Card>();
        String input = "";
        boolean badInput;

        // First Draw to dealer
        Card dealerCard = deck.draw();
        dealerCard.flip();
        dealer.add(dealerCard);

        // Second Draw to player
        Card playerCard = deck.draw();
        playerCard.flip();
        player.add(playerCard);

        // Third Draw to dealer (This card is not shown to player)
        dealer.add(deck.draw());

        // Fourth Draw to player (This card is revealed to the player)
        playerCard = deck.draw();
        playerCard.flip();
        player.add(playerCard);
        
        // Displays dealer's hand
        System.out.println("Dealer's hand.");
        for(int i=0; i<dealer.size(); i++) {
            System.out.println(dealer.get(i));
        }
        System.out.println("Value: " + cardValue(dealer) + "\n");

        // Displays your hand
        System.out.println("Your hand.");
        for(int i=0; i<player.size(); i++) {
            System.out.println(player.get(i));
        }
        System.out.println("Value: " + cardValue(player) + "\n");

        // Hit or Stand
        if(cardValue(player) < 21) {
            System.out.println("Hit or Stand");
            Scanner scan = new Scanner(System.in);
            do {
                badInput = false;
                try {
                    while(!input.equals("Stand") || !input.equals("stand")) {
                        input = scan.nextLine();
                        if(input.equals("Hit") || input.equals("hit")) {
                            playerCard = deck.draw();
                            playerCard.flip();
                            player.add(playerCard);
                            System.out.println("\n" + "Your hand.");
                            for(int i=0; i<player.size(); i++) {
                                System.out.println(player.get(i));
                            }
                            System.out.println("Value: " + cardValue(player) + "\n");
                            System.out.println((valueChecker(cardValue(player), player.size())));
                            if(cardValue(player) < 21) {
                                System.out.println("Hit or Stand");
                            }
                            if(valueChecker(cardValue(player), player.size()).equals("Blackjack") || valueChecker(cardValue(player), player.size()).equals("Bust") || cardValue(player) == 21) {
                                break;
                            }
                        } else {
                            if(input.equals("Stand") || input.equals("stand")) {
                                break;
                            } else {
                                throw new InputMismatchException();
                            }
                        }
                    }
                    System.out.println("\n" + "Dealer's hand.");
                    // Dealer flips his cards over and has to hit >= 17 points
                    for(int i=0; i<dealer.size(); i++) {
                        if(dealer.get(i).getFaceUp() == false) {
                            dealer.get(i).flip();
                            System.out.println(dealer.get(i));
                        } else {
                            System.out.println(dealer.get(i));
                        }
                    }
                    while(cardValue(dealer) < 17) {
                        dealerCard = deck.draw();
                        dealerCard.flip();
                        dealer.add(dealerCard);
                        System.out.println(dealerCard);
                    }
                    System.out.println("Value: " + cardValue(dealer) + "\n");
                    System.out.println((valueChecker(cardValue(dealer), player.size())));
                    if(valueChecker(cardValue(player), player.size()).equals("Blackjack") && valueChecker(cardValue(dealer), dealer.size()).equals("Blackjack") || (valueChecker(cardValue(player), player.size()).equals("Bust") && valueChecker(cardValue(dealer), dealer.size()).equals("Bust")) || cardValue(player) == cardValue(dealer)) {
                        System.out.println("Push.");
                    } else {
                        if((valueChecker(cardValue(player), player.size()).equals("Blackjack") && (cardValue(dealer) == 21)) || (valueChecker(cardValue(player), player.size()).equals("Blackjack") && valueChecker(cardValue(dealer), dealer.size()).equals("Bust")) || (valueChecker(cardValue(player), player.size()).equals("Blackjack") && (cardValue(dealer) < 21))) {
                            System.out.println("You win.");
                        } else {
                            if((valueChecker(cardValue(dealer), dealer.size()).equals("Blackjack") && (cardValue(player) == 21)) || (valueChecker(cardValue(dealer), dealer.size()).equals("Blackjack") && valueChecker(cardValue(player), player.size()).equals("Bust")) || (valueChecker(cardValue(dealer), dealer.size()).equals("Blackjack") && (cardValue(player) < 21))) {
                                System.out.println("You lose.");
                            } else {
                                if(valueChecker(cardValue(player), player.size()).equals("Bust") && (cardValue(dealer) < 21)) {
                                    System.out.println("You lose.");
                                } else {
                                    if(valueChecker(cardValue(dealer), dealer.size()).equals("Bust") && (cardValue(player) < 21)) {
                                        System.out.println("You win.");
                                    } else {
                                        if(cardValue(player) > cardValue(dealer)) {
                                            System.out.println("You win.");
                                        } else {
                                            System.out.println("You lose.");
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (InputMismatchException ime) {
                    badInput = true;
                    System.out.println("Hit or Stand");
                }
            } while(badInput);
            scan.close();
        } else {
            System.out.println(valueChecker(cardValue(player), player.size()));
        }
    }

    public static int cardValue(ArrayList<Card> person) {
        int personCardValue = 0;
        int aceCounter = 0;
        for(int i=0; i<person.size(); i++) {
            switch(person.get(i).getKind()) {
                case "0":
                    personCardValue += 0;
                    break;
                case "Ace":
                    if(aceCounter == 0) {
                        personCardValue += 11;
                    } else {
                        personCardValue += 1;
                    }
                    break;
                case "2":
                    personCardValue += 2;
                    break;
                case "3":
                    personCardValue += 3;
                    break;
                case "4":
                    personCardValue += 4;
                    break;
                case "5":
                    personCardValue += 5;
                    break;
                case "6":
                    personCardValue += 6;
                    break;
                case "7":
                    personCardValue += 7;
                    break;
                case "8":
                    personCardValue += 8;
                    break;
                case "9":
                    personCardValue += 9;
                    break;
                case "10":
                    personCardValue += 10;
                    break;
                case "Jack":
                    personCardValue += 10;
                    break;
                case "Queen":
                    personCardValue += 10;
                    break;
                case "King":
                    personCardValue += 10;
                    break;
            }
        }
        return personCardValue;
    }

    public static String valueChecker(int value, int numberOfCards) {
        if(value < 21) {
            return "Hand is " + value;
        } else {
            if(value == 21 && numberOfCards == 2) {
                return "Blackjack";
            } else {
                if(value == 21 && numberOfCards > 2) {
                    return "21";
                } else {
                    return "Bust";
                }
            }
        }
    }
}