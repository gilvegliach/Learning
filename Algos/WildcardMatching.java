// Solves https://leetcode.com/problems/wildcard-matching/
import java.util.*;

public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        switch(p.charAt(0)) {
            case '?': 
                return !s.isEmpty() && isMatch(s.substring(1), p.substring(1));
            case '*':
                String pleft = p.substring(1);
                for (int i = 0; i <= s.length(); i++) {
                    if (isMatch(s.substring(i), pleft)) return true;
                }
                return false;
            default: 
                return (!s.isEmpty() && p.charAt(0) == s.charAt(0)) 
                    && isMatch(s.substring(1), p.substring(1));
        }
    }

    public boolean isMatchFast(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (onlyAsterisks(p)) return true;
        if (s.isEmpty()) return false;    
        
        HashSet<Integer> state = new HashSet<Integer>();
        fill(state, 0, p);
        
        int n = s.length();
        for (int i = 0 ; i < n; i++) {
            char c = s.charAt(i);
            state = next(state, p, c);
            if (state.isEmpty()) return false;
        }
        return onlyAsterisks(p.substring(max(state)));
    }
    
    void fill(HashSet<Integer> state, int start, String p) {
        int n = p.length();
        int j = start;
        for ( ; j < n && p.charAt(j) == '*'; j++) {
            state.add(j);
        }
        state.add(j);
    }
    
    HashSet<Integer> next(HashSet<Integer> state, String p, char c) {
        HashSet<Integer> result = new HashSet<Integer>();
        int n = p.length();
        for (Integer i : state) {
            if (i >= n) continue;
            char ch = p.charAt(i);
            if (ch == '?' || ch == c){
                fill(result, i + 1, p);
            } else if (ch == '*') {
                fill(result, i, p);
            }
        }
        return result;
    }
    
    int max(HashSet<Integer> set) {
        int max = -1;
        for (Integer n : set) {
            if (n > max) max = n;
        }
        return max;
    }
    
    boolean onlyAsterisks(String p) {
        int n = p.length();
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) != '*') return false;
        }
        return true;
    }
}