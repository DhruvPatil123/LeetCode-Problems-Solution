import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0); // Base case for n = 0 (or starting point)
        
        for (int i = 0; i < n; i++) {
            int size = result.size();
            int mask = 1 << i; // This represents the heading bit to prepend
            
            // Iterate backwards through the existing list to create the mirror reflection
            for (int j = size - 1; j >= 0; j--) {
                result.add(result.get(j) | mask);
            }
        }
        
        return result;
    }
}