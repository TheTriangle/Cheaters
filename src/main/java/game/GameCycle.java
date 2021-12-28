package game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main game class - manages user input and output.
 */
public class GameCycle {
    private static final Deck playingDeck = new Deck();

    /**
     * Program entry point. Handles the game structure.
     * @param args arguments given to program.
     */
    public static void main(String[] args) {
        System.out.println("Insert amount of non-cheating players");
        int nonCheatersAm = getCorrectNonNegativeInteger();
        System.out.println("Insert amount of cheating players");
        int cheatersAm = getCorrectNonNegativeInteger();

        if (nonCheatersAm + cheatersAm == 0) {
            System.out.println("No one came to play :(");
            return;
        }
        System.out.println("Please, wait. The game is underway.");

        ArrayList<Player> players = new ArrayList<>(nonCheatersAm + cheatersAm);
        fillPlayers(players, cheatersAm, nonCheatersAm);
        startGame(players);

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            System.out.println("Game interrupted!");
        }

        endGame(players);
        printResults(players);
    }

    /**
     * Anounces the player with the highest score.
     * @param players array of all participating players.
     */
    static void printResults(ArrayList<Player> players) {
        int playerInd = 1;
        int maxScore = -1;
        int winnerInd = 1;

        for (Player player : players) {
            if (player.getScore() > maxScore) {
                winnerInd = playerInd;
                maxScore = player.getScore();
            }
            playerInd++;
        }
        System.out.println("Winner is player number " + winnerInd + "!");
        if (players.get(winnerInd - 1) instanceof Cheater) {
            System.out.println("(They are a cheater)");
        }
    }

    /**
     * Ends the game for every player.
     * @param players array of all participating players.
     */
    static void endGame(ArrayList<Player> players) {
        for (Player player : players) {
            player.gameEnded();
        }
    }

    /**
     * Begins the game for every player.
     * @param players array of all participating players.
     */
    static void startGame(ArrayList<Player> players) {
        for (Player player : players) {
            player.gameStarted();
        }
    }

    /**
     * Initializes the players array.
     * @param players array of all participating players.
     */
    static void fillPlayers(ArrayList<Player> players, int cheatersAm, int nonCheatersAm) {
        for (int i = 0; i < nonCheatersAm; i++) {
            players.add(new Player(playingDeck));
        }
        for (int i = 0; i < cheatersAm; i++) {
            players.add(new Cheater(playingDeck, players, nonCheatersAm));
        }
    }

    /**
     * Asks user repeatedly for a correct integer input.
     * @return provided non-negative number.
     */
    static int getCorrectNonNegativeInteger() {
        Scanner scanner = new Scanner(System.in);
        int ans;
        while (true) {
            String nonCheatersAmStr = scanner.nextLine();
            try {
                ans = Integer.parseInt(nonCheatersAmStr);
                if (ans < 0) {
                    System.out.println("It cannot be negative. Please, try again");
                    continue;
                }
            } catch (Exception E) {
                System.out.println("Invalid input. Please, try again");
                continue;
            }
            return ans;
        }
    }
}
