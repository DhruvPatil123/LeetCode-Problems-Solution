class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Frequency array for characters in t
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }

        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;
        int required = t.length();

        // Expand the right boundary of the window
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            
            // If the character is needed in t, decrement required count
            if (map[rightChar] > 0) {
                required--;
            }
            // Decrement the map count for this character
            map[rightChar]--;
            right++;

            // When we have a valid window containing all characters of t
            while (required == 0) {
                // Update the minimum window details
                if (right - left < minLen) {
                    minLen = right - left;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);
                // Try to shrink the window from the left
                map[leftChar]++;
                // If it becomes positive, it means we dropped a character essential to t
                if (map[leftChar] > 0) {
                    required++;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}