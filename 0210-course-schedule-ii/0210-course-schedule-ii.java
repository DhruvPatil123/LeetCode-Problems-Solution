import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Create adjacency list and in-degree tracking array
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Build the graph: prerequisites[i] = [course, pre] -> pre points to course
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int p = pre[1];
            adj.get(p).add(course);
            indegree[course]++;
        }
        
        // Add all courses with 0 in-degree to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] order = new int[numCourses];
        int index = 0;
        
        // Process the courses
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order[index++] = curr;
            
            // Reduce the in-degree for all adjacent dependent courses
            for (int neighbor : adj.get(curr)) {
                indegree[neighbor]--;
                // If in-degree becomes 0, it has no remaining prerequisites
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // If index != numCourses, a cycle exists, making it impossible to finish
        if (index == numCourses) {
            return order;
        } else {
            return new int[0];
        }
    }
}