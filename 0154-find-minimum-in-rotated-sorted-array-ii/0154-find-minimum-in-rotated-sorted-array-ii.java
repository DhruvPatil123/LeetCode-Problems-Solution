class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                // Minimum must be in the right unsorted part
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // Right part is sorted, minimum is at mid or to the left
                right = mid;
            } else {
                // nums[mid] == nums[right]
                // Cannot determine which side holds the minimum, shrink the boundary safely
                right--;
            }
        }
        
        return nums[left];
    }
}