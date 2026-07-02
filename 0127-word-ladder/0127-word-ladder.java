import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert list to a HashSet for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordList);
        
        // Base case: If the endWord isn't in the dictionary, no sequence exists
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        // The transformation sequence length starts at 1 (counting the beginWord)
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all words at the current level
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                
                // If we found the target word, return the sequence length
                if (currWord.equals(endWord)) {
                    return level;
                }
                
                char[] wordChars = currWord.toCharArray();
                
                // Mutate every character position of the word
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        
                        wordChars[j] = c;
                        String newWord = String.valueOf(wordChars);
                        
                        // If it's a valid next word in our ladder
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            // Remove from set acts as marking it "visited"
                            wordSet.remove(newWord); 
                        }
                    }
                    // Restore original character for the next position's mutations
                    wordChars[j] = originalChar;
                }
            }
            level++; // Moving to the next transformation tier
        }
        
        return 0; // Return 0 if no track down to endWord is possible
    }
}