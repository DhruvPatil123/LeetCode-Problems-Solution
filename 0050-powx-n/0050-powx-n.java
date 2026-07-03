class Solution {
    public double myPow(double x, int n) {
        // Cast n to long to prevent overflow when handling Integer.MIN_VALUE
        long N = n;
        
        // Handle negative exponents: x^(-N) = (1/x)^N
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        double result = 1.0;
        double currentProduct = x;
        
        // Iterative Binary Exponentiation
        while (N > 0) {
            // If the current bit of the exponent is odd, multiply the result
            if (N % 2 == 1) {
                result *= currentProduct;
            }
            // Square the base and halve the exponent exponent
            currentProduct *= currentProduct;
            N /= 2;
        }
        
        return result;
    }
}