class Solution {
    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {
            // Step 1: Skip trailing/intervening spaces
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            
            // If we reached the beginning of the string, we're done
            if (i < 0) {
                break;
            }

            // 'right' marks the end of the current word
            int right = i;

            // Step 2: Find the start of the current word
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }

            // If this isn't the first word we're adding, append a separating space
            if (result.length() > 0) {
                result.append(" ");
            }

            // Step 3: Append the word (from i + 1 to right)
            result.append(s.substring(i + 1, right + 1));
        }

        return result.toString();
    }
}