class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        
        // 8 possible directions for neighbors
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        // Step 1: Track state transitions
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int liveNeighbors = 0;
                
                // Count current live neighbors
                for (int i = 0; i < 8; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                        // 1 or 3 means the neighbor is currently alive
                        if (board[nr][nc] == 1 || board[nr][nc] == 3) {
                            liveNeighbors++;
                        }
                    }
                }
                
                // Apply Conway's rules
                if (board[r][c] == 1) {
                    if (liveNeighbors == 2 || liveNeighbors == 3) {
                        board[r][c] = 3; // Lives on
                    } // Else it defaults to 1 (Dies)
                } else {
                    if (liveNeighbors == 3) {
                        board[r][c] = 2; // Becomes live
                    }
                }
            }
        }
        
        // Step 2: Final update to resolve the states
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 1) {
                    board[r][c] = 0;
                } else if (board[r][c] == 2 || board[r][c] == 3) {
                    board[r][c] = 1;
                }
            }
        }
    }
}