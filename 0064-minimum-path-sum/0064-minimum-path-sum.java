class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // DP array to store the minimum path sums for the current row
        int[] dp = new int[n];
        
        // Initialize the top-left cell boundary
        dp[0] = grid[0][0];
        
        // Initialize the rest of the first row (can only be reached from the left)
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        
        // Build the DP table row by row
        for (int i = 1; i < m; i++) {
            // First element of a row can only be reached from the cell directly above
            dp[0] += grid[i][0];
            
            for (int j = 1; j < n; j++) {
                // dp[j] is the cell above, dp[j-1] is the cell to the left
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }
        
        // The last element contains the minimum path sum to the bottom-right corner
        return dp[n - 1];
    }
}