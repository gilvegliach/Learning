import java.util.*;

public class MissingNumber {
	public static void main(String[] args) {
		int[] arr = new int[] {6, 1, 5, 4, 2, 0};
		int missing = findMissing2(arr);
		System.out.println(missing);
		assert missing == 3;

		arr = new int[] {9, 2, 3, 4, 5, 6, 7, 8, 1};
		missing = findMissing2(arr);
		System.out.println(missing);
		assert missing == 0;

		arr = new int[] {0, 1, 2, 3, 4, 5, 6, 8, 9, 10};
		missing = findMissing2(arr);
		System.out.println(missing);
		assert missing == 7;
	}

    // O(n) time and space
	static int findMissing2(int[] arr) {
		int n = arr.length;
		int k = numberOfDigit(n);
		int missing = 0;
		List<Integer> next = intArrayToList(arr);
		for (int i = k - 1; i >= 0; i--) {
			List<Integer> zeros = new ArrayList<>();
			List<Integer> ones = new ArrayList<>();
			for (Integer val : next) {
				int bit = getBit(val, i);
				if (bit == 0) {
					zeros.add(val);
				} else {
					ones.add(val);
				}
			}
			missing *= 2;
			if (i > 0) {
				if ((zeros.size() % 2) == 1) {
					next = zeros;
				} else {
					next = ones;
					missing++;
				}
			} else {
				if (zeros.size() == 1) {
					missing++;
				}
			}
		}
		return missing;
	}
	
	static List<Integer> intArrayToList(int[] arr) {
		ArrayList<Integer> list = new ArrayList<>(arr.length);
		for (int n : arr) {
			list.add(n);
		}
		return list;
	}
	
	// n > 0
	static int numberOfDigit(int n) {
		return (int) Math.floor(Math.log(n) / Math.log(2)) + 1;
	}

	static int findMissing(int[] arr) {
		int n = arr.length + 1;
		int missing = 0;
		for (int i = 0; i < 32; i++) {
			int calPar = calculateParityOnes(arr, i);
			int expPar = expectedParityOnes(n, i);
			int digit = calPar ^ expPar;
			missing |= digit << i;
		}
		return missing;
	}
	
	static int expectedParityOnes(int n, int pos) {
		if (pos == 0) {
			return (n >> 1) & 1;
		}
		if ((n & 1) == 0) {
			return 0;
		}
		int k = (n - 1) >> 1;
		return getBit(k, pos - 1);
	}
	
	static int calculateParityOnes(int[] arr, int pos) {
		int parity = 0;
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			parity ^= getBit(arr[i], pos);
		}
		return parity;
	}
	
	static int getBit(int number, int pos) {
		return (number >> pos) & 1;
	}
}
