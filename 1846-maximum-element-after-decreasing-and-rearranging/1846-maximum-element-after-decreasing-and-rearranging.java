class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] counts = new int[n + 1];
        
        // Count frequencies, capping values at n
        for (int num : arr) {
            counts[Math.min(num, n)]++;
        }
        
        int maxVal = 0;
        // Process each possible value from 1 to n
        for (int i = 1; i <= n; i++) {
            // We can increase our max possible value by at most the number of elements available
            maxVal = Math.min(maxVal + counts[i], i);
        }
        
        return maxVal;
    }
}