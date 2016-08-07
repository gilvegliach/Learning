public class Permutations {
    static void permutations(String s) {
        perm(new StringBuilder(s), new StringBuilder(s.length()));
    }

    static void perm(StringBuilder sb, StringBuilder acc) {
        int len = sb.length();
        if (len == 0) {
            System.out.println(acc);
            return;
        }
        for (int i = 0; i < len; i++) {
            StringBuilder newSb = new StringBuilder(sb);
            newSb.deleteCharAt(i);
            StringBuilder newAcc = new StringBuilder(acc);
            newAcc.append(sb.charAt(i));
            perm(newSb, newAcc);
        } 
    }

    public static void main(String[] args) {
        String s = "abcd";
        permutations(s);
    }
}
