class Solution {
    public int removeDuplicates(int[] nums) {
        // If the array has 2 or fewer elements, it already satisfies the condition
        if (nums.length <= 2) {
            return nums.length;
        }
        
        // 'k' tracks the index where the next valid element should be written.
        // The first two elements are always allowed.
        int k = 2; 
        
        // Start scanning from the third element (index 2)
        for (int i = 2; i < nums.length; i++) {
            // Compare the current element with the element two positions behind 'k'
            if (nums[i] != nums[k - 2]) {
                nums[k] = nums[i]; // Keep the element
                k++;               // Move the replacement pointer forward
            }
        }
        
        // k represents the total number of valid elements
        return k;
    }
}