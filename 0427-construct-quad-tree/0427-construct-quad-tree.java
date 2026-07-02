/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
};
*/

class Solution {
    public Node construct(int[][] grid) {
        return helper(grid, 0, grid.length - 1, 0, grid[0].length - 1);
    }
    
    private Node helper(int[][] grid, int r1, int r2, int c1, int c2) {
        // Base Case Check: Are all elements in this sub-grid uniform?
        if (isUniform(grid, r1, r2, c1, c2)) {
            return new Node(grid[r1][c1] == 1, true);
        }
        
        // If not uniform, it's an internal node. Compute midpoints to split into 4 quadrants.
        int midRow = r1 + (r2 - r1) / 2;
        int midCol = c1 + (c2 - c1) / 2;
        
        Node root = new Node(true, false); // val can be anything when isLeaf is false
        
        root.topLeft = helper(grid, r1, midRow, c1, midCol);
        root.topRight = helper(grid, r1, midRow, midCol + 1, c2);
        root.bottomLeft = helper(grid, midRow + 1, r2, c1, midCol);
        root.bottomRight = helper(grid, midRow + 1, r2, midCol + 1, c2);
        
        return root;
    }
    
    // Helper function to verify if all cells within the coordinates have identical values
    private boolean isUniform(int[][] grid, int r1, int r2, int c1, int c2) {
        int val = grid[r1][c1];
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                if (grid[r][c] != val) {
                    return false;
                }
            }
        }
        return true;
    }
}