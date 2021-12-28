package game;

import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A junit 5 class testing main game cycle functionality
 */
class GameCycleTest {
    /**
     * method testing result announcement.
     */
    @org.junit.jupiter.api.Test
    void printResults() {
        ArrayList<Player> players = new ArrayList<>();
        Random generator = new Random();
        int playersAm = generator.nextInt(100);
        int nonCheatingAm = generator.nextInt(playersAm);
        int maxScore = generator.nextInt(50, 100);
        GameCycle.fillPlayers(players, playersAm - nonCheatingAm, nonCheatingAm);

        int randomPlayerInd = generator.nextInt(playersAm);
        players.get(randomPlayerInd).addToScore(-players.get(randomPlayerInd).getScore()); // set random score to 0
        players.get(randomPlayerInd).addToScore(maxScore); // set random score to the highest score possible

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));
        GameCycle.printResults(players);
        String expectedMessage = "Winner is player number " + (randomPlayerInd + 1) + "!\r\n" +
                (players.get(randomPlayerInd) instanceof Cheater ? "(They are a cheater)\r\n" : "");

        Assertions.assertEquals(expectedMessage, outContent.toString()); // asserts correct output to System.out
        System.setOut(originalOut);
    }

    /**
     * Method testing GameCycle fillPlayers method.
     */
    @org.junit.jupiter.api.Test
    void fillPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        Random generator = new Random();
        int cheatersAm = generator.nextInt(100);
        int nonCheatersAm = generator.nextInt(100);

        GameCycle.fillPlayers(players, cheatersAm, nonCheatersAm);
        int actualCheatersAm = 0;
        int actualNonCheatersAm = 0;
        for (Player player : players) {
            if (player instanceof Cheater) {
                actualCheatersAm++;
            } else {
                actualNonCheatersAm++;
            }
        }
        Assertions.assertEquals(actualCheatersAm, cheatersAm);
        Assertions.assertEquals(actualNonCheatersAm, nonCheatersAm);
    }
}