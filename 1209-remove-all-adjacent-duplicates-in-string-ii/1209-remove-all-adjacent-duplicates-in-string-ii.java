class Solution {
    public String removeDuplicates(String s, int k) {
        // StringBuilder acts as our character stack
        StringBuilder sb = new StringBuilder();
        // Array to store the running counts of characters in the stack
        int[] counts = new int[s.length()];
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            sb.append(ch);
            int lastIdx = sb.length() - 1;
            
            // If current char matches the previous one in the stack, increment count
            if (lastIdx > 0 && sb.charAt(lastIdx - 1) == ch) {
                counts[lastIdx] = counts[lastIdx - 1] + 1;
            } else {
                counts[lastIdx] = 1; // Start a new sequence count
            }
            
            // When the streak hits k, delete the last k characters from our stack
            if (counts[lastIdx] == k) {
                sb.delete(sb.length() - k, sb.length());
            }
        }
        
        return sb.toString();
    }
}