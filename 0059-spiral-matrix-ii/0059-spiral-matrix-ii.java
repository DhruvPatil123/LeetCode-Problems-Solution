class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        // Define our 4 shifting boundaries
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        
        int num = 1;
        int maxNum = n * n;
        
        while (num <= maxNum) {
            // 1. Traverse from left to right along the top row
            for (int j = left; j <= right; j++) {
                matrix[top][j] = num++;
            }
            top++; // Shrink top boundary down
            
            // 2. Traverse from top to bottom along the right column
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--; // Shrink right boundary left
            
            // 3. Traverse from right to left along the bottom row
            for (int j = right; j >= left; j--) {
                matrix[bottom][j] = num++;
            }
            bottom--; // Shrink bottom boundary up
            
            // 4. Traverse from bottom to top along the left column
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = num++;
            }
            left++; // Shrink left boundary right
        }
        
        return matrix;
    }
}