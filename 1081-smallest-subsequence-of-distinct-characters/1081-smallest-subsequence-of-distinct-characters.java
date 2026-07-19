class Solution {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        boolean[] seen = new boolean[26];
        StringBuilder stack = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            int idx = curr - 'a';
            
            // Skip if the character is already included in our result
            if (seen[idx]) continue;
            
            // Pop characters that are heavier and appear later
            while (stack.length() > 0 && stack.charAt(stack.length() - 1) > curr 
                   && lastIndex[stack.charAt(stack.length() - 1) - 'a'] > i) {
                seen[stack.charAt(stack.length() - 1) - 'a'] = false;
                stack.deleteCharAt(stack.length() - 1);
            }
            
            stack.append(curr);
            seen[idx] = true;
        }
        
        return stack.toString();
    }
}