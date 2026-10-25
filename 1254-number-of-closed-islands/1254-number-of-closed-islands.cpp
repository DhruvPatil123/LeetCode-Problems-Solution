class Solution {
private:
    bool dfs(int r, int c, vector<vector<int>>& grid) {
        int rows = grid.size();
        int cols = grid[0].size();
        
        // Agar boundary se bahar chala gaya, matlab ye closed nahi hai
        if (r < 0 || r >= rows || c < 0 || c >= cols)
            return false;
            
        // Agar pani (1) mil gaya ya pehle visit ho chuka hai, toh return true
        if (grid[r][c] == 1)
            return true;
            
        // Visit kar liya, toh land ko water (1) mark kar do taaki dubara count na ho
        grid[r][c] = 1;
        
        // 4 directions mein traverse karo
        bool left = dfs(r, c - 1, grid);
        bool right = dfs(r, c + 1, grid);
        bool top = dfs(r - 1, c, grid);
        bool bottom = dfs(r + 1, c, grid);
        
        // Tabhi closed hoga jab saari directions valid ho
        return left && right && top && bottom;
    }

public:
    int closedIsland(vector<vector<int>>& grid) {
        int rows = grid.size();
        int cols = grid[0].size();
        
        // Step 1: Boundary ke saare 0s aur unke connected components ko flood fill karke 1 bana do
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if ((i == 0 || i == rows - 1 || j == 0 || j == cols - 1) && grid[i][j] == 0) {
                    dfs(i, j, grid);
                }
            }
        }
        
        // Step 2: Baki bache hue valid closed islands ko count karo
        int closedIslandsCount = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 0) {
                    if (dfs(i, j, grid)) {
                        closedIslandsCount++;
                    }
                }
            }
        }
        
        return closedIslandsCount;
    }
};