class Solution {
    public int tribonacci(int n) {
        // Base cases
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        
        // Pointers to track the last three values
        int t0 = 0;
        int t1 = 1;
        int t2 = 1;
        
        // Compute sequentially up to n
        for (int i = 3; i <= n; i++) {
            int nextTrib = t0 + t1 + t2;
            
            // Shift the window forward
            t0 = t1;
            t1 = t2;
            t2 = nextTrib;
        }
        
        return t2;
    }
}