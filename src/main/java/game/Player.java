package game;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A class representing player without cheating abilities.
 */
public class Player {
    protected Deck playingDeck;
    protected Thread playingThread;
    private int score;

    /**
     * returns player's score.
     * A synchronized method, meaning score will be correct if someone is stealing from this player.
     * @return current score.
     */
    synchronized public int getScore() {
        return score;
    }

    /**
     * Tries to add a value to score (subtract if negative). If after subtraction score is negative sets score to 0
     * and returns, how much was constructed.
     * A synchronized method, ensuring only one cheater can steal from this player.
     * @param addingValue a value to be added/subtracted
     * @return previous score if full theft was unsuccessful, -addingValue otherwise.
     */
    synchronized int addToScore(int addingValue) {
        if (addingValue + score < 0) {
            int ans = score;
            score = 0;
            return ans;
        }
        score = score + addingValue;
        return -addingValue;
    }

    /**
     * Constructor initializing a player
     * @param givenDeck provided deck of cards
     */
    public Player(Deck givenDeck) {
        playingDeck = givenDeck;
        score = 0;
    }

    /**
     * Method called at the beginning of the game. Starts the thread without stealing.
     */
    public void gameStarted() {
        playingThread = new Thread() {
            @Override
            public void run() {
                do {
                    int card = playingDeck.getCard();
                    addToScore(card);
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
                    } catch (InterruptedException e) {
                        break;
                    }
                } while (!Thread.currentThread().isInterrupted());
            }
        };
        playingThread.start();
    }

    /**
     * Method called at the end of the game. Interrupts and joins playing thread.
     */
    public void gameEnded() {
        playingThread.interrupt();
        try {
            playingThread.join();
        } catch (InterruptedException e) {
            System.out.println("Game interrupted");
        }
    }
}
