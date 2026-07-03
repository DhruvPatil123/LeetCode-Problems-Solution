class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        // 1D DP array initialized with an extra padding element to handle boundaries smoothly
        int[] dp = new int[n + 1];
        int maxSide = 0;
        int prev = 0; // Tracks the top-left diagonal element (dp[i-1][j-1])
        
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // Store the current value before it gets overwritten
                
                if (matrix[i][j - 1] == '1') {
                    // dp[j] is the cell above, dp[j-1] is the cell to the left, prev is diagonal top-left
                    dp[j] = Math.min(Math.min(dp[j], dp[j - 1]), prev) + 1;
                    maxSide = Math.max(maxSide, dp[j]);
                } else {
                    dp[j] = 0; // Reset if the grid character is '0'
                }
                
                prev = temp; // The old dp[j] becomes the 'prev' (diagonal top-left) for the next column
            }
        }
        
        // Return the area of the largest square found
        return maxSide * maxSide;
    }
}