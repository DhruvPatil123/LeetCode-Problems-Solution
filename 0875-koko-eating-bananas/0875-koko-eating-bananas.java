class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        
        // Find the maximum pile size to set the upper bound
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        
        int result = high;
        
        // Binary search on the eating speed k
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canEatAll(piles, h, mid)) {
                result = mid;       // Current speed works, record it
                high = mid - 1;     // Try to find a slower valid speed
            } else {
                low = mid + 1;      // Too slow, must speed up
            }
        }
        
        return result;
    }
    
    private boolean canEatAll(int[] piles, int h, int speed) {
        long totalHours = 0; // Use long to prevent integer overflow
        
        for (int pile : piles) {
            // Equivalent to Math.ceil((double) pile / speed)
            totalHours += (pile + speed - 1) / speed;
        }
        
        return totalHours <= h;
    }
}