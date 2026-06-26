class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        // Base state for Day 0
        int free = 0;                 // Max profit if we don't own stock
        int hold = -prices[0];        // Max profit if we bought stock on day 0
        
        // Iterate through the remaining days
        for (int i = 1; i < prices.length; i++) {
            int prevFree = free;
            
            // Update free state: either continue resting or sell our held stock
            free = Math.max(free, hold + prices[i] - fee);
            
            // Update hold state: either continue holding or buy stock today
            hold = Math.max(hold, prevFree - prices[i]);
        }
        
        return free;
    }
}