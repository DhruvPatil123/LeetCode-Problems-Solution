class Solution {
    public int longestSubarray(int[] nums) {
        int left = 0;
        int maxLen = 0;
        int zeroCount = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Count the zero entering the window
            if (nums[right] == 0) {
                zeroCount++;
            }
            
            // Shrink the window if we have more than one zero
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            
            // The window size minus 1 element (the mandatory deletion) is right - left
            maxLen = Math.max(maxLen, right - left);
        }
        
        return maxLen;
    }
}