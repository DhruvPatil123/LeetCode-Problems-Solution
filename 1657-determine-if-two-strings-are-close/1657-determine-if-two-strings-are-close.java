import java.util.Arrays;

class Solution {
    public boolean closeStrings(String word1, String word2) {
        // Rule 0: If lengths don't match, they can never be close
        if (word1.length() != word2.length()) {
            return false;
        }

        // Arrays to store the frequency counts for lowercase English letters
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (int i = 0; i < word1.length(); i++) {
            freq1[word1.charAt(i) - 'a']++;
            freq2[word2.charAt(i) - 'a']++;
        }

        // Rule 1: Both strings must contain the exact same set of unique characters
        for (int i = 0; i < 26; i++) {
            if ((freq1[i] == 0 && freq2[i] != 0) || (freq1[i] != 0 && freq2[i] == 0)) {
                return false;
            }
        }

        // Rule 2: The distribution of frequencies must be identical
        Arrays.sort(freq1);
        Arrays.sort(freq2);

        return Arrays.equals(freq1, freq2);
    }
}