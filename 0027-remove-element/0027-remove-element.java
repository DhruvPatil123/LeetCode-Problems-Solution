class Solution {
    public int removeElement(int[] nums, int val) {
        // Pointer to keep track of the position for the next valid element
        int k = 0; 
        
        // Iterate through the array with a scanner pointer
        for (int i = 0; i < nums.length; i++) {
            // If the current element is NOT equal to val, keep it
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++; // Move the valid element pointer forward
            }
        }
        
        // k represents the count of elements not equal to val
        return k;
    }
}