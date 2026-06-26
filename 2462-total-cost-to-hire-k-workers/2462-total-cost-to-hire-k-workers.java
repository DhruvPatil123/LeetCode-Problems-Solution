import java.util.PriorityQueue;

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        
        // Min-heaps to track the lowest costs from both ends
        PriorityQueue<Integer> leftHeap = new PriorityQueue<>();
        PriorityQueue<Integer> rightHeap = new PriorityQueue<>();
        
        // Pointers to keep track of the next available worker to add to the heaps
        int left = 0;
        int right = n - 1;
        
        // Initialize the left heap with the first 'candidates' workers
        while (left < candidates) {
            leftHeap.offer(costs[left]);
            left++;
        }
        
        // Initialize the right heap with the last 'candidates' workers.
        // Ensure we don't overlap with workers already added to the left heap.
        while (right >= Math.max(left, n - candidates)) {
            rightHeap.offer(costs[right]);
            right--;
        }
        
        long totalCost = 0;
        
        // Hire exactly k workers
        for (int i = 0; i < k; i++) {
            // Peek at the minimum values from both heaps
            int leftMin = leftHeap.isEmpty() ? Integer.MAX_VALUE : leftHeap.peek();
            int rightMin = rightHeap.isEmpty() ? Integer.MAX_VALUE : rightHeap.peek();
            
            // Choose the smaller cost. Tie goes to the left side (smaller index)
            if (leftMin <= rightMin) {
                totalCost += leftHeap.poll();
                // Replenish the left heap if there are remaining middle workers
                if (left <= right) {
                    leftHeap.offer(costs[left]);
                    left++;
                }
            } else {
                totalCost += rightHeap.poll();
                // Replenish the right heap if there are remaining middle workers
                if (left <= right) {
                    rightHeap.offer(costs[right]);
                    right--;
                }
            }
        }
        
        return totalCost;
    }
}