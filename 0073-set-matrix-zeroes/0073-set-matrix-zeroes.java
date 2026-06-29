class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstColZero = false;

        // Step 1: Gather zero markers using the first row and column
        for (int i = 0; i < m; i++) {
            // Check if the first column has any zeros
            if (matrix[i][0] == 0) {
                firstColZero = true;
            }
            
            // Record zeros in the first row/col markers
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 2: Update cells from bottom-right up to avoid overriding flags early
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            // Update the first column cell if needed
            if (firstColZero) {
                matrix[i][0] = 0;
            }
        }
    }
}