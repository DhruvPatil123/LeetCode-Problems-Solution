class Solution {
    public String countAndSay(int n) {
        // Base case for n = 1
        String current = "1";
        
        // Build the sequence up to n iteratively
        for (int i = 2; i <= n; i++) {
            StringBuilder nextSequence = new StringBuilder();
            int len = current.length();
            int j = 0;
            
            // Scan through the current string to perform run-length encoding
            while (j < len) {
                char digit = current.charAt(j);
                int count = 0;
                
                // Count consecutive occurrences of the same digit
                while (j < len && current.charAt(j) == digit) {
                    count++;
                    j++;
                }
                
                // Append the frequency followed by the digit itself
                nextSequence.append(count).append(digit);
            }
            
            // Move to the next string sequence
            current = nextSequence.toString();
        }
        
        return current;
    }
}