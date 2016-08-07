import java.util.List;
import java.util.LinkedList;

// 9.10
public class Boxes {
    public static void main(String[] args) {
        int[] w = { 2, 3, 1 };
        int[] h = { 2, 3, 1 };
        int[] d = { 2, 3, 1 };
        int maxHeight = tallest(w, h, d);
        System.out.println(maxHeight);
        assert maxHeight == 6;
    }

    static int tallest(int[] w, int[] h, int[] d) {
        List<Integer> choosen = new LinkedList<>();
        return tallestHelper(w, h, d, choosen);
    }

    static int tallestHelper(int[] w, int[] h, int[] d, List<Integer> choosen) {
        int maxHeight = 0;
        for (int i : choosen) {
            maxHeight += h[i];
        }
        int n = w.length;
        for (int i = 0; i < n; i++) {
            if (/*!choosen.contains(i) &&*/ canBoxBePlacedOnTop(i, w, h, w, choosen)) {
                choosen.add(0, i);
                int currHeight = tallestHelper(w, h, d, choosen);
                choosen.remove(0);
                if (currHeight > maxHeight) {
                    maxHeight = currHeight;
                }
            }
        }
        return maxHeight; 
    }

    static boolean canBoxBePlacedOnTop(int currBox, int[] w, int[] h, int[] d, 
            List<Integer> choosen) {
        if (choosen.isEmpty()) {
            return true;
        }
        int last = choosen.get(0);
        return w[currBox] < w[last] && h[currBox] < h[last] && d[currBox] < d[last];
    }
} 
