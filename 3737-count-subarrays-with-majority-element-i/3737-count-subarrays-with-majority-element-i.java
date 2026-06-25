class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int count = 0;
        int n = nums.length;
        
        // Traverse every possible starting position of a subarray
        for (int i = 0; i < n; i++) {
            int balance = 0;
            
            // Extend the subarray to the right
            for (int j = i; j < n; j++) {
                if (nums[j] == target) {
                    balance++;
                } else {
                    balance--;
                }
                
                // If balance > 0, target is strictly the majority element
                if (balance > 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
}