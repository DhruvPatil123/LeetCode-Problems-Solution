import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int minReorder(int n, int[][] connections) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] c : connections) {
            adj.get(c[0]).add(new int[]{c[1], 1});
            adj.get(c[1]).add(new int[]{c[0], 0});
        }
        
        int changeCount = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(0);
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int[] edge : adj.get(curr)) {
                int neighbor = edge[0];
                int sign = edge[1];
                
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    changeCount += sign; 
                    queue.offer(neighbor);
                }
            }
        }
        
        return changeCount;
    }
}