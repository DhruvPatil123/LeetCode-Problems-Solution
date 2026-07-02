class Solution {
    private int count = 0;

    public int totalNQueens(int n) {
        // Track occupied vertical columns
        boolean[] cols = new boolean[n];
        // Track occupied major diagonals (\) -> index calculated as: row - col + n
        boolean[] diag1 = new boolean[2 * n];
        // Track occupied minor diagonals (/) -> index calculated as: row + col
        boolean[] diag2 = new boolean[2 * n];
        
        backtrack(0, n, cols, diag1, diag2);
        return count;
    }
    
    private void backtrack(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        // Base Case: If we have successfully placed queens in all rows (0 to n-1)
        if (row == n) {
            count++;
            return;
        }
        
        // Try placing a queen in every column of the current row
        for (int col = 0; col < n; col++) {
            int d1 = row - col + n;
            int d2 = row + col;
            
            // Check if the current position is under attack
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }
            
            // 1. Place the queen (Mark positions as attacked)
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            
            // 2. Recurse to the next row
            backtrack(row + 1, n, cols, diag1, diag2);
            
            // 3. Backtrack (Remove the queen and restore state)
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}