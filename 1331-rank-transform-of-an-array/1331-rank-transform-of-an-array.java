import java.util.*;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // Create a copy of the array and sort it
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        
        // Map to store the element to rank mapping
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        
        for (int num : sortedArr) {
            // Assign rank only if the element hasn't been ranked yet
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank++;
            }
        }
        
        // Replace each element with its rank
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }
        
        return result;
    }
}