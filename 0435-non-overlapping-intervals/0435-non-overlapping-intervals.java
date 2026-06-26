import java.util.Arrays;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // Step 1: Sort intervals by their end times in ascending order
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        int countToRemove = 0;
        // Track the end time of the last valid, non-overlapping interval we kept
        int prevEnd = intervals[0][1];
        
        // Step 2: Iterate through the intervals starting from the second one
        for (int i = 1; i < intervals.length; i++) {
            // If the current interval starts before the previous one ends, it's an overlap
            if (intervals[i][0] < prevEnd) {
                countToRemove++; // Greedily remove the interval that ends later
            } else {
                // No overlap: update our end boundary to the current interval's end
                prevEnd = intervals[i][1];
            }
        }
        
        return countToRemove;
    }
}