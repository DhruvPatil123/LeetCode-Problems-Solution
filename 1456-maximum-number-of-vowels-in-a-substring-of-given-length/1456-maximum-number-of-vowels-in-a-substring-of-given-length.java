class Solution {
    public int maxVowels(String s, int k) {
        int currentVowels = 0;
        
        // Step 1: Count vowels in the first window of size k
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                currentVowels++;
            }
        }
        
        int maxVowels = currentVowels;
        
        // If the first window already has the max possible vowels, return early
        if (maxVowels == k) {
            return k;
        }
        
        // Step 2: Slide the window across the rest of the string
        for (int i = k; i < s.length(); i++) {
            // Add the new character entering from the right
            if (isVowel(s.charAt(i))) {
                currentVowels++;
            }
            // Subtract the old character exiting from the left
            if (isVowel(s.charAt(i - k))) {
                currentVowels--;
            }
            
            // Update the maximum found
            maxVowels = Math.max(maxVowels, currentVowels);
            
            // Optimization: If maxVowels reaches k, we can't possibly find a better window
            if (maxVowels == k) {
                return k;
            }
        }
        
        return maxVowels;
    }
    
    // Helper function to quickly check if a character is a lowercase vowel
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}