import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Sorting allows us to prune the search tree early
        Arrays.sort(candidates);
        
        backtrack(0, candidates, target, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int start, int[] candidates, int target, List<Integer> current, List<List<Integer>> result) {
        // Base Case: If target is met, we found a valid combination
        if (target == 0) {
            result.add(new ArrayList<>(current)); // Make a deep copy
            return;
        }
        
        // Explore choices starting from the 'start' index to avoid duplicate combinations
        for (int i = start; i < candidates.length; i++) {
            // Pruning: Since the array is sorted, if candidates[i] is greater than 
            // the remaining target, no elements after it can form a valid combination.
            if (candidates[i] > target) {
                break;
            }
            
            // 1. Take the element
            current.add(candidates[i]);
            
            // 2. Recurse: Notice we pass 'i' instead of 'i + 1' because 
            // we are allowed to reuse the same element.
            backtrack(i, candidates, target - candidates[i], current, result);
            
            // 3. Backtrack: Remove the element to explore other branches
            current.remove(current.size() - 1);
        }
    }
}