import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1000000007;
        
        int[][] dp = new int[n][n];
        int[][] count = new int[n][n];
        
        // Initialize dp array with -1 (unreachable)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        
        // Base case at the starting point 'S'
        dp[n - 1][n - 1] = 0;
        count[n - 1][n - 1] = 1;
        
        // Direction vectors to look at valid previous positions: 
        // down (i+1, j), right (i, j+1), and down-right diagonal (i+1, j+1)
        int[] dr = {1, 0, 1};
        int[] dc = {0, 1, 1};
        
        // Iterate backwards from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Skip the starting position since it's already initialized
                if (i == n - 1 && j == n - 1) continue;
                
                char c = board.get(i).charAt(j);
                // Skip obstacles
                if (c == 'X') continue;
                
                int maxScore = -1;
                int paths = 0;
                
                // Check all 3 incoming directions
                for (int d = 0; d < 3; d++) {
                    int ni = i + dr[d];
                    int nj = j + dc[d];
                    
                    // If neighbor is within bounds and reachable
                    if (ni < n && nj < n && dp[ni][nj] != -1) {
                        if (dp[ni][nj] > maxScore) {
                            maxScore = dp[ni][nj];
                            paths = count[ni][nj];
                        } else if (dp[ni][nj] == maxScore) {
                            paths = (paths + count[ni][nj]) % MOD;
                        }
                    }
                }
                
                // If at least one neighbor was reachable, update current cell
                if (maxScore != -1) {
                    int currentVal = (c == 'E') ? 0 : (c - '0');
                    dp[i][j] = maxScore + currentVal;
                    count[i][j] = paths;
                }
            }
        }
        
        // If 'E' at (0,0) is not reachable, return [0, 0]
        if (dp[0][0] == -1) {
            return new int[]{0, 0};
        }
        
        return new int[]{dp[0][0], count[0][0]};
    }
}