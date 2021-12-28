package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class representing a deck of playing cards.
 */
public class Deck {
    private final ArrayList<Integer> cards = new ArrayList<Integer>();

    public ArrayList<Integer> getCards() {
        return new ArrayList<Integer>(cards);
    }

    /**
     * Constructor of Deck class - initialize cards with shuffled numbers 1 through 10.
     */
    public Deck() {
        ArrayList<Integer> availableCards = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Random generator = new Random();
        for (int i = 0; i < 10; i++) {
            int randomCard = generator.nextInt(0, availableCards.size());
            cards.add(availableCards.get(randomCard));
            availableCards.remove(randomCard);
        }
    }

    /**
     * Pulls a card out of a deck. A synchronized method - only one player can pull a card from a deck at a time!
     * @return a top card from the deck.
     */
    public synchronized int getCard() {
        if (cards.size() == 0) {
            return 0;
        }
        int topCard = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return topCard;
    }
}
