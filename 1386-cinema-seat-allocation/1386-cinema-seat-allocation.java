import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map row number -> bitmask representing reserved seats
        Map<Integer, Integer> rowToMask = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // We only care about seats 2 to 9
            if (col >= 2 && col <= 9) {
                rowToMask.put(row, rowToMask.getOrDefault(row, 0) | (1 << (col - 2)));
            }
        }
        
        // Start assuming all rows can take 2 groups
        int totalGroups = 2 * n;
        
        // Define masks for the three possible 4-seat configurations (relative to seat 2)
        // Seats 2,3,4,5 -> indices 0,1,2,3 -> binary 00001111 (15)
        int leftMask = 0b00001111;
        // Seats 6,7,8,9 -> indices 4,5,6,7 -> binary 11110000 (240)
        int rightMask = 0b11110000;
        // Seats 4,5,6,7 -> indices 2,3,4,5 -> binary 00111100 (60)
        int middleMask = 0b00111100;
        
        // Process only rows that have reservations
        for (int mask : rowToMask.values()) {
            boolean leftAvailable = (mask & leftMask) == 0;
            boolean rightAvailable = (mask & rightMask) == 0;
            
            if (leftAvailable && rightAvailable) {
                // Both blocks are clear, no groups lost for this row
                continue;
            } else if (leftAvailable || rightAvailable || (mask & middleMask) == 0) {
                // If either side is completely clear, or the center is clear, we can fit 1 group
                totalGroups -= 1; 
            } else {
                // All options blocked, we lose both group capacities for this row
                totalGroups -= 2;
            }
        }
        
        return totalGroups;
    }
}