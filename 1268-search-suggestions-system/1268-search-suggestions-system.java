import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        
        // Step 1: Sort products lexicographically
        Arrays.sort(products);
        
        int left = 0;
        int right = products.length - 1;
        
        // Step 2: Iterate through each character of the searchWord
        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            
            // Narrow left pointer: eliminate products that don't match prefix at index i
            while (left <= right && (products[left].length() <= i || products[left].charAt(i) != c)) {
                left++;
            }
            
            // Narrow right pointer: eliminate products that don't match prefix at index i
            while (left <= right && (products[right].length() <= i || products[right].charAt(i) != c)) {
                right--;
            }
            
            // Step 3: Collect up to 3 valid lexicographical products within the window
            List<String> currentSuggestions = new ArrayList<>();
            int windowSize = Math.min(3, right - left + 1);
            for (int j = 0; j < windowSize; j++) {
                currentSuggestions.add(products[left + j]);
            }
            
            result.add(currentSuggestions);
        }
        
        return result;
    }
}