import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Find the maximum element to bound our frequency/sieve size
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // Step 1: Count individual element frequencies
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }
        
        // Step 2 & 3: Compute the exact count of pairs for each GCD using Inclusion-Exclusion
        long[] gcdCount = new long[maxVal + 1];
        
        for (int i = maxVal; i >= 1; i--) {
            // Count how many numbers in 'nums' are multiples of i
            long multiplesCount = 0;
            for (int j = i; j <= maxVal; j += i) {
                multiplesCount += freq[j];
            }
            
            // Total pairs where 'i' is a divisor
            long totalPairs = multiplesCount * (multiplesCount - 1) / 2;
            
            // Subtract pairs where the GCD is a strict multiple of i
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= gcdCount[j];
            }
            
            gcdCount[i] = totalPairs;
        }
        
        // Step 4: Build a prefix sum array of the counts
        long[] prefixSum = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSum[i] = prefixSum[i - 1] + gcdCount[i];
        }
        
        // Step 5: Process each query using Binary Search
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            
            // Binary search to find the smallest index where prefixSum[idx] > target
            int low = 1, high = maxVal;
            int ans = maxVal;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSum[mid] > target) {
                    ans = mid;      // Mid is a potential candidate
                    high = mid - 1; // Try to find a smaller valid GCD
                } else {
                    low = mid + 1;  // Not enough pairs accumulated yet
                }
            }
            answer[i] = ans;
        }
        
        return answer;
    }
}