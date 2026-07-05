import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort to easily handle duplicates and enable pruning
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start) {
        if (remain == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Prune the search: if the current element exceeds the remaining target, stop the loop
            if (candidates[i] > remain) break;
            
            // Skip duplicate elements at the same recursion depth
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            tempList.add(candidates[i]);
            // Move to i + 1 because each number can only be used once
            backtrack(result, tempList, candidates, remain - candidates[i], i + 1);
            tempList.remove(tempList.size() - 1); // Backtrack
        }
    }
}