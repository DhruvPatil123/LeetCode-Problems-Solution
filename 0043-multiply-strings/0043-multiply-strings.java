class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        
        // Edge case: if either number is 0, the result is 0
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        
        // The result of multiplying lengths m and n can be at most m + n digits
        int[] pos = new int[m + n];
        
        // Multiply from right to left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                
                // Position where the current digit multiplication result drops
                int p1 = i + j;     // Carry position
                int p2 = i + j + 1; // Remainder position
                
                int sum = mul + pos[p2];
                
                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }
        
        // Build the final string result
        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            // Skip leading zero if it exists at index 0
            if (!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }
        
        return sb.toString();
    }
}