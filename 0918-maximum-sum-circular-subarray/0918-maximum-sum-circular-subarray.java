class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        
        // Variables for tracking standard Kadane (Maximum Subarray)
        int currentMax = 0;
        int maxSum = nums[0];
        
        // Variables for tracking modified Kadane (Minimum Subarray)
        int currentMin = 0;
        int minSum = nums[0];
        
        for (int num : nums) {
            totalSum += num;
            
            // Standard Kadane to find the maximum subarray
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);
            
            // Modified Kadane to find the minimum subarray
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
        }
        
        // Edge Case: If all elements are negative, maxSum will be negative.
        // We cannot return totalSum - minSum because that would equal 0 (an empty subarray).
        if (maxSum < 0) {
            return maxSum;
        }
        
        // Otherwise, return the maximum of the non-wrap-around and wrap-around strategies
        return Math.max(maxSum, totalSum - minSum);
    }
}