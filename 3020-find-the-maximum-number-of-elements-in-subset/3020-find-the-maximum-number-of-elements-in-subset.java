import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }
        
        int maxLength = 1;
        
        // Handle edge case for 1s
        if (freq.containsKey(1L)) {
            int count1 = freq.get(1L);
            maxLength = Math.max(maxLength, count1 % 2 == 0 ? count1 - 1 : count1);
        }
        
        // Iterate through all possible starting bases x > 1
        for (long x : freq.keySet()) {
            if (x == 1) continue;
            
            int currentLength = 0;
            long current = x;
            
            // Build the chain with elements having frequency >= 2
            while (freq.containsKey(current) && freq.get(current) >= 2) {
                currentLength += 2;
                current = current * current; // Move to the next square
            }
            
            // If the final squared number exists at least once, it can be the peak
            if (freq.containsKey(current)) {
                currentLength += 1;
            } else {
                // If it doesn't exist, the previous element must act as the peak instead
                currentLength -= 1;
            }
            
            maxLength = Math.max(maxLength, currentLength);
        }
        
        return maxLength;
    }
}