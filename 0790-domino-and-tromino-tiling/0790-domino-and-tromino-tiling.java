class Solution {
    public int numTilings(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 5;
        
        long MOD = 1_000_000_007;
        
        // Base cases mapped to rolling variables
        long f1 = 1; // n = 1
        long f2 = 2; // n = 2
        long f3 = 5; // n = 3
        
        // Compute sequentially up to n using our formula: f(n) = 2 * f(n-1) + f(n-3)
        for (int i = 4; i <= n; i++) {
            long fCurrent = (2 * f3 + f1) % MOD;
            
            // Shift the state window forward
            f1 = f2;
            f2 = f3;
            f3 = fCurrent;
        }
        
        return (int) f3;
    }
}