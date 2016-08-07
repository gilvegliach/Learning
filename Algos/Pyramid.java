public class Pyramid {
    static void solve(String left, String right, String s) {
        if ("".equals(s)) return;
        int len = s.length();
        printSpaces(len / 2);
        System.out.println(left + right);
        solve(left + s.charAt(0), s.charAt(len - 1) + right,
               s.substring(1, len - 1)); 
    }
    
    static void solve(String s) {
        solve("","",s);
    }

    static void printSpaces(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        solve("abcdefghij");
        solve("abcdefghi");
    }
}
