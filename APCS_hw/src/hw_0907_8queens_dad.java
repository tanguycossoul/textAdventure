/* Eight queens puzzle:
- Given an 8 x 8 chess board, place 8 queens so that none can threaten each other.
- (a) You can write a method: public static boolean isValidSolution( … board positions …) { …. }
which checks a board to ensure no queens can threaten each other.
(b). Then you can write a brute force solution which checks all possible arrangements of queens!
 */

public class hw_0907_8queens_dad {
    private static final int GRID_SIZE = 8;
    private static int[][] grid  = new int[GRID_SIZE][GRID_SIZE];
    private static int num_tiles_free = GRID_SIZE * GRID_SIZE;
    private static int num_queens = 0;

    public static void main(String[] args) {
        bruteForce();
    }

    private static void bruteForce() {
        for (int r=0; r < GRID_SIZE; r++ ) {
            for (int c=0; c < GRID_SIZE; c++) {
                if ( grid[r][c] == 0 ) {
                    addNewQueen(r, c);
                    displayGrid();
                    if (num_queens == GRID_SIZE || num_tiles_free == 0) break;
                }
            }
        }
        System.out.println("DONE! Placed " + num_queens + " queens (num_tiles_free == " + num_tiles_free + ")");
    }

    // Disable all tiles in row r, col c, and diagonals
    private static void addNewQueen(int row, int col) {
        for (int r=0; r < GRID_SIZE; r++ ) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (r == row && c == col) {
                    grid[r][c] = 9;
                    num_queens++;
                    num_tiles_free--;
                }
                else if (r == row || c == col || Math.abs(r - row) == Math.abs(c - col)) {
                    if (grid[r][c] == 0) {
                        grid[r][c] = 1;
                        num_tiles_free--;
                    }
                }
            }
        }
    }

    private static void displayGrid() {
        System.out.println("--------");
        for (int r = GRID_SIZE - 1; r >= 0; r-- ) {
            for (int c = 0; c < GRID_SIZE; c++) {
                System.out.print(grid[r][c]);
            }
            System.out.print("\n");
        }
    }
}