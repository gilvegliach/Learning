public class NullifyMatrix {
	public static void nullify(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		boolean hasFirstRowZeros = false;
		boolean hasFirstColumnZeros = false;
		for (int j = 0; j < n; j++) {
			if (matrix[0][j] == 0) {
				hasFirstRowZeros = true;
				break;
			}
		}
		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0) {
				hasFirstColumnZeros = true;
				break;
			}
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		for (int j = 1; j < n; j++) {
			if (matrix[0][j] == 0) {
				for (int i = 1; i < m; i++) {
					matrix[i][j] = 0;
				}
			}
		}
		for (int i = 1; i < m; i++) {
			if (matrix[i][0] == 0) {
				for (int j = 1; j < n; j++) {
					matrix[i][j] = 0;
				}
			}
		}
		if (hasFirstRowZeros) {
			for (int j = 0; j < n; j++) {
				matrix[0][j] = 0;
			}
		}
		if (hasFirstColumnZeros) {
			for (int i = 0; i < m; i++) {
				matrix[i][0] = 0;
			}
		}
	}
	
	public static void printMatrix(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j]);
				if (j < n - 1) {
					System.out.print(", ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] m = new int[][] {
			{1, 2, 3, 4},
			{5, 6, 0, 8},
			{0, 1, 2, 3},
			{4, 5, 6, 7}
		};
		printMatrix(m);
		nullify(m);
		System.out.println();
		printMatrix(m);
	}
}