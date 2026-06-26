import java.util.*;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Step 1: Build the graph
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];
            
            graph.putIfAbsent(u, new HashMap<>());
            graph.putIfAbsent(v, new HashMap<>());
            
            graph.get(u).put(v, val);       // u -> v (val)
            graph.get(v).put(u, 1.0 / val); // v -> u (1 / val)
        }
        
        // Step 2: Process each query
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dest = queries.get(i).get(1);
            
            // If either variable doesn't exist in our graph, it's invalid
            if (!graph.containsKey(src) || !graph.containsKey(dest)) {
                results[i] = -1.0;
            } else if (src.equals(dest)) {
                results[i] = 1.0; // Shortcut for a / a
            } else {
                Set<String> visited = new HashSet<>();
                results[i] = dfs(src, dest, 1.0, graph, visited);
            }
        }
        
        return results;
    }
    
    private double dfs(String curr, String dest, double currentProduct, 
                       Map<String, Map<String, Double>> graph, Set<String> visited) {
        
        // Base Case: If we reached the destination, return the accrued product
        if (curr.equals(dest)) {
            return currentProduct;
        }
        
        visited.add(curr);
        Map<String, Double> neighbors = graph.get(curr);
        
        for (Map.Entry<String, Double> neighbor : neighbors.entrySet()) {
            String nextNode = neighbor.getKey();
            double nextWeight = neighbor.getValue();
            
            if (!visited.contains(nextNode)) {
                double result = dfs(nextNode, dest, currentProduct * nextWeight, graph, visited);
                if (result != -1.0) {
                    return result; // Valid path found downstream
                }
            }
        }
        
        return -1.0; // No valid path through this branch
    }
}