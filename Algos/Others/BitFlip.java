public class BitFlip {
	static int countDifferentBits(int a, int b) {
		int count = 0;
		// c &= c - 1: clear least significant 1
		for (int c = a ^ b; c != 0; c &= c - 1) {
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		// 150_10 = 10010110_2
		// 173_10 = 10101101_2
		//            ... .. 5
		int result = countDifferentBits(150, 173);
		assert result == 5;
	
		result = countDifferentBits(0, 0);
		assert result == 0;
	}
}