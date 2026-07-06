class Solution {
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenE = false;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (Character.isDigit(ch)) {
                seenDigit = true;
                
            } else if (ch == '+' || ch == '-') {
                // Signs can only appear at index 0 OR immediately after 'e' or 'E'
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
                
            } else if (ch == '.') {
                // Dot cannot appear more than once, or after an exponent 'e'/'E'
                if (seenDot || seenE) {
                    return false;
                }
                seenDot = true;
                
            } else if (ch == 'e' || ch == 'E') {
                // Exponent cannot appear more than once, and must follow a digit
                if (seenE || !seenDigit) {
                    return false;
                }
                seenE = true;
                seenDigit = false; // Reset to ensure there is at least one digit after 'e'
                
            } else {
                // Any other character (like alphabets or spaces) is invalid
                return false;
            }
        }
        
        // The string is valid only if it ends having seen a digit (handles trailing 'e' or alone '.')
        return seenDigit;
    }
}