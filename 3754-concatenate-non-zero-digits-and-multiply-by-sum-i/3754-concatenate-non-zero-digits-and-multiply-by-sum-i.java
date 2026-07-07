class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;
        long multiplier = 1;

        while (n > 0) {
            int digit = n % 10;
            if (digit != 0) {
                // Add to the digit sum
                sum += digit;
                // Construct x keeping the correct place value sequence
                x += digit * multiplier;
                // Shift the multiplier to the next decimal position
                multiplier *= 10;
            }
            n /= 10;
        }

        return x * sum;
    }
}