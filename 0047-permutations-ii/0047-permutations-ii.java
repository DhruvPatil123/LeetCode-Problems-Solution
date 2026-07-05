import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 1. Sort the array to group identical numbers together
        Arrays.sort(nums);
        
        // 2. Backtrack with a 'used' array tracking indices
        backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, boolean[] used) {
        // Base case: If the current permutation is complete
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if the element at index i is already part of the current permutation path
            if (used[i]) continue;

            // Crucial Deduplication Check:
            // If the current number is the same as the previous one, 
            // only use it if the previous one is already consumed in this path.
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            // Move forward
            used[i] = true;
            tempList.add(nums[i]);

            // Recurse
            backtrack(result, tempList, nums, used);

            // Backtrack
            used[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }
}