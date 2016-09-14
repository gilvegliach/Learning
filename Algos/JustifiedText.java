// Given an array of words and a length L, format the text such that each line has
// exactly L characters and is fully (left and right) justified.
// You should pack your words in a greedy approach; that is, pack as many words as
// you can in each line.
//
// Pad extra spaces ‘ ‘ when necessary so that each line has exactly L characters.
// Extra spaces between words should be distributed as evenly as possible.
// If the number of spaces on a line do not divide evenly between words, the empty 
// slots on the left will be assigned more spaces than the slots on the right.
// For the last line of text, it should be left justified and no extra space is 
// inserted between words.
//
// Your program should return a list of strings, where each string represents a 
// single line.
//
// Example:
//
// words: ["This", "is", "an", "example", "of", "text", "justification."]
//
// L: 16.
//
// Return the formatted lines as:
//
// [
//    "This    is    an",
//    "example  of text",
//    "justification.  "
// ]
//  Note: Each word is guaranteed not to exceed L in length.
public class JustifiedText {
	public ArrayList<String> fullJustify(ArrayList<String> a, int b) {
	    ArrayList<String> result = new ArrayList<String>();
	    int n = a.size();
	    int total = 0;
	    ArrayList<String> buff = new ArrayList<String>();
	    for (int i = 0; i < n; i++) {
	        String s = a.get(i);
	        int k = s.length();
	        if (total + k <= b) {
	            buff.add(s);
	            total += k;
	        } else {
	            result.add(formatLine(buff, b, false));
	            buff.clear();
	            buff.add(s);
	            total = k;
	        }
	        total++;
	    }
	    if (!buff.isEmpty()) result.add(formatLine(buff, b, true));
	    return result;
	}
	
	private int total(ArrayList<String> arr) {
	    int total = 0;
	    for (String s : arr) { total += s.length(); }
	    return total;
	}
	
	private String formatLine(ArrayList<String> arr, int l, boolean leftJustify) {
	    int n = arr.size();
	    if (n == 1 || leftJustify) {
	        StringBuilder result = new StringBuilder(l);
	        for (int i = 0; i < n; i++) {
	            String s = arr.get(i);
	            result.append(s);
	            if (i < n - 1) result.append(' ');
	        }
	        int left = l - result.length();
	        return result.append(spaces(left)).toString();
	    }
	    int total = total(arr);
	    int delta = l - total;
	    int q = delta / (n - 1);
	    int r = delta - q * (n - 1);
	    
	    StringBuilder sb = new StringBuilder(l);
	    sb.append(arr.get(0));
	    for (int i = 1; i < n; i++) {
	        int k = (i - 1) < r ? q + 1 : q;
	        sb.append(spaces(k));
	        sb.append(arr.get(i));
	    }
	    return sb.toString();
	}
	
	private StringBuilder spaces(int n) {
	    StringBuilder sb = new StringBuilder(n);
	    for (int i = 0; i < n; i++) {
	        sb.append(' ');
	    }
	    return sb;
	}
}