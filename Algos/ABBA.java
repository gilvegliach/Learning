public class ABBA {
    public static void main(String[] args) {
        ABBA a = new ABBA();
        System.out.println(a.canObtain("B", "ABBA"));
        System.out.println(a.reverse("ABBBAA"));
    }
    // append A
    // reverse, append B
    // initial 1-999
    // target 2-1000 > initial
    public String canObtain(String initial, String target) {
        int lefta = count(target, 'A') - count(initial, 'A');
        int leftb = count(target, 'B') - count(initial, 'B');
        if (lefta < 0 || leftb < 0) return "Impossible";
        return canObtain(initial, target, lefta, leftb) 
            ? "Possible"
            : "Impossible";
    }
    
    boolean canObtain(String initial, String target, int lefta, int leftb) {
        if (lefta == 0 && leftb == 0) 
            return initial.equals(target);
        if (lefta > 0 && canObtain(initial + "A", target, lefta - 1, leftb)) 
            return true;
        if (leftb > 0 && canObtain(reverse(initial) + "B", target, lefta, leftb - 1))
            return true;
        return false;
    }
    
    String reverse(String s) {
    	char[] ca = s.toCharArray();
        for (int i = 0; i < ca.length / 2; i++) 
            ca[i] = ca[ca.length - 1 - i];
        return new String(ca);
    }
    
    int count(String s, char c) {
    	int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) counter++;
        }
		return counter;
    }
}