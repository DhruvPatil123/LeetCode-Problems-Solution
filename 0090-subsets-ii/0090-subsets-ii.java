import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Step 1: Sort the array to bring duplicates together
        Arrays.sort(nums);
        // Step 2: Kick off the backtracking helper
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentSubset, int[] nums, int start) {
        // Every combination generated is a valid subset, copy it to the result
        result.add(new ArrayList<>(currentSubset));

        for (int i = start; i < nums.length; i++) {
            // If the element is a duplicate of the previous element at the same level, skip it
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // Include the element
            currentSubset.add(nums[i]);
            
            // Move to the next element
            backtrack(result, currentSubset, nums, i + 1);
            
            // Backtrack: remove the element before the next iteration
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}