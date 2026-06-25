class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int maxConsecutive = 0;
        int zeroCount = 0;

        for (int right = 0; right < nums.length; right++) {
            // Expand the window by incorporating the current element
            if (nums[right] == 0) {
                zeroCount++;
            }

            // Shrink the window from the left if we have exceeded k flips
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++; // Slide the left boundary forward
            }

            // Calculate the valid window length and update max
            maxConsecutive = Math.max(maxConsecutive, right - left + 1);
        }

        return maxConsecutive;
    }
}