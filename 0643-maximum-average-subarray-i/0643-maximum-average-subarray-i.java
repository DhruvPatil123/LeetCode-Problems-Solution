class Solution {
    public double findMaxAverage(int[] nums, int k) {
        // Step 1: Compute the sum of the first window of size k
        int currentWindowSum = 0;
        for (int i = 0; i < k; i++) {
            currentWindowSum += nums[i];
        }
        
        int maxWindowSum = currentWindowSum;
        
        // Step 2: Slide the window across the rest of the array
        for (int i = k; i < nums.length; i++) {
            // Add the incoming element and subtract the outgoing element
            currentWindowSum += nums[i] - nums[i - k];
            // Track the highest sum achieved
            maxWindowSum = Math.max(maxWindowSum, currentWindowSum);
        }
        
        // Step 3: Return the maximum average
        return (double) maxWindowSum / k;
    }
}