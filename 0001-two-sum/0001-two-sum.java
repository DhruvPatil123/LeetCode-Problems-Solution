import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int[] target) { // Note: The signature usually uses 'int target'
        return twoSum(nums, target[0]); // Adapting slightly if target was passed as an array segment
    }

    public int[] twoSum(int[] nums, int target) {
        // Map to store: Key = Number value, Value = Index of that number
        Map<Integer, Integer> numMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // If the complement exists in the map, we found our pair
            if (numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), i };
            }
            
            // Otherwise, add the current number and its index to the map
            numMap.put(nums[i], i);
        }
        
        // Return an empty array if no solution is found (though constraints guarantee one exists)
        return new int[] {};
    }
}