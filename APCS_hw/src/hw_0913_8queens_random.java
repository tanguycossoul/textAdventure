public class hw_0913_8queens_random
{
    private static final int SIZE = 8;
    private static final int QUEEN = 1;

    public static void main(String[] args) {
        solvePuzzle( SIZE );
    }

    private static void solvePuzzle(int n) {
        boolean solved = false;
        while (!solved) {
            int[][] board = generateRandomBoard(n);
            if ( isSolution( board ) ) {
                printBoard( board );
                solved = true;
            }
        }
    }

    private static int[][] generateRandomBoard(int n) {
        int[][] board = new int[n][n];
        for (int row = 0; row < board.length; row++) {
            int col = (int) (Math.random() * board.length);
            board[row][col] = QUEEN;
        }
        return board;
    }

    private static boolean isSolution(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c] == QUEEN) {
                    int count_row = countQueensInRow(board, r);
                    int count_col = countQueensInCol(board, c);
                    int count_diagonals = countQueensInDiagonals(board, r, c);
                    if (count_row > 1 || count_col > 1 || count_diagonals > 4 )
                        return false;
                }
            }
        }
        return true;
    }

    private static int countQueensInRow(int[][] board, int r) {
        int count = 0;
        for (int col = 0; col < board.length; col++) {
            if (board[r][col] == QUEEN)
                count++;
        }
        return count;
    }

    private static int countQueensInCol(int[][] board, int c) {
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            if (board[row][c] == QUEEN)
                count++;
        }
        return count;
    }

    private static int countQueensInDiagonals(int[][] board, int r, int c) {
        int count = 0;
        int row = r;
        int col = c;
        while (row < board.length && col < board.length) {
            if (board[row++][col++] == QUEEN)
                count++;
        }

        row = r;
        col = c;
        while (row >= 0 && col < board.length) {
            if (board[row--][col++] == QUEEN)
                count++;
        }

        row = r;
        col = c;
        while (row < board.length && col >= 0) {
            if (board[row++][col--] == QUEEN)
                count++;
        }

        row = r;
        col = c;
        while (row >= 0 && col >= 0) {
            if (board[row--][col--] == QUEEN)
                count++;
        }
        return count;
    }

    private static void printBoard(int[][] board) {
        for (int r = board.length - 1; r >= 0; r-- ) {
            for (int c = 0; c < board.length; c++) {
                System.out.print( board[r][c] );
            }
            System.out.print("\n");
        }
    }
}