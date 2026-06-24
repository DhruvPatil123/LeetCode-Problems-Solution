class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Array to store the last seen index of each ASCII character
        // Initialized to -1 to represent that we haven't seen any character yet
        int[] lastSeen = new int[128];
        for (int i = 0; i < 128; i++) {
            lastSeen[i] = -1;
        }

        int maxLength = 0;
        int left = 0; // Left boundary of the sliding window

        for (int right = 0; right < s.length(); right++) {
            char currChar = s.charAt(right);

            // If the character was seen before, and its last position is inside 
            // our current window, jump the left pointer right after that position
            if (lastSeen[currChar] >= left) {
                left = lastSeen[currChar] + 1;
            }

            // Update or record the last seen index of the current character
            lastSeen[currChar] = right;

            // Update the maximum length found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}