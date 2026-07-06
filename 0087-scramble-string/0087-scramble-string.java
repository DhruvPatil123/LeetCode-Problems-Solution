import java.util.HashMap;
import java.util.Map;

class Solution {
    // Memoization map to store results of previously checked pairs
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // Base Case 1: Strings are identical
        if (s1.equals(s2)) return true;
        
        // Base Case 2: Lengths don't match (though constraints say they do)
        if (s1.length() != s2.length()) return false;
        
        // Check cache
        String key = s1 + "_" + s2;
        if (memo.containsKey(key)) return memo.get(key);

        // Pruning: Check character frequencies
        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) {
                memo.put(key, false);
                return false;
            }
        }

        int n = s1.length();
        // Try splitting the string at every possible pivot point
        for (int i = 1; i < n; i++) {
            // Case 1: Substrings are NOT swapped
            // s1 splits into [0, i) and [i, n) -> s2 splits into [0, i) and [i, n)
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && 
                isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true);
                return true;
            }

            // Case 2: Substrings ARE swapped
            // s1 splits into [0, i) and [i, n) -> s2 splits into [n - i, n) and [0, n - i)
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) && 
                isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }
}