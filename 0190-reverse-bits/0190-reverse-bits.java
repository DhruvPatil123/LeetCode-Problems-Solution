public class Solution {
    // treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        
        for (int i = 0; i < 32; i++) {
            // Shift result to the left to make room for the incoming bit
            result <<= 1;
            
            // Extract the last bit of n and add it to result
            result |= (n & 1);
            
            // Unsigned right shift n by 1 to process the next bit
            n >>>= 1;
        }
        
        return result;
    }
}