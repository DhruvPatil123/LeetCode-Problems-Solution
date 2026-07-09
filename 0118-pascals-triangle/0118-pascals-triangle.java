import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        
        // Base case: if numRows is 0, return an empty triangle
        if (numRows == 0) {
            return triangle;
        }
        
        // First row is always [1]
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);
        
        // Generate subsequent rows
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> currRow = new ArrayList<>();
            
            // The first element of every row is always 1
            currRow.add(1);
            
            // Each middle element is the sum of the two elements directly above it
            for (int j = 1; j < i; j++) {
                currRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            
            // The last element of every row is always 1
            currRow.add(1);
            
            // Add the constructed row to our triangle list
            triangle.add(currRow);
        }
        
        return triangle;
    }
}