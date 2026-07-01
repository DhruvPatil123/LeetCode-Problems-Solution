class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        
        int m = board.length;
        int n = board[0].length;
        
        // Step 1: Run DFS from 'O's on the left and right borders
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][n - 1] == 'O') dfs(board, i, n - 1);
        }
        
        // Step 1: Run DFS from 'O's on the top and bottom borders
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[m - 1][j] == 'O') dfs(board, m - 1, j);
        }
        
        // Step 2 & 3: Traverse the board to flip surrounded regions and restore border regions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'; // Surrounded, capture it
                } else if (board[i][j] == 'E') {
                    board[i][j] = 'O'; // Connected to boundary, restore it
                }
            }
        }
    }
    
    private void dfs(char[][] board, int r, int c) {
        int m = board.length;
        int n = board[0].length;
        
        // Base case: check boundary limits and if the cell is an 'O'
        if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] != 'O') {
            return;
        }
        
        // Mark current cell as 'E' (Escaped / Safe from capture)
        board[r][c] = 'E';
        
        // Traverse 4-directionally
        dfs(board, r - 1, c); // Up
        dfs(board, r + 1, c); // Down
        dfs(board, r, c - 1); // Left
        dfs(board, r, c + 1); // Right
    }
}