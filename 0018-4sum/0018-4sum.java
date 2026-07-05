import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        // 1. Sort the array to implement the two-pointer approach
        Arrays.sort(nums);
        int n = nums.length;

        // 2. First pointer 'i'
        for (int i = 0; i < n - 3; i++) {
            // Skip duplicate values for the first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Optimization 1: If the smallest 4 numbers exceed target, no solution possible
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            // Optimization 2: If the largest possible 4 numbers can't reach target, move i forward
            if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;

            // 3. Second pointer 'j'
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicate values for the second element
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Optimization 3: Check min and max possibilities for this specific j loop
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if ((long) nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) continue;

                // 4. Two-pointer approach for the remaining two elements
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    // Use long to prevent integer overflow
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Move pointers past duplicates
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // We need a larger sum
                    } else {
                        right--; // We need a smaller sum
                    }
                }
            }
        }

        return result;
    }
}