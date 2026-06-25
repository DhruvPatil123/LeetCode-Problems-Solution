import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        // Step 1: Count occurrences of each number
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Step 2: Use a HashSet to see if any counts repeat
        Set<Integer> uniqueCounts = new HashSet<>(frequencyMap.values());
        
        // If the size matches, all occurrence counts are unique
        return frequencyMap.size() == uniqueCounts.size();
    }
}