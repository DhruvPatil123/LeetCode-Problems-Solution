class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        // dp[i][j] stores the LCS length of text1[0...i-1] and text2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Build the DP matrix bottom-up
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // Match found: take diagonal value and add 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // No match: take the maximum of skipping a character from text1 or text2
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // The bottom-right cell contains the length of the full LCS
        return dp[m][n];
    }
}