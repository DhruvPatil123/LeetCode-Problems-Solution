class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // If s is shorter than t, it's impossible for t to be a subsequence
        if (m < n) {
            return 0;
        }
        
        // dp[j] stores the number of distinct subsequences of s matching t[0...j-1]
        int[] dp = new int[n + 1];
        
        // Base case: an empty t can be formed by an empty s in 1 way
        dp[0] = 1;
        
        // Iterate through string s
        for (int i = 1; i <= m; i++) {
            // Iterate backwards through string t to use values from the previous iteration
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = dp[j] + dp[j - 1];
                }
                // If they don't match, dp[j] remains dp[j] (which is the inherited dp[i-1][j])
            }
        }
        
        return dp[n];
    }
}