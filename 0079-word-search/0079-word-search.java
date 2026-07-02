class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        // Try to start the word from every cell in the grid
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == word.charAt(0)) {
                    if (dfs(board, word, r, c, 0)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int r, int c, int index) {
        // Base Case: If we've successfully matched all characters in the word
        if (index == word.length()) {
            return true;
        }
        
        // Boundary conditions and mismatch check
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }
        
        // 1. Mark the current cell as visited by storing its original value and overwriting it
        char temp = board[r][c];
        board[r][c] = '#'; 
        
        // 2. Recurse in all 4 directions for the next character
        boolean found = dfs(board, word, r + 1, c, index + 1) || // Down
                        dfs(board, word, r - 1, c, index + 1) || // Up
                        dfs(board, word, r, c + 1, index + 1) || // Right
                        dfs(board, word, r, c - 1, index + 1);   // Left
                        
        // 3. Backtrack: Restore the original character for other search branches
        board[r][c] = temp;
        
        return found;
    }
}