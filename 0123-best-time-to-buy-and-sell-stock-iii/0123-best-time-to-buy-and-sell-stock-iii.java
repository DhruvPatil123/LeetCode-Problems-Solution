class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // Initialize buy states to negative infinity since we want to find the maximums
        int buy1 = Integer.MIN_VALUE;
        int buy2 = Integer.MIN_VALUE;
        
        int sell1 = 0;
        int sell2 = 0;
        
        for (int price : prices) {
            // 1. Maximize money left after first buy (minimize buying price)
            buy1 = Math.max(buy1, -price);
            
            // 2. Maximize profit after first sell
            sell1 = Math.max(sell1, buy1 + price);
            
            // 3. Maximize money left after second buy (using profit from sell1)
            buy2 = Math.max(buy2, sell1 - price);
            
            // 4. Maximize profit after second sell
            sell2 = Math.max(sell2, buy2 + price);
        }
        
        return sell2;
    }
}