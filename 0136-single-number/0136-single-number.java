class Solution {
    public int singleNumber(int[] nums) {
        int uniqueNumber = 0;
        
        // XOR all elements in the array
        for (int num : nums) {
            uniqueNumber ^= num;
        }
        
        return uniqueNumber;
    }
}