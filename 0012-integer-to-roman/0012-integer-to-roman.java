class Solution {
    public String intToRoman(int num) {
        // Map integer values to their Roman numeral representations in descending order
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder roman = new StringBuilder();
        
        // Loop through the values array
        for (int i = 0; i < values.length; i++) {
            // While the current number is greater than or equal to the value
            while (num >= values[i]) {
                roman.append(symbols[i]); // Append the symbol
                num -= values[i];         // Subtract the value
            }
        }
        
        return roman.toString();
    }
}