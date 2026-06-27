class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];
        
        // Step 1: Populate the buckets
        for (int c : citations) {
            if (c >= n) {
                buckets[n]++; // Cap citations greater than or equal to n
            } else {
                buckets[c]++;
            }
        }
        
        // Step 2: Iterate backwards to find the h-index
        int totalPapers = 0;
        for (int i = n; i >= 0; i--) {
            totalPapers += buckets[i]; // Add papers with at least 'i' citations
            
            // If the total papers found so far is >= current citation threshold i
            if (totalPapers >= i) {
                return i;
            }
        }
        
        return 0;
    }
}