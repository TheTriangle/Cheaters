package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A junit 5 class testing Deck functionality.
 */
class DeckTest {
    /**
     * method testing getCard method and Deck constructor.
     */
    @Test
    void getCard() {
        Deck testingDeck = new Deck();
        HashSet<Integer> gotCards = new HashSet<Integer>();

        for (int i = 0; i < 10; i++) {
            gotCards.add(testingDeck.getCard());
        }
        Assertions.assertEquals(gotCards, new HashSet<Integer>(List.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})));
        Random generator = new Random();
        for (int i = 0; i < generator.nextInt(10, 20); i++) {
            Assertions.assertEquals(testingDeck.getCard(), 0);
        }
    }

    /**
     * method testing deck constructor and getCards method
     */
    @Test
    void getCards() {
        Deck testingDeck = new Deck();
        Assertions.assertEquals(new HashSet<Integer>(testingDeck.getCards()),
                new HashSet<Integer>(List.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})));
    }
}