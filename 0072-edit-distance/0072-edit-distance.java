class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // dp[i][j] represents operations to convert word1[0...i-1] to word2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Base cases: filling the first row and first column
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Deleting all characters to match empty word2
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Inserting all characters to match from empty word1
        }
        
        // Fill the rest of the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Match: no new operation needed
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Mismatch: find minimum of Insert, Delete, or Replace, and add 1
                    int insertOp = dp[i][j - 1];
                    int deleteOp = dp[i - 1][j];
                    int replaceOp = dp[i - 1][j - 1];
                    
                    dp[i][j] = 1 + Math.min(replaceOp, Math.min(insertOp, deleteOp));
                }
            }
        }
        
        // Bottom-right cell holds the answer for full strings
        return dp[m][n];
    }
}