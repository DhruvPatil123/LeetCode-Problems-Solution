class Solution {
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2; // Prevents potential integer overflow
            
            if (nums[mid] == target) {
                return mid; // Target found
            } else if (nums[mid] < target) {
                low = mid + 1; // Look in the right half
            } else {
                high = mid - 1; // Look in the left half
            }
        }
        
        // If not found, low represents the correct insertion index
        return low;
    }
}