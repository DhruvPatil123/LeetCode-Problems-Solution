import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        int n = words.length;
        
        while (i < n) {
            int j = i;
            int lineLength = 0;
            
            // Find how many words can fit in the current line
            // minimum 1 space between words -> (j - i) represents the count of spaces
            while (j < n && lineLength + words[j].length() + (j - i) <= maxWidth) {
                lineLength += words[j].length();
                j++;
            }
            
            StringBuilder sb = new StringBuilder();
            int numWords = j - i;
            int numSlots = numWords - 1;
            
            // Case 1: Last line or line contains only 1 word -> Left-justified
            if (j == n || numWords == 1) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        sb.append(" ");
                    }
                }
                // Pad trailing spaces to meet maxWidth
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
            } 
            // Case 2: Fully-justified line
            else {
                int totalSpaces = maxWidth - lineLength;
                int baseSpaces = totalSpaces / numSlots;
                int extraSpaces = totalSpaces % numSlots;
                
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        // Distribute base spaces + 1 extra space if available for leftmost slots
                        int spacesToApply = baseSpaces + (k - i < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spacesToApply; s++) {
                            sb.append(" ");
                        }
                    }
                }
            }
            
            result.add(sb.toString());
            i = j; // Move the index pointer to the next batch of words
        }
        
        return result;
    }
}