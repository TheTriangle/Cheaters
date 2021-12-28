package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A junit 5 class testing Player class
 */
class PlayerTest {
    /**
     * method testing getScore method.
     */
    @Test
    void getScore() {
        Deck someDeck = new Deck();
        Random generator = new Random();
        for (int i = 0; i < 5000; i++) {
            Player randomPlayer = new Player(someDeck);
            int score = generator.nextInt();
            randomPlayer.addToScore(score);

            // Player's score can't be less than 0
            Assertions.assertEquals(Math.max(score, 0), randomPlayer.getScore());
        }
    }

    /**
     * method testing addToScore method
     */
    @Test
    void addToScore() {
        Deck someDeck = new Deck();
        Random generator = new Random();
        for (int i = 0; i < 5000; i++) {                // Doesn't matter for the purposes of the test
            Cheater randomPlayer = new Cheater(someDeck, new ArrayList<Player>(), 0);
            int score = generator.nextInt();
            randomPlayer.addToScore(score);

            // Player's score can't be less than 0
            Assertions.assertEquals(Math.max(score, 0), randomPlayer.getScore());
        }
    }
}