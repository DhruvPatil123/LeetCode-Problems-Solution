import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;
        
        // Step 1: Find all initial rotten oranges and count fresh ones
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshOranges++;
                }
            }
        }
        
        // If there are no fresh oranges to begin with, 0 minutes are needed
        if (freshOranges == 0) return 0;
        
        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // Step 2: Begin BFS traversal minute by minute
        while (!queue.isEmpty() && freshOranges > 0) {
            int size = queue.size();
            minutes++; // Increment time for the upcoming wave of rotting
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    // Check boundaries and if the adjacent orange is fresh
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2; // Infect the orange
                        freshOranges--;   // Reduce the fresh orange count
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        
        // Step 3: Check if any fresh oranges are completely isolated
        return freshOranges == 0 ? minutes : -1;
    }
}