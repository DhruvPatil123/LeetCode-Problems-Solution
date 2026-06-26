import java.util.Arrays;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];
        
        // Step 1: Sort the potions array
        Arrays.sort(potions);
        
        // Step 2: For each spell, binary search for the first valid potion
        for (int i = 0; i < n; i++) {
            long spellStrength = spells[i];
            
            int low = 0;
            int high = m - 1;
            int firstValidIndex = m; // Default if no potion is strong enough
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                
                // Use long to prevent integer overflow during multiplication
                if (spellStrength * potions[mid] >= success) {
                    firstValidIndex = mid; // This is valid, but look for a smaller index to the left
                    high = mid - 1;
                } else {
                    low = mid + 1; // Too small, look to the right
                }
            }
            
            // Step 3: Total valid potions from firstValidIndex to the end of the array
            pairs[i] = m - firstValidIndex;
        }
        
        return pairs;
    }
}