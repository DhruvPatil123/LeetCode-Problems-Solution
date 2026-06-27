class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        
        int leftMax = 0;
        int rightMax = 0;
        
        int totalWater = 0;

        while (left < right) {
            // Processing depends on which side has the smaller limiting boundary
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left]; // Update left maximum boundary
                } else {
                    totalWater += leftMax - height[left]; // Trap water
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right]; // Update right maximum boundary
                } else {
                    totalWater += rightMax - height[right]; // Trap water
                }
                right--;
            }
        }

        return totalWater;
    }
}