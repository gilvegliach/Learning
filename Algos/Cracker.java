import java.util.*;

public class Cracker {
    static List<Integer> secret = new ArrayList<Integer>() {{
        add(2); add(3); add(5); add(1); add(8); add(6);
    }};
    
    static int MIN_DIGITS = 1;
    static int MAX_DIGITS = 9;

    static List<Integer> crack(List<Integer> left, List<Integer> choosen, 
            int depth) {
        if (left.isEmpty() || depth == 0) {
            if (choosen.equals(secret)) return choosen;
            else return null;
        }

        for (int i = 0; i < left.size(); i++) {
            int choice = left.get(i);
            left.remove(i);
            choosen.add(choice);
            List<Integer> res = crack(left, choosen, depth - 1);
            left.add(i, choice);
            if (res != null) return res;
            choosen.remove(choosen.size() - 1);
        }
        return null;
    }

    public static List<Integer> crack() {
        for (int i = MIN_DIGITS; i <= MAX_DIGITS; i++) {
            List<Integer> left = new ArrayList<Integer>() {{
                add(1); add(2); add(3);
                add(4); add(5); add(6);
                add(7); add(8); add(9);
            }};
            List<Integer> choosen = new ArrayList<Integer>();
            List<Integer> res = crack(left, choosen, i);
            if (res != null) return res;
        }
        return null;
    }
    
    public static void print(List<Integer> board) {
        for (int i = 1; i < 10; i++) {
            System.out.print(board.contains(i) ? board.indexOf(i) + 1 : ".");
            if (i % 3 == 0) System.out.println();
        }
    }

    public static void main(String[] args) {
        print(secret);
        System.out.println();
        print(crack()); // should be the same!
    }
}
