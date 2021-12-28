package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class representing a cheating player.
 */
public class Cheater extends Player {
    private final ArrayList<Player> players;
    private final int nonCheatersAm;

    /**
     * Constructor of a cheater.
     * @param givenDeck deck of cards within the game.
     * @param givenPlayers players of the game. Cheaters must go after normal players.
     * @param nonCheatingPlayersAm the amount of non cheating players.
     */
    public Cheater(Deck givenDeck, ArrayList<Player> givenPlayers, int nonCheatingPlayersAm) {
        super(givenDeck);
        players = givenPlayers;
        nonCheatersAm = nonCheatingPlayersAm;
    }

    /**
     * Method called at the beginning of the game. Starts the thread with stealing.
     */
    @Override
    public void gameStarted() {
        playingThread = new Thread(() -> {
            do {
                int sleepingTime;
                if ((ThreadLocalRandom.current().nextDouble(1) < 0.4) && (nonCheatersAm > 0)) { // steal
                    int stolenPointsAm = ThreadLocalRandom.current().nextInt(9);
                    int stealingPlayer = ThreadLocalRandom.current().nextInt(nonCheatersAm);

                    int succesfullyStolen = players.get(stealingPlayer).addToScore(-stolenPointsAm);
                    addToScore(succesfullyStolen);
                    sleepingTime = ThreadLocalRandom.current().nextInt(180, 300);
                } else { // take a card
                    int card = playingDeck.getCard();
                    addToScore(card);
                    sleepingTime = ThreadLocalRandom.current().nextInt(100, 200);
                }
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                    break;
                }
            } while (!Thread.currentThread().isInterrupted());
        });
        playingThread.start();
    }
}
