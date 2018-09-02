// Tanguy Cossoul
// APCS Block-4, work due 8/27
// Snake dice game
// Rev.1: using loops and if-statements

import java.util.Scanner;

public class hw_0827_snake_rev1 {
    private static boolean gameOver, turnOver; // flag to record if the game is over
    private static int playerTurn;   // whose turn it is
    private static int dice1, dice2;
    private static int scores[];
    private static int score_turn;
    private static boolean players[]; // HUMAN or ROBOT
    private static String user_response;
    private static final boolean HUMAN = false;
    private static final boolean ROBOT = true;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        gameOver = false;

        players = new boolean[2];
        players[0] = ROBOT;
        players[1] = ROBOT;

        scores = new int[2];

        // Start the game with player 0
        playerTurn = 0;
        while (!gameOver) {
            score_turn = 0;

            // Start a turn
            turnOver = false;
            while (!turnOver) {
                dice1 = roll();
                dice2 = roll();
                System.out.println("Player " + (playerTurn + 1) + " rolled: " + dice1 + " " + dice2);

                if (dice1 == 1 && dice2 == 1) {
                    score_turn = 0;
                    turnOver = true;
                } else if (dice1 == 1 || dice2 == 1) {
                    score_turn += 1;
                    turnOver = true;
                } else if (dice1 == dice2) {
                    score_turn += 4 * dice1; // Twice the sum of the dice
                } else {
                    score_turn += dice1 + dice2;
                }

                // Check if current player wins
                if (scores[playerTurn] + score_turn >= 100) {
                    turnOver = true;
                    gameOver = true;
                }

                // Roll again or hold?
                if (!turnOver) {
                    if (players[playerTurn] == HUMAN) {
                        System.out.println("Player " + (playerTurn + 1) + " current score: " + scores[playerTurn] + ", turn score:" + score_turn);
                        do {
                            System.out.println("Do you want to roll again? [y|n]");
                            user_response = userInput.next();
                        } while (!user_response.equals("y") && !user_response.equals("n"));

                        if (user_response.equals("n")) {
                            turnOver = true;
                        }
                    } else // current player is a robot
                    {
                        turnOver = robotRandom();
                    }
                }
            }
            System.out.println("Player " + (playerTurn + 1) + " new score: " + scores[playerTurn] + " + " + score_turn +
                    " = " + (scores[playerTurn] + score_turn));

            scores[playerTurn] += score_turn;

            // Switch player if turn is over, but game isn't
            if (turnOver && !gameOver) {
                playerTurn = (playerTurn + 1) % 2;
                System.out.println("-----------------");
            }
        }
        // Game is over, announce the winner
        System.out.println("=================");
        System.out.println("Player " + (playerTurn + 1) + " won " + scores[playerTurn] + " to " + scores[(playerTurn + 1) % 2]);
    }

    private static int roll() {
        return (int) (Math.random() * 6 + 1);
    }

    private static boolean robotRandom() {
        return (Math.random() > 0.5);
    }
}