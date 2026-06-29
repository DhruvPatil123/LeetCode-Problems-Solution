class Solution {
    public boolean isIsomorphic(String s, String t) {
        // Since both strings have the same length (guaranteed by constraints),
        // we can use arrays to store the last seen positions + 1 (0 means unseen).
        int[] mapS = new int[256];
        int[] mapT = new int[256];
        
        int length = s.length();
        
        for (int i = 0; i < length; i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            
            // If their previous recorded positions don't match, they aren't isomorphic
            if (mapS[charS] != mapT[charT]) {
                return false;
            }
            
            // Record the current position (using i + 1 because default value is 0)
            mapS[charS] = i + 1;
            mapT[charT] = i + 1;
        }
        
        return true;
    }
}