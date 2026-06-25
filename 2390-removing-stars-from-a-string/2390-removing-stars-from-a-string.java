class Solution {
    public String removeStars(String s) {
        StringBuilder stack = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '*') {
                // Remove the last character added (LIFO)
                stack.deleteCharAt(stack.length() - 1);
            } else {
                // Add the valid character to our stack
                stack.append(c);
            }
        }
        
        return stack.toString();
    }
}