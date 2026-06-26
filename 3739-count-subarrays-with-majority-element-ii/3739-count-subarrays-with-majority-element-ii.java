import java.util.*;

class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        // Step 1: Generate prefix sums
        int[] pref = new int[n + 1];
        pref[0] = 0;
        for (int i = 0; i < n; i++) {
            int val = (nums[i] == target) ? 1 : -1;
            pref[i + 1] = pref[i] + val;
        }
        
        // Step 2: Coordinate Compression
        // Collect unique values to rank them
        int[] sortedPref = pref.clone();
        Arrays.sort(sortedPref);
        
        // Step 3: Use Fenwick Tree to count pairs where pref[j] > pref[i]
        long totalSubarrays = 0;
        FenwickTree bit = new FenwickTree(sortedPref.length + 2);
        
        for (int p : pref) {
            // Find the 1-based rank of the current prefix sum
            int rank = Arrays.binarySearch(sortedPref, p) + 1;
            
            // Query how many elements have a strictly lower rank
            totalSubarrays += bit.query(rank - 1);
            
            // Add the current rank to the Fenwick Tree
            bit.update(rank, 1);
        }
        
        return totalSubarrays;
    }
    
    // Fenwick Tree (Binary Indexed Tree) implementation
    class FenwickTree {
        int[] tree;
        int size;
        
        FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size];
        }
        
        void update(int i, int delta) {
            while (i < size) {
                tree[i] += delta;
                i += i & (-i);
            }
        }
        
        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & (-i);
            }
            return sum;
        }
    }
}