class Solution {
    public int compress(char[] chars) {
        int write = 0;
        int read = 0;
        
        while (read < chars.length) {
            char currentChar = chars[read];
            int count = 0;
            
            // Count the consecutive occurrences of the current character
            while (read < chars.length && chars[read] == currentChar) {
                read++;
                count++;
            }
            
            // Write the character to the write index
            chars[write++] = currentChar;
            
            // If the character repeated, write the count digits
            if (count > 1) {
                for (char c : Integer.toString(count).toCharArray()) {
                    chars[write++] = c;
                }
            }
        }
        
        // The write pointer represents the new length of the compressed array
        return write;
    }
}