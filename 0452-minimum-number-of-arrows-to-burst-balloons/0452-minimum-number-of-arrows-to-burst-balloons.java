import java.util.Arrays;

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        // Sort balloons by their end coordinates securely against integer overflow
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        // First arrow is placed at the end of the first balloon
        int arrows = 1;
        int currentEnd = points[0][1];

        for (int i = 1; i < points.length; i++) {
            // If the next balloon starts after the current arrow position, we need a new arrow
            if (points[i][0] > currentEnd) {
                arrows++;
                currentEnd = points[i][1]; // Move arrow to the end of the new balloon
            }
        }

        return arrows;
    }
}