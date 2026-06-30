class Solution {
    public int numberOfSubstrings(String s) {

        int[] count = new int[3]; // a,b,c
        int left = 0;
        int ans = 0;
        int n = s.length();

        for (int right = 0; right < n; right++) {

            // Add current character
            count[s.charAt(right) - 'a']++;

            // While window contains all a,b,c
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {

                // All substrings ending from right to n-1 are valid
                ans += n - right;

                // Shrink window
                count[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return ans;
    }
}