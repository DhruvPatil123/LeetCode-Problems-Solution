class Solution {
    public int trailingZeroes(int n) {
        int count = 0;
        
        // Continuously divide n by powers of 5
        while (n >= 5) {
            count += n / 5;
            n /= 5; // Reduces n to count higher powers of 5 (25, 125, etc.) next
        }
        
        return count;
    }
}