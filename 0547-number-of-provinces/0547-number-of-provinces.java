class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        
        for (int i = 0; i < n; i++) {
            // If the city hasn't been visited, it belongs to a new province
            if (!visited[i]) {
                provinces++;
                dfs(i, isConnected, visited);
            }
        }
        
        return provinces;
    }
    
    private void dfs(int city, int[][] isConnected, boolean[] visited) {
        visited[city] = true;
        
        // Check all potential connections for the current city
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(neighbor, isConnected, visited);
            }
        }
    }
}