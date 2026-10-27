class Solution {
public:
    vector<string> createGrid(int m, int n) {
        vector<string> grid(m, string(n, '#'));
        
        // Go right along the top row
        for (int j = 0; j < n; ++j) {
            grid[0][j] = '.';
        }
        
        // Go down along the last column
        for (int i = 0; i < m; ++i) {
            grid[i][n - 1] = '.';
        }
        
        return grid;
    }
};