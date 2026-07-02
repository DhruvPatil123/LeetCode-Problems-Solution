import java.util.*;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;
        
        // BFS structures
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[target + 1];
        
        queue.offer(1);
        visited[1] = true;
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all nodes at the current level (current number of dice rolls)
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                
                if (curr == target) {
                    return steps;
                }
                
                // Roll the 6-sided die
                for (int die = 1; die <= 6; die++) {
                    int next = curr + die;
                    if (next > target) break;
                    
                    // Convert 1D square label to 2D matrix coordinates
                    int[] coords = getCoordinates(next, n);
                    int r = coords[0];
                    int c = coords[1];
                    
                    // If there's a snake or ladder, take it; otherwise stay at 'next'
                    int destination = board[r][c] != -1 ? board[r][c] : next;
                    
                    if (!visited[destination]) {
                        visited[destination] = true;
                        queue.offer(destination);
                    }
                }
            }
            steps++;
        }
        
        return -1; // Target unreachable
    }
    
    // Helper method to translate 1D square index to 2D matrix coordinates
    private int[] getCoordinates(int square, int n) {
        // Zero-index the square for easier math
        int zeroIndexedSquare = square - 1;
        
        // Row index from the bottom up
        int rowFromBottom = zeroIndexedSquare / n;
        int r = n - 1 - rowFromBottom;
        
        // Column index alternates based on the row level
        int c;
        if (rowFromBottom % 2 == 0) {
            c = zeroIndexedSquare % n; // Left-to-right
        } else {
            c = n - 1 - (zeroIndexedSquare % n); // Right-to-left
        }
        
        return new int[]{r, c};
    }
}