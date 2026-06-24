import java.util.Arrays;

class Solution {
    private static final int MOD = 1_000_000_007;
    private int m;

    // Standard matrix multiplication modulo 10^9 + 7
    private long[][] multiply(long[][] A, long[][] B) {
        long[][] C = new long[m][m];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < m; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < m; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    // Binary matrix exponentiation
    private long[][] power(long[][] matrix, long p) {
        long[][] res = new long[m][m];
        for (int i = 0; i < m; i++) {
            res[i][i] = 1; // Identity matrix
        }
        
        long[][] base = matrix;
        while (p > 0) {
            if ((p & 1) == 1) {
                res = multiply(res, base);
            }
            base = multiply(base, base);
            p >>= 1;
        }
        return res;
    }

    public int zigZagArrays(int n, int l, int r) {
        m = r - l + 1;
        
        long[][] U = new long[m][m];
        long[][] L = new long[m][m];
        
        // Populate the base transition states
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i < j) U[i][j] = 1;
                if (i > j) L[i][j] = 1;
            }
        }
        
        long steps = n - 1;
        long pairs = steps / 2;
        
        // UL matrix represents an Up transition followed immediately by a Down transition
        long[][] UL = multiply(U, L);
        long[][] P = power(UL, pairs);
        
        // If the remaining number of single steps is odd, apply one last Up transition
        if (steps % 2 == 1) {
            P = multiply(P, U);
        }
        
        // Sum up all possible transitions
        long totalSum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                totalSum = (totalSum + P[i][j]) % MOD;
            }
        }
        
        // Multiply by 2 because of complete symmetry between starting with an Up vs a Down step
        return (int) ((totalSum * 2) % MOD);
    }
}