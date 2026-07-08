class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        long MOD = 1_000_000_007L;

        // 1. Gather all non-zero digits
        java.util.List<Integer> nzDigits = new java.util.ArrayList<>();
        for (int i = 0; i < m; i++) {
            char ch = s.charAt(i);
            if (ch != '0') {
                nzDigits.add(ch - '0');
            }
        }

        int n = nzDigits.size();

        // Precompute powers of 10 modulo 10^9 + 7
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Precompute prefix concatenated numbers
        long[] P = new long[n + 1];
        for (int i = 0; i < n; i++) {
            P[i + 1] = (P[i] * 10 + nzDigits.get(i)) % MOD;
        }

        // Precompute prefix sums of digits
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nzDigits.get(i);
        }

        // 2. Map original indices to compressed indices
        int[] nextNz = new int[m];
        int curr = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                nextNz[i] = curr;
                curr++;
            } else {
                nextNz[i] = curr;
            }
        }

        int[] prevNz = new int[m];
        curr = -1;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                curr++;
            }
            prevNz[i] = curr;
        }

        // 3. Process each query
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int L = queries[q][0];
            int R = queries[q][1];

            // Find the boundary indices in the compressed non-zero list
            int j = nextNz[L];
            int k = prevNz[R];

            // If no non-zero digits exist in the range [L, R]
            if (j > k) {
                ans[q] = 0;
                continue;
            }

            // Extract x using prefix rolling hash logic
            long x = (P[k + 1] - (P[j] * pow10[k - j + 1]) % MOD + MOD) % MOD;

            // Extract sum using prefix sum
            long sum = prefixSum[k + 1] - prefixSum[j];

            ans[q] = (int) ((x * (sum % MOD)) % MOD);
        }

        return ans;
    }
}