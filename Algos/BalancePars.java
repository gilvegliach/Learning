import java.util.*;

public class BalancePars {
    public static void main(String[] args) {
        assert solution("(").equals("");
        assert solution(")").equals("");
        assert solution(")(").equals("");
        assert solution("())").equals("()");
        assert solution("(()").equals("()");
        assert solution("()((()())").equals("()(()())");        
        assert solution("()(()()))").equals("()(()())");
        System.out.println("OK");       
    }
    
    static String solution(String s) {
        int n = s.length();
        Stack<Integer> lpars = new Stack<>();
        Set<Integer> mark = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                lpars.push(i);
            } else if (!lpars.isEmpty()) {
                lpars.pop();
            } else {
                mark.add(i);
            }
        }
        mark.addAll(lpars);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!mark.contains(i)) sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}

