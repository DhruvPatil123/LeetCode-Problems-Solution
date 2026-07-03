import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        
        // DP array initialized to the size of the bottom row (which is exactly 'n')
        int[] dp = new int[n];
        
        // Initialize the DP array with the values of the bottom-most row
        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }
        
        // Walk backwards from the second-to-last row up to the top
        for (int i = n - 2; i >= 0; i--) {
            List<Integer> currentRow = triangle.get(i);
            
            for (int j = 0; j <= i; j++) {
                // The minimum path sum beneath index j is its value plus 
                // the minimum of its two direct bottom-neighbors
                dp[j] = currentRow.get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        
        // The top of the triangle now contains the final minimum path sum
        return dp[0];
    }
}