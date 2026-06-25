class Solution {
    public boolean isSubsequence(String s, String t) {
        // An empty string is always a subsequence of any string
        if (s.isEmpty()) {
            return true;
        }

        int pS = 0; // Pointer for s
        int pT = 0; // Pointer for t

        while (pT < t.length()) {
            // If characters match, move the pointer for s
            if (s.charAt(pS) == t.charAt(pT)) {
                pS++;
                // If we've matched all characters of s, we're done
                if (pS == s.length()) {
                    return true;
                }
            }
            // Always move the pointer for t
            pT++;
        }

        return false;
    }
}