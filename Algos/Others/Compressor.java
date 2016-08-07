public class Compressor {
	public static String compress(String s) {
		if (s == null || s.length() < 3)
			return s;

        int len = s.length();
		int index = 0;
        char[] cbuff = new char[len];
		
		for (int i = 0; i < len; ) {
			// We write at least 2 chars in cbuff per iter.
			if (index + 2 >= len) return s;
			char c = s.charAt(i);
			cbuff[index++] = c;
			// Calculates count
			int j = i + 1;
			while(j < len && s.charAt(j) == c) j++;
			// Post-condition: j is 1 pos past the last char
			// equal to c. number = j - i
			int number = j - i;
			int offset = writeNumber(cbuff, index, number);
			// Buffer overflow, original string is shorter
            if (offset < 0) return s;
            index += offset;
            i = j;
		}
		return new String(cbuff);
	}

	// Assumes: number > 0
    private static int writeNumber(char[] arr, int from, 
		int number) {
		int digits = 0;
		int quotient = number;
		int power = 1;
        while (quotient > 0) {
			quotient /= 10;
			power *= 10;
			digits++;
		}
		power /= 10;
		if (digits > arr.length - from) return -1;
        while (power > 0) {
            quotient = number / power;
            number %= power;	
	        power /= 10;
	        char digit = Character.forDigit(quotient, 10);
	        arr[from++] = digit;
        }
        return digits;
    }

    public static void main(String[] args) {
        String[] tests = new String[]{ null, "", "a", "ab",  "aab", "aaab", "aaabbbb", 
               "aaaaaaaaaaaabbb", "aaaaaaaaaaaaAAAAAAAAAA" };
        for (String test : tests) {
            System.out.println(test + " -> " + compress(test));
        }
    }
}