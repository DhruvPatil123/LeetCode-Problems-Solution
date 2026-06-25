class Solution {
    public boolean increasingTriplet(int[] nums) {
        // Start with the maximum possible values
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        
        for (int num : nums) {
            if (num <= first) {
                first = num; // num is the smallest so far
            } else if (num <= second) {
                second = num; // num is greater than first but smaller than/equal to second
            } else {
                // If it's greater than both first and second, we found our triplet!
                return true;
            }
        }
        
        return false;
    }
}