public class MatrixRotation {
	public static void rotate(int[][] m){
		if (m == null) throw new NullPointerException();
		
		// Flip matrix on the vertical axis
		for (int i = 0; i < m.length; i++) {
			int len = m[i].length;
			for (int j = 0; j < len / 2; j++) {
				int t = m[i][j];
				m[i][j] = m[i][len - 1 - j];
				m[i][len - 1 - j] = t;
			}
		}
		
		// Flip matrix on the top-right--bottom-left axis
		for (int i = 0; i < m.length; i++) {
			int len = m[i].length;
			for (int j = 0; j < len - 1 - i; j++) {
				int t = m[i][j];
				m[i][j] = m[len - 1 - j][len - 1 - i];
				m[len - 1 - j][len - 1 - i] = t;
			}
		}
	}
	
	public static void rotate2(int[][] m) {
		if (m == null) throw new NullPointerException();
		
		int n = m.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				// Rotation:
				// 1. t      <- left
				// 2. left   <- bottom
				// 3. bottom <- right
				// 4. right  <- top
				// 5. top    <- t
				int t = m[n - j - 1][i]; 
				m[n - j - 1][i] = m[n - i - 1][n - j - 1]; 
				m[n - i - 1][n - j - 1] = m[j][n - i - 1]; 
				m[j][n - i - 1] = m[i][j]; 
				m[i][j] = t;
			}
		}
	}
	
	public static void print(int[][] m) {
		if (m == null) throw new NullPointerException();
		for (int i = 0; i < m.length; i++) {
	        for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int[][] m = new int[][]{
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 0, 1, 2},
			{3, 4, 5, 6},
		};
		print(m);
		rotate2(m);
		System.out.println();
		print(m);
	}
}