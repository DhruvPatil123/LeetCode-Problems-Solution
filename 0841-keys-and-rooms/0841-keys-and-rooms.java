import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        
        Queue<Integer> queue = new LinkedList<>();
        
        // Start by entering room 0
        queue.offer(0);
        visited[0] = true;
        int visitedCount = 1; // Track how many rooms we've reached
        
        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();
            
            // Loop through all keys found in the current room
            for (int key : rooms.get(currentRoom)) {
                if (!visited[key]) {
                    visited[key] = true;
                    visitedCount++;
                    queue.offer(key);
                }
            }
        }
        
        // If the total visited count matches the number of rooms, return true
        return visitedCount == n;
    }
}