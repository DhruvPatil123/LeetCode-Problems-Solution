class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;
        
        for (int num : nums) {
            // Update 'twos' with bits that have appeared twice already
            twos |= (ones & num);
            
            // Update 'ones' with the current number's bits
            ones ^= num;
            
            // Determine bits that have appeared three times
            int threes = ones & twos;
            
            // Clear the bits that have reached a count of three from ones and twos
            ones &= ~threes;
            twos &= ~threes;
        }
        
        // 'ones' holds the bits of the element that appeared exactly once
        return ones;
    }
}