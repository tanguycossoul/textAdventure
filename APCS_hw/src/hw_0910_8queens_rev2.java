public class hw_0910_8queens_rev2 {
    private static final int BOARD_SIZE = 8;
    private static final int QUEEN = 9;
    private static int solution_count = 0;
    public static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) {
        display8QueenSolutions();
    }

    private static void display8QueenSolutions() {
        for (int row0 = 0; row0 < BOARD_SIZE; row0++) {
            for (int row1 = 0; row1 < BOARD_SIZE; row1++) {
                for (int row2 = 0; row2 < BOARD_SIZE; row2++) {
                    for (int row3 = 0; row3 < BOARD_SIZE; row3++) {
                        for (int row4 = 0; row4 < BOARD_SIZE; row4++) {
                            for (int row5 = 0; row5 < BOARD_SIZE; row5++) {
                                for (int row6 = 0; row6 < BOARD_SIZE; row6++) {
                                    for (int row7 = 0; row7 < BOARD_SIZE; row7++) {
                                        int[][] board = makeBoard(row0, row1, row2, row3, row4, row5, row6, row7);
                                        if ( isSolution(board) ) printBoard(board);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static int[][] makeBoard(int row0, int row1, int row2, int row3, int row4, int row5, int row6, int row7 ) {
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        board[0][row0] = QUEEN;
        board[1][row1] = QUEEN;
        board[2][row2] = QUEEN;
        board[3][row3] = QUEEN;
        board[4][row4] = QUEEN;
        board[5][row5] = QUEEN;
        board[6][row6] = QUEEN;
        board[7][row7] = QUEEN;
//        board[0][3] = QUEEN;
//        board[1][7] = QUEEN;
//        board[2][0] = QUEEN;
//        board[3][2] = QUEEN;
//        board[4][5] = QUEEN;
//        board[5][1] = QUEEN;
//        board[6][6] = QUEEN;
//        board[7][4] = QUEEN;

        return board;
    }

    private static boolean isSolution(int[][] board) {
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                if (board[r][c] == QUEEN) {
                    int count_row = countQueensInRow(board, r);
                    int count_col = countQueensInCol(board, c);
                    int count_diagonals = countQueensInDiagonals(board, r, c);
                    if (count_row > 1 || count_col > 1 || count_diagonals > 4 ) return false;
                }
            }
        }
        return true;
    }

    private static int countQueensInRow(int[][] board, int r) {
        int count = 0;
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[r][col] == QUEEN)
                count++;
        }
        return count;
    }

    private static int countQueensInCol(int[][] board, int c) {
        int count = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][c] == QUEEN)
                count++;
        }
        return count;
    }

    private static int countQueensInDiagonals(int[][] board, int r, int c) {
        int count = 0;
        int row = r;
        int col = c;
        while (row < BOARD_SIZE && col < BOARD_SIZE) {
            if (board[row++][col++] == QUEEN)
                count++;
        }

        row = r;
        col = c;
        while (row >= 0 && col < BOARD_SIZE) {
            if (board[row--][col++] == QUEEN)
                count++;
        }

        row = r;
        col = c;
        while (row < BOARD_SIZE && col >= 0) {
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
        System.out.println("-- Solution #" + ++solution_count + " ------");
        for (int r = BOARD_SIZE - 1; r >= 0; r-- ) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                System.out.print( board[r][c] );
            }
            System.out.print("\n");
        }
    }
}