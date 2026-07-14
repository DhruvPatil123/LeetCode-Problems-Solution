class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        // dp[g1][g2] stores the number of ways to have seq1 with gcd g1 and seq2 with gcd g2
        // 0 represents an empty sequence
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1; // Base case: both sequences empty

        for (int x : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    long currentWays = dp[g1][g2];

                    // Choice 1: Skip x (keep current states)
                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + currentWays) % MOD);

                    // Choice 2: Put x into seq1
                    int nextG1 = gcd(g1, x);
                    nextDp[nextG1][g2] = (int) ((nextDp[nextG1][g2] + currentWays) % MOD);

                    // Choice 3: Put x into seq2
                    int nextG2 = gcd(g2, x);
                    nextDp[g1][nextG2] = (int) ((nextDp[g1][nextG2] + currentWays) % MOD);
                }
            }
            dp = nextDp;
        }

        long totalPairs = 0;
        // Sum up all states where g1 == g2 and both are non-empty (> 0)
        for (int g = 1; g <= maxVal; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    private int gcd(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}