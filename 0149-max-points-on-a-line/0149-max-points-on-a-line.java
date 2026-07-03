import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        
        int maxGlobalPoints = 0;
        
        // Treat each point as an anchor/pivot point
        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int localMax = 0;
            
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                // Reduce the fraction using GCD to prevent floating point precision issues
                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;
                
                // Canonical sign stabilization: ensure the sign is predictable
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    // Vertical line normalization
                    dy = Math.abs(dy);
                }
                
                // Create a unique string key representing the precise slope
                String slopeKey = dy + "/" + dx;
                
                slopeMap.put(slopeKey, slopeMap.getOrDefault(slopeKey, 0) + 1);
                localMax = Math.max(localMax, slopeMap.get(slopeKey));
            }
            
            // +1 accounts for the anchor point 'i' itself
            maxGlobalPoints = Math.max(maxGlobalPoints, localMax + 1);
        }
        
        return maxGlobalPoints;
    }
    
    // Helper method to find the greatest common divisor
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}