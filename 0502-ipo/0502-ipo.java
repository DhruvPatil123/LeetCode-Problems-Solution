import java.util.*;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        
        // 1. Pair up capital and profits, then sort by minimum capital required
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }
        
        Arrays.sort(projects, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 2. Max-Heap to store profits of all currently affordable projects
        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        
        int projectIndex = 0;
        
        // 3. Select up to k projects greedily
        for (int i = 0; i < k; i++) {
            // Push all projects we can afford with our current capital into the heap
            while (projectIndex < n && projects[projectIndex][0] <= w) {
                maxProfitHeap.offer(projects[projectIndex][1]);
                projectIndex++;
            }
            
            // If there are no projects we can afford, we can't proceed further
            if (maxProfitHeap.isEmpty()) {
                break;
            }
            
            // Greedily pick the project with the maximum profit
            w += maxProfitHeap.poll();
        }
        
        return w;
    }
}