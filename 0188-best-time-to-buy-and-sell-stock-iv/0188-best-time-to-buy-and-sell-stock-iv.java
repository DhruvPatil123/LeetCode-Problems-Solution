import java.util.Arrays;

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) {
            return 0;
        }

        // Optimization: If k is larger than the maximum possible transactions (n / 2),
        // the problem becomes equivalent to having infinite transactions (Stock II).
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }

        // Arrays to track cash after buying or selling for each transaction 1 to k
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        // Initialize buy states to negative infinity
        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                // Maximize cash left after the i-th buy
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                
                // Maximize profit after the i-th sell
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }

        return sell[k];
    }
}