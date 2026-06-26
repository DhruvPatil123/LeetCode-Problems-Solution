import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(k, n, 1, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int k, int remainingSum, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        // Base Case 1: Successfully found a combination of size k that sums to n
        if (currentCombination.size() == k && remainingSum == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        // Base Case 2: Pruning early if combination size is reached or remaining sum is exceeded
        if (currentCombination.size() == k || remainingSum < 0) {
            return;
        }

        // Only numbers 1 through 9 are allowed
        for (int i = start; i <= 9; i++) {
            currentCombination.add(i); // Choose
            
            // Explore next elements starting from i + 1 to avoid duplicates
            backtrack(k, remainingSum - i, i + 1, currentCombination, result); 
            
            currentCombination.remove(currentCombination.size() - 1); // Backtrack
        }
    }
}