// Tanguy Cossoul
// APCS Block-4, work due 9/3
// Snake dice game
// Rev.2: code cleanup
/* Pseudo-code:
    while ( !gameOver )
        turnOver = roll();
        gameOver = checkWinner();

        if (gameOver) {
            updateScore()
        } else if (turnOver || !playAgain() ) {
            updateScore()
            switchUser()
        }
    }
 */
import java.util.Scanner;

public class hw_0903_snake_rev2 {
    private static boolean gameOver = false, turnOver = false;
    private static int player = 0; // whose turn it is, start with player 0
    private static int dice1, dice2;
    private static int scores[];
    private static int score_turn = 0;
    private static boolean players[]; // HUMAN or ROBOT
    private static final boolean HUMAN = false;
    private static final boolean ROBOT = true;

    public static void main(String[] args) {
        players = new boolean[] {HUMAN, ROBOT};
        scores = new int[2];

        while (!gameOver) {
            turnOver = roll();
            gameOver = checkWinner();
            if (gameOver) {
                updateScore();
            } else if ( turnOver || !rollAgain() ) {
                updateScore();
                switchUser();
            }
        }
        announceWinner();
    }

    private static void announceWinner() {
        // Game is over, announce the winner
        System.out.println("=================\n"
                           + "Player " + (player + 1) + " won " + scores[player] + " to " + scores[(player + 1) % 2]);
    }

    private static void switchUser() {
        System.out.println("-----------------");
        player = (player + 1) % 2; // Change player turn
        turnOver = false;
        score_turn = 0;
    }

    private static void updateScore() {
        System.out.println("Player " + (player + 1) + " new score: " + scores[player] + " + " + score_turn
                           + " = " + (scores[player] + score_turn));
        scores[player] += score_turn;
    }

    private static boolean checkWinner() {
        return ( scores[player] + score_turn >= 100 );
    }

    private static boolean roll() {
        dice1 = rollDice();
        dice2 = rollDice();
        System.out.println("Player " + (player + 1) + " rolled: " + dice1 + " " + dice2);

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

        return turnOver;
    }

    private static boolean rollAgain() {
        String user_response;
        Scanner userInput = new Scanner(System.in);

        if (players[player] == HUMAN) {
            System.out.println("Player " + (player + 1) + " current score: " + scores[player] + ", turn score:" + score_turn);
            do {
                System.out.println("Do you want to roll again? [y|n]");
                user_response = userInput.next();
            } while (!user_response.equals("y") && !user_response.equals("n"));
            return user_response.equals("y");
        } else { // current player is a robot
            return robotRandom();
        }
    }

    private static int rollDice() {
        return (int) (Math.random() * 6 + 1);
    }

    private static boolean robotRandom() {
        return (Math.random() > 0.5);
    }
}