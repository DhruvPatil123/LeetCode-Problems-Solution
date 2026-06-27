class Solution {
    public int removeDuplicates(int[] nums) {
        // Edge case: if the array is empty, return 0
        if (nums.length == 0) {
            return 0;
        }
        
        // 'k' is the pointer for unique elements. 
        // The first element is always unique, so we start at index 1.
        int k = 1; 
        
        // 'i' scans the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // If the current element is different from the previous one,
            // it means we found a new unique element.
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i]; // Move it to the unique region
                k++;               // Increment the unique count
            }
        }
        
        // k represents the total number of unique elements
        return k;
    }
}