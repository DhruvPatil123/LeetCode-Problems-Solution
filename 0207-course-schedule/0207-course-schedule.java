import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create an adjacency list to represent the graph and an array for indegrees
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Build the graph: prerequisites[i] = [course, prereq]
        // Edge goes from prereq -> course
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course);
            indegree[course]++;
        }
        
        // Add all courses with 0 prerequisites to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int completedCoursesCount = 0;
        
        // Process the graph
        while (!queue.isEmpty()) {
            int current = queue.poll();
            completedCoursesCount++;
            
            // Decrease indegree for all neighboring nodes
            for (int neighbor : adj.get(current)) {
                indegree[neighbor]--;
                // If indegree becomes 0, it means all its prerequisites are satisfied
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // If we processed all courses, no cycle exists
        return completedCoursesCount == numCourses;
    }
}