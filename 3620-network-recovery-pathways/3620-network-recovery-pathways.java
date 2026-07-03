import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // If start or end node is offline, no valid path can exist
        if (!online[0] || !online[n - 1]) return -1;
        
        // Build the full adjacency list to compute in-degrees for topological sort
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            // Only consider edges between online nodes
            if (online[u] && online[v]) {
                graph[u].add(new int[]{v, cost});
            }
        }
        
        // Find a global topological order of the online nodes
        List<Integer> topoOrder = getTopoOrder(n, graph, online);
        
        // Binary search for the maximum possible minimum-edge-cost
        int low = 0, high = 1_000_000_000;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(n, graph, topoOrder, mid, k)) {
                ans = mid; // mid is possible, try a larger minimum edge cost
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }
    
    private List<Integer> getTopoOrder(int n, List<int[]>[] graph, boolean[] online) {
        int[] inDegree = new int[n];
        for (int u = 0; u < n; u++) {
            if (!online[u]) continue;
            for (int[] edge : graph[u]) {
                inDegree[edge[0]]++;
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (online[i] && inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder.add(u);
            for (int[] edge : graph[u]) {
                int v = edge[0];
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return topoOrder;
    }
    
    private boolean isValid(int n, List<int[]>[] graph, List<Integer> topoOrder, int minEdgeCost, long k) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        
        // Dynamic programming using the precalculated topological order
        for (int u : topoOrder) {
            if (dist[u] == Long.MAX_VALUE) continue;
            
            for (int[] edge : graph[u]) {
                int v = edge[0];
                int cost = edge[1];
                
                // Only traverse edges that satisfy the binary search condition
                if (cost >= minEdgeCost) {
                    if (dist[u] + cost < dist[v]) {
                        dist[v] = dist[u] + cost;
                    }
                }
            }
        }
        
        return dist[n - 1] <= k;
    }
}