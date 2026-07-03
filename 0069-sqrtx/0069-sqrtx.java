class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        
        long low = 1;
        long high = x;
        long ans = 0;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            long square = mid * mid;
            
            if (square == x) {
                return (int) mid; // Perfect square found
            } else if (square < x) {
                ans = mid;        // Record mid as a potential rounded-down candidate
                low = mid + 1;    // Try to find a closer, larger value
            } else {
                high = mid - 1;   // The square is too large, search left
            }
        }
        
        return (int) ans;
    }
}