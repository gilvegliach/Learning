import java.util.Arrays;

public class SpacesEncoder {
	// "      ", 2
	//  k = j + 2 * (count - seen_r)
	public static char[] encode(char[] s, int len) {
        int count = 0;
        for (int i = 0; i < len; i++) {
			if (s[i] == ' ') count++;
		}
		int j = len - 1; // decoded  str index
		int k = len + 2 * count - 1; // encoded str index
		while (j >= 0) {
			char c = s[j];
			if (c == ' ') {
				s[k--] = '0';
				s[k--] = '2';
				s[k--] = '%';
				j--;
            } else {
				s[k] = s[j];
				j--;
				k--;
			}
		}
		return s;
	}
  

     // "Mr John Smith    ", 13
     // "Mr%20John%20Smith"
     public static void main(String[] args) {
        Test[] tests = new Test[]{ new Test("Mr John Smith    ", 13, "Mr%20John%20Smith") };
        for (Test t : tests) {
              t.test();
        }
     }

     static class Test {
         final char[] str;
         final int length;
         final char[] expected;
         Test(String s, int length, String expected) {
             this.length = length;
             str = s.toCharArray();
             this.expected = expected.toCharArray();
         }
         
        void test() {
            char[] enc = encode(str, length);
            String s = new String(str);
            String encStr = new String(enc);
            if (!Arrays.equals(expected, enc)) {
				throw new RuntimeException("Test failed for: " + s + 
				    " encoded: " + encStr);
			}
         }
     }
}