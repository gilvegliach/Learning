public class Fill {
    static void fill(int[][] a, int i, int j, int color) {
        if (a == null || a.length == 0
                || a[0].length == 0) return;
        if (i >= a.length || j >= a[0].length) return;
        int oldCol = a[i][j];
        fillHelper(a, i, j, oldCol, color);
    }

    private static void fillHelper(int[][] a, int i, int j,
            int oldc, int newc) {
        int m = a.length;
        int n = a[0].length;
        if (i < 0 || i >= m) return;
        if (j < 0 || j >= n) return;
        if (a[i][j] == oldc) {
            a[i][j] = newc;
            fillHelper(a, i - 1, j, oldc, newc);
            fillHelper(a, i + 1, j, oldc, newc);
            fillHelper(a, i, j - 1, oldc, newc);
            fillHelper(a, i, j + 1, oldc, newc);
        }
    }

    static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
            { 0, 0, 0, 1, 0, 0, 0 },
            { 0, 0, 1, 0, 1, 0, 0 },
            { 0, 1, 0, 0, 0, 1, 0 },
            { 0, 0, 1, 1, 1, 0, 0 } };
    
        print(a);
        System.out.println();
        fill(a, 2, 3, 2);
        print(a);
        System.out.println();
        fill(a, 0, 0, 5);
        print(a);
        
    }
}