class Solution {
    public String gcdOfStrings(String str1, String str2) {
        // Check if the strings can even share a common divisor
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        
        // The length of the GCD string is the GCD of the two lengths
        int gcdLength = gcd(str1.length(), str2.length());
        
        // Return the prefix of that length from either string
        return str1.substring(0, gcdLength);
    }
    
    // Helper method to calculate the GCD of two numbers using the Euclidean algorithm
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}