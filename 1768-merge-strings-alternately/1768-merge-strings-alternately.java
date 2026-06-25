class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder merged = new StringBuilder();
        int i = 0;
        int len1 = word1.length();
        int len2 = word2.length();

        // Loop as long as there are characters left in either string
        while (i < len1 || i < len2) {
            // Append from word1 if characters are available
            if (i < len1) {
                merged.append(word1.charAt(i));
            }
            // Append from word2 if characters are available
            if (i < len2) {
                merged.append(word2.charAt(i));
            }
            i++;
        }

        return merged.toString();
    }
}