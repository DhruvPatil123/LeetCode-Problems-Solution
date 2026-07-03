class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        
        // Traverse the array from the back (least significant digit)
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // No more carry, return immediately
            }
            
            // If the digit is 9, it becomes 0 and carry moves to the next digit
            digits[i] = 0;
        }
        
        // If we reach here, it means all digits were 9 (e.g., 999 -> 1000)
        int[] newDigits = new int[n + 1];
        newDigits[0] = 1; // Remaining indices default to 0 in Java
        
        return newDigits;
    }
}