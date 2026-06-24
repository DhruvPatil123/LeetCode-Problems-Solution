class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd-length palindrome (centered at i)
            int len1 = expandAroundCenter(s, i, i);
            
            // Case 2: Even-length palindrome (centered between i and i + 1)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Find the maximum length of the two expansions
            int maxLen = Math.max(len1, len2);

            // Update the global maximum boundary if a longer palindrome is found
            if (maxLen > (end - start + 1)) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        // Expand outwards as long as boundaries are valid and characters match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Subtract 1 because the while loop terminates *after* breaking the condition
        return right - left - 1;
    }
}