import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // min_loss[r][c] will store the minimum health lost to reach (r, c)
        int[][] min_loss = new int[m][n];
        for (int[] row : min_loss) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        Deque<int[]> deque = new ArrayDeque<>();
        
        // Initialize starting point
        min_loss[0][0] = grid.get(0).get(0);
        deque.offerFirst(new int[]{0, 0});
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            
            // If we reached the destination, we can early exit if desired, 
            // but standard 0-1 BFS ensures correctness upon completion.
            if (r == m - 1 && c == n - 1) {
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int weight = grid.get(nr).get(nc);
                    int nextLoss = min_loss[r][c] + weight;
                    
                    if (nextLoss < min_loss[nr][nc]) {
                        min_loss[nr][nc] = nextLoss;
                        if (weight == 0) {
                            deque.offerFirst(new int[]{nr, nc}); // 0-weight edges go to the front
                        } else {
                            deque.offerLast(new int[]{nr, nc});  // 1-weight edges go to the back
                        }
                    }
                }
            }
        }
        
        // We must reach the end with at least 1 health point remaining
        return health > min_loss[m - 1][n - 1];
    }
}