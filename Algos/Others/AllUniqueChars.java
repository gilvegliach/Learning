import java.util.Set;
import java.util.TreeSet;

public class AllUniqueChars {
    interface StringOps {
        boolean hasDuplicates(String s);
    }
    
    public static void main(String[] args) {
        String[] arr = new String[]{"abcdefg", "abcdefa", "abba"};
        String format = "%s has %sduplicates\n";
        StringOps[] impls = new StringOps[] { new StringOpsImpl1(), 
            new StringOpsImpl2(), new StringOpsImpl3() };
     
        for (StringOps impl : impls) {
            for (String s : arr) 
                System.out.format(format, s, fmt(impl.hasDuplicates(s)));
        
            System.out.println();
        }
    }   

    public static String fmt(boolean b) {
        return b ? "" : "no ";
    }
    
    static class StringOpsImpl1 implements StringOps {
        // O(n log n)
        public boolean hasDuplicates(String s) {
            if (s == null) return false;
            int n = s.length();
            if (n < 2) return false;
      
            Set<Character> seen = new TreeSet<Character>();
            seen.add(s.charAt(0));
  
            for (int i = 1; i < n; i++) {
                char c = s.charAt(i);
                if (seen.contains(c))
                    return true;
                seen.add(c);
            }
            return false;
        }
    }

    static class StringOpsImpl2 implements StringOps {
        // O(nˆ2)
        public boolean hasDuplicates(String s) {
            if (s == null)
                return false;
 
            int n = s.length();
            if (n < 2)
                return false;
    
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                for (int j = i + 1; j < n; j++) {
                    if (c == s.charAt(j))
                        return true;
                }
            }
            return false;
        }
    }
    
    static class StringOpsImpl3 implements StringOps {
        // O(n), assumes String has char from std ASCII set (7 bits)
        public boolean hasDuplicates(String s) {
            int len = s.length();
            if (len > 127) return true;
        
            int[] found = new int[] { 0, 0, 0, 0 };
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (set(found, (int) c) == 1) return true;
            }
            return false;
        }
    
        /**
         * Sets the bit at index in the specified bit array arr and returns the previous 
         * value
         */
        private static int set(int[] arr, int index) {
            int bucket = index / 32;
            int mask = 1 << (index % 32);
            int result = (arr[bucket] & mask) == 0 ? 0 : 1;
            arr[bucket] |= mask;
            return result;
        }
    }
}
