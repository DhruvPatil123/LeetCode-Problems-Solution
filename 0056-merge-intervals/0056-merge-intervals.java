import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort by starting point
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();

        for (int[] interval : intervals) {

            // If result is empty OR no overlap
            if (result.isEmpty() || 
                result.get(result.size() - 1)[1] < interval[0]) {

                result.add(interval);
            } 
            // Overlap exists → merge
            else {
                result.get(result.size() - 1)[1] =
                    Math.max(result.get(result.size() - 1)[1], interval[1]);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}