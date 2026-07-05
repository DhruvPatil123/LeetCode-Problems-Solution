class Solution {
    public boolean isMatch(String s, String p) {
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;
        
        int sLen = s.length();
        int pLen = p.length();

        while (sIdx < sLen) {
            // Case 1: Standard match or '?' wildcard
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                sIdx++;
                pIdx++;
            }
            // Case 2: Encountered a '*' wildcard, record the checkpoint
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                sTmpIdx = sIdx;
                pIdx++; // Safely advance past '*' assuming 0-character match initially
            }
            // Case 3: Current characters don't match, check if we can backtrack to a previous '*'
            else if (starIdx != -1) {
                pIdx = starIdx + 1; // Reset pattern pointer to right after the star
                sTmpIdx++;          // Consume one more character via the star match
                sIdx = sTmpIdx;     // Sync up string pointer
            }
            // Case 4: Mismatch and no '*' available to absorb the characters
            else {
                return false;
            }
        }

        // Clean up remaining trailing characters in pattern (only '*' can match empty sequence)
        while (pIdx < pLen && p.charAt(pIdx) == '*') {
            pIdx++;
        }

        // If the entire pattern was consumed successfully, it's a match
        return pIdx == pLen;
    }
}