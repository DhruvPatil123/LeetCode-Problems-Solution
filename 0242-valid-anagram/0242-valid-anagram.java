import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        Map<Character, Integer> charCounts = new HashMap<>();
        
        // Count frequencies of characters in string s
        for (char c : s.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }
        
        // Decrement frequencies using string t
        for (char c : t.toCharArray()) {
            if (!charCounts.containsKey(c)) {
                return false;
            }
            charCounts.put(c, charCounts.get(c) - 1);
            if (charCounts.get(c) == 0) {
                charCounts.remove(c);
            }
        }
        
        // If the map is empty, all character frequencies matched perfectly
        return charCounts.isEmpty();
    }
}