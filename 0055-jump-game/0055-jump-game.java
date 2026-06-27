class Solution {
    public boolean canJump(int[] nums) {
        int farthestReachable = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            // If the current index is unreachable, we can't move forward
            if (i > farthestReachable) {
                return false;
            }
            
            // Update the furthest index we can reach from the current spot
            farthestReachable = Math.max(farthestReachable, i + nums[i]);
            
            // Optimization: If we can already reach or pass the last index, return true
            if (farthestReachable >= n - 1) {
                return true;
            }
        }
        
        return false;
    }
}