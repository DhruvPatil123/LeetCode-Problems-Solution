class Solution {
    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        int leftSum = 0;
        
        // Step 1: Calculate the total sum of the array
        for (int num : nums) {
            totalSum += num;
        }
        
        // Step 2: Traverse the array to find the pivot index
        for (int i = 0; i < nums.length; i++) {
            // Check if left sum equals right sum
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            // Update left sum for the next index
            leftSum += nums[i];
        }
        
        return -1;
    }
}