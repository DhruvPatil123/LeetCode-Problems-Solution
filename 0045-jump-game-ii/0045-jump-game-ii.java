class Solution {
    public int jump(int[] nums) {
        // If the array has only 1 element, we are already at the destination
        if (nums.length <= 1) {
            return 0;
        }
        
        int jumps = 0;
        int current_end = 0;
        int farthest = 0;
        
        // We loop up to nums.length - 1 because once we reach or pass 
        // the last element, we don't need to jump again.
        for (int i = 0; i < nums.length - 1; i++) {
            // Update the furthest index we can reach from the current spot
            farthest = Math.max(farthest, i + nums[i]);
            
            // If we have reached the end of the current jump's range
            if (i == current_end) {
                jumps++;                 // We must make a jump
                current_end = farthest;   // Update the boundary for the next jump level
                
                // Optimization: If the next boundary already reaches the end, we can stop
                if (current_end >= nums.length - 1) {
                    break;
                }
            }
        }
        
        return jumps;
    }
}