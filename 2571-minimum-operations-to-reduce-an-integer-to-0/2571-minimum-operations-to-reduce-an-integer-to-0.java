class Solution {
    public int minOperations(int n) {
        int operations = 0;
        
        while (n > 0) {
            // If the last bit is 0, no operation is needed; just shift right
            if ((n & 1) == 0) {
                n >>= 1;
            } else {
                // Check the last two bits
                // If it ends in '11', adding 1 clears a block of 1s due to carries
                if ((n & 3) == 3) {
                    n += 1;
                } else {
                    // If it ends in '01', subtracting 1 isolates the 0s
                    n -= 1;
                }
                operations++;
            }
        }
        
        return operations;
    }
}