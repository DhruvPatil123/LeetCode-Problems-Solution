import java.util.*;

public class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // If the start or end cell has a thief, the safeness factor is immediately 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        int[][] safeness = new int[n][n];
        for (int[] row : safeness) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Initialize Multi-Source BFS with all thieves
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    safeness[r][c] = 0;
                    queue.offer(new int[]{r, c});
                }
            }
        }

        // Directions for moving Up, Down, Left, Right
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Calculate Manhattan distance to the nearest thief for every cell
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && safeness[nr][nc] == Integer.MAX_VALUE) {
                    safeness[nr][nc] = safeness[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        // Step 2: Use a Max-Heap (Priority Queue) to find the path maximizing the minimum safeness
        // Priority Queue stores elements as {safeness_factor, row, col}, sorted in descending order of safeness
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        
        // Track visited positions for the path finding
        boolean[][] visited = new boolean[n][n];
        
        pq.offer(new int[]{safeness[0][0], 0, 0});
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currSafeness = curr[0];
            int r = curr[1], c = curr[2];

            // Reached the destination, this path's bottleneck safeness is the maximum possible
            if (r == n - 1 && c == n - 1) {
                return currSafeness;
            }

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // The safeness of the path to (nr, nc) is limited by the minimum safeness encountered so far
                    pq.offer(new int[]{Math.min(currSafeness, safeness[nr][nc]), nr, nc});
                }
            }
        }

        return 0;
    }
}