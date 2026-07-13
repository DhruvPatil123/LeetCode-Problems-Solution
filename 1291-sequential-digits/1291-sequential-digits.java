import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String sample = "123456789";
        
        // Loop through all possible lengths of numbers (from 2 digits up to 9 digits)
        for (int length = 2; length <= 9; length++) {
            // Slide a window of 'length' across the sample string
            for (int start = 0; start <= 9 - length; start++) {
                String substring = sample.substring(start, start + length);
                int num = Integer.parseInt(substring);
                
                // If it fits within the range, add it to our list
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}