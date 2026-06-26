import java.util.ArrayList;
import java.util.List;

class Solution {
    // Mapping of digits 2-9 to their corresponding letters
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        // Edge Case: If the input is empty, return an empty list immediately
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        backtrack(0, digits, new StringBuilder(), result);
        return result;
    }

    private void backtrack(int index, String digits, StringBuilder currentCombination, List<String> result) {
        // Base Case: If the combination is complete, save it
        if (index == digits.length()) {
            result.add(currentCombination.toString());
            return;
        }

        // Get the letters corresponding to the current digit
        char digit = digits.charAt(index);
        String letters = KEYPAD[digit - '0'];

        // Loop through all options for this digit
        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            
            currentCombination.append(letter);                  // Choose
            backtrack(index + 1, digits, currentCombination, result); // Explore
            currentCombination.deleteCharAt(currentCombination.length() - 1); // Backtrack
        }
    }
}