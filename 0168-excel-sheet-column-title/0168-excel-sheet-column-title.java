class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        
        while (columnNumber > 0) {
            // Shift to 0-indexed to make modulo math work perfectly
            columnNumber--;
            
            int remainder = columnNumber % 26;
            sb.append((char) ('A' + remainder));
            
            // Move to the next significant digit
            columnNumber /= 26;
        }
        
        // Reverse because digits were collected from right to left
        return sb.reverse().toString();
    }
}