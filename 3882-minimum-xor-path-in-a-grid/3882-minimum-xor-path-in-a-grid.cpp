#include <vector>
#include <bitset>
#include <algorithm>

class Solution {
public:
    int minCost(std::vector<std::vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        
        // dp[i][j] is a bitset where the k-th bit is 1 if XOR value k is reachable at cell (i, j)
        std::vector<std::vector<std::bitset<1024>>> dp(m, std::vector<std::bitset<1024>>(n));
        
        // Base case: starting cell (0, 0)
        dp[0][0].set(grid[0][0]);
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // If coming from the top (i - 1, j)
                if (i > 0) {
                    for (int x = 0; x < 1024; ++x) {
                        if (dp[i - 1][j][x]) {
                            dp[i][j].set(x ^ grid[i][j]);
                        }
                    }
                }
                // If coming from the left (i, j - 1)
                if (j > 0) {
                    for (int x = 0; x < 1024; ++x) {
                        if (dp[i][j - 1][x]) {
                            dp[i][j].set(x ^ grid[i][j]);
                        }
                    }
                }
            }
        }
        
        // Find the minimum XOR value at the bottom-right cell (m - 1, n - 1)
        for (int x = 0; x < 1024; ++x) {
            if (dp[m - 1][n - 1][x]) {
                return x;
            }
        }
        
        return 0;
    }
};