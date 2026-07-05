import java.util.ArrayList;
import java.util.List;

class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n + 1];
        StringBuilder sb = new StringBuilder();
        
        // 1. Calculate factorials and initialize the list of digits
        int sum = 1;
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
            factorial[i] = sum;
            numbers.add(i);
        }
        
        // 2. Convert k to 0-based indexing
        k--;
        
        // 3. Directly determine digits one by one
        for (int i = 1; i <= n; i++) {
            // Find how many permutations are inside each sub-block
            int blockCount = factorial[n - i];
            
            // Determine the correct index of the next digit
            int index = k / blockCount;
            
            // Append the digit and remove it from the pool
            sb.append(numbers.get(index));
            numbers.remove(index);
            
            // Update k for the next iteration
            k %= blockCount;
        }
        
        return sb.toString();
    }
}