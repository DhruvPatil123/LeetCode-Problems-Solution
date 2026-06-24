import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        // Map to store (prefix_sum -> frequency of this sum)
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        
        // Base case: a prefix sum of 0 has occurred exactly once (before any elements)
        prefixSumMap.put(0, 1);
        
        int currentSum = 0;
        int count = 0;
        
        for (int num : nums) {
            currentSum += num;
            
            // Check if there is a prefix sum that satisfies: currentSum - previousSum = k
            if (prefixSumMap.containsKey(currentSum - k)) {
                count += prefixSumMap.get(currentSum - k);
            }
            
            // Record the current prefix sum in the map
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
}