public class CountWays {
	private static long count;
	
	public static long count(int[][] matrix) {
		count = 0L;
		count(matrix, 0, 0);
		return count;
	}

	private static void count(int[][] matrix, int i, int j) {
		if (i == matrix.length - 1 && j == matrix[0].length - 1) {
			++count;
			return;
		}
		
		if (j + 1 < matrix[0].length && matrix[i][j + 1] == 0)
			count(matrix, i, j + 1);

		if (i + 1 < matrix.length && matrix[i + 1][j] == 0)
			count(matrix, i + 1, j);
	}
}
