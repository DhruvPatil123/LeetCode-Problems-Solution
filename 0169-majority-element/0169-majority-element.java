class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        
        for (int num : nums) {
            // If count falls to 0, we choose a new candidate
            if (count == 0) {
                candidate = num;
            }
            
            // If the current number matches the candidate, increment count.
            // Otherwise, decrement count (cancellation step).
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        // The problem guarantees that a majority element always exists,
        // so the remaining candidate is guaranteed to be correct.
        return candidate;
    }
}