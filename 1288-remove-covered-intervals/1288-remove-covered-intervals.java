import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort by start ascending, then by end descending
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        
        int coveredCount = 0;
        int maxEnd = 0;
        
        for (int[] interval : intervals) {
            int end = interval[1];
            // If the current interval's end is within maxEnd, it is covered
            if (end <= maxEnd) {
                coveredCount++;
            } else {
                maxEnd = end;
            }
        }
        
        return intervals.length - coveredCount;
    }
}