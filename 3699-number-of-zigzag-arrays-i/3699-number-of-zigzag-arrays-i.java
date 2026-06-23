class Solution {
    public int zigZagArrays(int n, int l, int r) {
        long MOD = 1_000_000_007L;
        int m = r - l + 1;
        
        // If the sequence length is 1 or 2, handles it based on problem constraints
        // Constraint says 3 <= n <= 2000, so we always compute up to n.
        
        // dp[0][x]: next move must be DOWN (< x)
        // dp[1][x]: next move must be UP (> x)
        long[] dp0 = new long[m];
        long[] dp1 = new long[m];
        
        // Base case: transitions for length 2
        for (int x = 0; x < m; x++) {
            dp0[x] = x;
            dp1[x] = m - 1 - x;
        }
        
        // Iteratively transition from length 3 up to n
        for (int i = 3; i <= n; i++) {
            long[] nextDp0 = new long[m];
            long[] nextDp1 = new long[m];
            
            // Optimize transitions using running prefix/suffix sums
            long prefixSumDp1 = 0;
            for (int y = 0; y < m; y++) {
                nextDp0[y] = prefixSumDp1;
                prefixSumDp1 = (prefixSumDp1 + dp1[y]) % MOD;
            }
            
            long suffixSumDp0 = 0;
            for (int y = m - 1; y >= 0; y--) {
                nextDp1[y] = suffixSumDp0;
                suffixSumDp0 = (suffixSumDp0 + dp0[y]) % MOD;
            }
            
            dp0 = nextDp0;
            dp1 = nextDp1;
        }
        
        // The total number of valid sequences of length n is the sum of all ending states
        long totalSum = 0;
        for (int x = 0; x < m; x++) {
            totalSum = (totalSum + dp0[x] + dp1[x]) % MOD;
        }
        
        return (int) totalSum;
    }
}