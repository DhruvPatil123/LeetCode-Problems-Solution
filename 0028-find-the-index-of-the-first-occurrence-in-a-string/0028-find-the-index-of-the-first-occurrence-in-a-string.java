class Solution {
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length();
        int nLen = needle.length();
        
        for (int i = 0; i <= hLen - nLen; i++) {
            int j = 0;
            // Match character by character
            while (j < nLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            // If we successfully matched the whole needle
            if (j == nLen) {
                return i;
            }
        }
        return -1;
    }
}