public class GameOfLife {

    public static int[][] iterate(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] output = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (shouldBeAlive(board, i, j)) {
                    output[i][j] = 1;
                }
            }
        }

        return output;
    }

    private static boolean shouldBeAlive(int[][] board, int i, int j) {
        int aliveNeighbors = countAliveNeighbors(board, i, j);
        return (isAlive(board, i, j) && (aliveNeighbors == 2 || aliveNeighbors == 3))
                || (aliveNeighbors == 3);
    }

    private static boolean isAlive(int[][] board, int i, int j) {
        return board[i][j] == 1;
    }

    static int countAliveNeighbors(int[][] board, int i, int j) {
        int n = board.length;
        int m = board[0].length;
        int count = 0;
        int[][] b = board;

        if (i > 0 && isAlive(b, i - 1, j)) count++;
        if (i < (n - 1) && isAlive(b, i + 1, j)) count++;

        if (j > 0 && isAlive(b, i, j - 1)) count++;
        if (j < (m - 1) && isAlive(b, i, j + 1)) count++;

        if (i > 0 && j > 0 && isAlive(b, i - 1, j - 1)) count++;
        if (i > 0 && j < (m - 1) && isAlive(b, i - 1, j + 1)) count++;

        if (i < (n - 1) && j > 0 && isAlive(b, i + 1, j - 1)) count++;
        if (i < (n - 1) && j < (m - 1) && isAlive(b, i + 1, j + 1)) count++;

        return count;
    }
}