import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        int sLen = s.length();

        // Count frequencies of each word in the input array
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Run 'wordLen' independent sliding windows
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> currentFreq = new HashMap<>();
            int matchedWordsCount = 0;

            // Slide across the string by 'wordLen' steps
            while (right + wordLen <= sLen) {
                // Pull the next word from the right
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordFreq.containsKey(word)) {
                    currentFreq.put(word, currentFreq.getOrDefault(word, 0) + 1);
                    matchedWordsCount++;

                    // If a word's count exceeds what's required, shrink window from left
                    while (currentFreq.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentFreq.put(leftWord, currentFreq.get(leftWord) - 1);
                        matchedWordsCount--;
                        left += wordLen;
                    }

                    // If the total window matches the targeted word count, we found a match
                    if (matchedWordsCount == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Invalid word encountered: Reset window completely past 'right'
                    currentFreq.clear();
                    matchedWordsCount = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}