class Solution {
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        
        // Loop until all bits of a, b, and c have been processed
        while (a > 0 || b > 0 || c > 0) {
            // Extract the rightmost (least significant) bit of each number
            int bitA = a & 1;
            int bitB = b & 1;
            int bitC = c & 1;
            
            if (bitC == 1) {
                // If c's bit is 1, we need at least one of bitA or bitB to be 1
                if (bitA == 0 && bitB == 0) {
                    flips += 1;
                }
            } else {
                // If c's bit is 0, both bitA and bitB must be 0
                // Each '1' bit present will require 1 flip to become 0
                flips += (bitA + bitB);
            }
            
            // Shift right to inspect the next bit position
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        
        return flips;
    }
}