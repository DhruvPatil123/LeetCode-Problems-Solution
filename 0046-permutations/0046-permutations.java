import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Track elements used in the current permutation path
        boolean[] visited = new boolean[nums.length];
        
        backtrack(nums, new ArrayList<>(), visited, result);
        return result;
    }
    
    private void backtrack(int[] nums, List<Integer> current, boolean[] visited, List<List<Integer>> result) {
        // Base case: If the current permutation is complete
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current)); // Make a deep copy
            return;
        }
        
        // Explore all available choices
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                // Take the element
                visited[i] = true;
                current.add(nums[i]);
                
                // Recursively explore further down this path
                backtrack(nums, current, visited, result);
                
                // Backtrack: undo our choice for the next iterations
                current.remove(current.size() - 1);
                visited[i] = false;
            }
        }
    }
}