import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        
        // Step 1: Pair up nums1 and nums2, then sort based on nums2 in descending order
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
        }
        
        Arrays.sort(pairs, (a, b) -> Integer.compare(b[1], a[1]));
        
        // Min-heap to keep track of the largest 'k' elements from nums1
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        
        long currentSum = 0;
        long maxScore = 0;
        
        // Step 2 & 3: Iterate through sorted pairs
        for (int i = 0; i < n; i++) {
            int num1Val = pairs[i][0];
            int num2Min = pairs[i][1];
            
            currentSum += num1Val;
            minHeap.offer(num1Val);
            
            // If we have more than k elements, discard the smallest element from nums1
            if (minHeap.size() > k) {
                currentSum -= minHeap.poll();
            }
            
            // If we have exactly k elements, compute the potential maximum score
            if (minHeap.size() == k) {
                maxScore = Math.max(maxScore, currentSum * num2Min);
            }
        }
        
        return maxScore;
    }
}