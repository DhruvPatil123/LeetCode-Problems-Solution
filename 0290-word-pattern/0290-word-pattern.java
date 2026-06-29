import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public boolean wordPattern(String pattern, String s) {
        // Split the string s into individual words
        String[] words = s.split(" ");
        
        // If the lengths don't match, a bijection is impossible
        if (pattern.length() != words.length) {
            return false;
        }
        
        // Map to keep track of char -> word mapping
        HashMap<Character, String> charToWord = new HashMap<>();
        // Set to keep track of already mapped words
        HashSet<String> usedWords = new HashSet<>();
        
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = words[i];
            
            // If the character has been seen before
            if (charToWord.containsKey(ch)) {
                // Check if it maps to the same word
                if (!charToWord.get(ch).equals(word)) {
                    return false;
                }
            } else {
                // If the character is new, but the word is already taken by another character
                if (usedWords.contains(word)) {
                    return false;
                }
                
                // Establish the bidirectional link
                charToWord.put(ch, word);
                usedWords.add(word);
            }
        }
        
        return true;
    }
}