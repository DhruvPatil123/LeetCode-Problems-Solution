class Solution {
    public int minCut(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        // cuts[i] stores the minimum cuts needed for s[0...i]
        int[] cuts = new int[n];
        
        // Initialize cuts array with the maximum possible cuts (i cuts for a string of length i+1)
        for (int i = 0; i < n; i++) {
            cuts[i] = i;
        }

        for (int mid = 0; mid < n; mid++) {
            // Case 1: Odd-length palindromes centered at 'mid' (e.g., "aba")
            expand(s, mid, mid, cuts);
            
            // Case 2: Even-length palindromes centered between 'mid' and 'mid+1' (e.g., "abba")
            expand(s, mid, mid + 1, cuts);
        }

        return cuts[n - 1];
    }

    private void expand(String s, int left, int right, int[] cuts) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (left == 0) {
                cuts[right] = 0; // s[0...right] is a palindrome, 0 cuts needed
            } else {
                cuts[right] = Math.min(cuts[right], cuts[left - 1] + 1);
            }
            left--;
            right++;
        }
    }
}