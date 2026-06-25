import java.util.HashMap;
import java.util.Map;

class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> rowCounter = new HashMap<>();
        
        // Step 1: Serialize each row and store its frequency count
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]).append(",");
            }
            String rowStr = sb.toString();
            rowCounter.put(rowStr, rowCounter.getOrDefault(rowStr, 0) + 1);
        }
        
        int equalPairsCount = 0;
        
        // Step 2: Serialize each column and look it up in our map
        for (int j = 0; j < n; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(grid[i][j]).append(",");
            }
            String colStr = sb.toString();
            
            // If the column string matches a row string, add its frequency
            if (rowCounter.containsKey(colStr)) {
                equalPairsCount += rowCounter.get(colStr);
            }
        }
        
        return equalPairsCount;
    }
}