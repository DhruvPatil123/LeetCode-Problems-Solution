import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        
        // Initialize the board with empty spaces '.'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        // Tracking arrays for fast O(1) safety checks
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // For row - col
        boolean[] diag2 = new boolean[2 * n]; // For row + col
        
        backtrack(0, n, board, result, cols, diag1, diag2);
        return result;
    }
    
    private void backtrack(int row, int n, char[][] board, List<List<String>> result, 
                           boolean[] cols, boolean[] diag1, boolean[] diag2) {
        // Base case: If all queens are safely placed
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }
        
        for (int col = 0; col < n; col++) {
            int d1 = row - col + n; // Main diagonal index
            int d2 = row + col;     // Anti-diagonal index
            
            // If the column or diagonals are already under attack, skip
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }
            
            // Place the queen
            board[row][col] = 'Q';
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            
            // Move to the next row
            backtrack(row + 1, n, board, result, cols, diag1, diag2);
            
            // Backtrack and remove the queen
            board[row][col] = '.';
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
    
    // Helper method to convert the char array grid into the required list of strings
    private List<String> constructBoard(char[][] board) {
        List<String> currentPath = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            currentPath.add(new String(board[i]));
        }
        return currentPath;
    }
}