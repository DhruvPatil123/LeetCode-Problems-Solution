class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int twoBack = 1; // Base case: empty string has 1 way
        int oneBack = 1; // Base case: first valid character has 1 way

        for (int i = 1; i < n; i++) {
            int current = 0;
            int singleDigit = s.charAt(i) - '0';
            int doubleDigit = Integer.parseInt(s.substring(i - 1, i + 1));

            // Check if valid single digit translation (1-9)
            if (singleDigit >= 1 && singleDigit <= 9) {
                current += oneBack;
            }

            // Check if valid double digit translation (10-26)
            if (doubleDigit >= 10 && doubleDigit <= 26) {
                current += twoBack;
            }

            // If it can't be decoded either way (e.g., "30" or "00"), 
            // current stays 0, which will correctly break downstream tracking.
            twoBack = oneBack;
            oneBack = current;
        }

        return oneBack;
    }
}