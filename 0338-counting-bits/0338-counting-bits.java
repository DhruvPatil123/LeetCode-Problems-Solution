class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        
        // Base case: ans[0] is automatically initialized to 0
        
        // Compute the bit counts iteratively using previously solved subproblems
        for (int i = 1; i <= n; i++) {
            // i >> 1 is identical to i / 2
            // i & 1 is identical to i % 2
            ans[i] = ans[i >> 1] + (i & 1);
        }
        
        return ans;
    }
}