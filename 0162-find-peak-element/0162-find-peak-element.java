class Solution {
    public int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        
        // Loop terminates when low == high, narrowing down to a single peak element
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (nums[mid] < nums[mid + 1]) {
                // We are ascending to the right; a peak lies to the right
                low = mid + 1;
            } else {
                // We are descending to the right; mid could be a peak, or a peak lies to the left
                high = mid;
            }
        }
        
        // 'low' and 'high' meet at the peak index
        return low;
    }
}