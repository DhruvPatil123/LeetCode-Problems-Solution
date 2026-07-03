class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        
        // Loop runs until low and high converge on the minimum element
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (nums[mid] > nums[high]) {
                // Minimum must be in the right half
                low = mid + 1;
            } else {
                // Minimum could be mid itself or to the left
                high = mid;
            }
        }
        
        // Both low and high point to the minimum element
        return nums[low];
    }
}