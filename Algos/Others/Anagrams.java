
import java.util.Arrays;
import java.util.Comparator;

public class Anagrams { 
	public static class AnagramComparator implements Comparator<String>{
		@Override
		public int compare(String lhs, String rhs) {
			if (lhs == null) lhs = "";
			if (rhs == null) rhs = "";
			char[] buff = lhs.toCharArray();
			Arrays.sort(buff);
			lhs = new String(buff);
			buff = rhs.toCharArray();
			Arrays.sort(buff);
			rhs = new String(buff);
			return lhs.compareTo(rhs);
		}
	}

	public static void anagramSort(String[] arr) {
		Arrays.sort(arr, new AnagramComparator());
	}

	public static void main(String[] args) {
		String[] arr = new String[]{ "caab", "abba", "baab", "xda", "acab"};
		System.out.println(Arrays.toString(arr));
		anagramSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}