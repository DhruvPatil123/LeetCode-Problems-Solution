class Solution {
    public void moveZeroes(int[] nums) {
        int write = 0;
        
        for (int read = 0; read < nums.length; read++) {
            // If the current element is non-zero
            if (nums[read] != 0) {
                // Swap elements at read and write indices
                int temp = nums[read];
                nums[read] = nums[write];
                nums[write] = temp;
                
                // Advance the write pointer
                write++;
            }
        }
    }
}