import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        // Create the DP array to store results for all sub-amounts up to 'amount'
        int[] dp = new int[amount + 1];
        
        // Fill the array with a pre-defined max value representing infinity
        Arrays.fill(dp, amount + 1);
        
        // Base case: 0 coins are needed to make an amount of 0
        dp[0] = 0;
        
        // Build up solutions for every sub-amount from 1 to amount
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // If the coin value is less than or equal to the target sub-amount
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // If dp[amount] remains greater than amount, it means the total could not be reached
        return dp[amount] > amount ? -1 : dp[amount];
    }
}