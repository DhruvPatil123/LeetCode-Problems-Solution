class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // If the starting point or ending point has an obstacle, no paths exist
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        
        // 1D DP array representing the current row
        int[] dp = new int[n];
        dp[0] = 1; // Base case: 1 way to be at the starting point
        
        // Traverse the grid row by row
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0; // Obstacle blocks all paths to this cell
                } else if (j > 0) {
                    // Current paths = paths from top (dp[j]) + paths from left (dp[j-1])
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        // The last element contains the unique paths to the bottom-right corner
        return dp[n - 1];
    }
}