import java.util.Arrays;

class Solution {
    public int uniquePaths(int m, int n) {
        // Create a 1D DP array representing a single row of size n
        int[] dp = new int[n];
        
        // Base case: There is exactly 1 way to reach any cell in the first row
        Arrays.fill(dp, 1);
        
        // Iteratively compute paths row by row
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j] (from previous row) + dp[j-1] (from current row's left side)
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        
        // The last element contains the total paths to the bottom-right corner
        return dp[n - 1];
    }
}