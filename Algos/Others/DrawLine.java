public class DrawLine {
    static void printScreen(byte[] screen, int w) {
        int h = screen.length * 8 / w;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(get(screen, w, i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    static int get(byte[] screen, int w, int i, int j) {
       int pos = i * w + j;
       int q = pos / 8;
       int r = pos % 8;
       return (screen[q] >> (7 - r)) & 1;
    }

    static void drawLine(byte[] screen, int w, int i, int j1, int j2) {
        for (int j = j1; j <= j2; j++) {
            set(screen, w, i, j);
        }
    }

    static void set(byte[] screen, int w, int i, int j) {
        int pos = i * w + j;
        int q = pos / 8;
        int r = pos % 8;
        screen[q] |= 1 << (7 - r);
    }

    public static void main(String[] args) {
        byte[] s = new byte[]{ 0, 0, 0, 0, 0, 0, 0, 0 };
        int w = 16;
        printScreen(s, w);
        drawLine(s, w, 2, 3, 9);
        printScreen(s, w);
    }
}
