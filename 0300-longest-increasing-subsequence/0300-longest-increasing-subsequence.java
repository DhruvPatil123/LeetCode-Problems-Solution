import java.util.Arrays;

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] tails = new int[nums.length];
        int size = 0; // Tracks the current active length of the LIS

        for (int x : nums) {
            // Binary search to find the correct insertion/replacement index
            int i = Arrays.binarySearch(tails, 0, size, x);
            
            // If the element is not found, binarySearch returns: -(insertion point) - 1
            if (i < 0) {
                i = -(i + 1);
            }
            
            // Update the tail of the subsequence of length i + 1
            tails[i] = x;
            
            // If x was placed at the end, it means we found a longer subsequence
            if (i == size) {
                size++;
            }
        }

        return size;
    }
}