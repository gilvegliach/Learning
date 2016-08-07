public class TicTacToe {
    public static void main(String[] args) {
        int[][] a = {
            { 1, -1, -1 },
            { 0,  0,  0 },
            { 1,  1,  1 }
       };
       System.out.println(winner(a)); 

       int[][] b = {
            { 1, -1, -1 },
            { 0,  0,  1 },
            { 1,  0,  1 }
       };
       System.out.println(winner(b)); 
    }
    
    static int winner(int[][] b) {
        if (winner(b, 1)) return 1;
        if (winner(b, -1)) return -1;
        return 0;
    }

    static boolean winner(int[][] b, int who) {
        int n = b.length;
        boolean won = false;
        outer: for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (b[i][j] != who) continue outer;
            }
            won = true;
        }
        if (won) return true;
        outer: for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (b[i][j] != who) continue outer;
            }
            won = true;
        }
        if (won) return true;
        won = true;
        for (int i = 0; i < n; i++) {
            if (b[i][i] != who) {
                won = false;
                break;
            }
        }
        if (won) return true;
        won = true;
        for (int i = 0; i < n; i++) {
            if (b[i][n - i - 1] != who) {
                won = false;
                break;
            }
        }
        return won;
    }
}
