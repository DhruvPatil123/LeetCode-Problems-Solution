import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        // Initialize the first element as 1
        row.add(1);
        
        for (int i = 1; i <= rowIndex; i++) {
            // Add a temporary 1 at the end of the row
            row.add(1);
            
            // Update the elements from right to left
            for (int j = i - 1; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        
        return row;
    }
}