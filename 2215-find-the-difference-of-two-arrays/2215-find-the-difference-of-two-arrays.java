import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        
        // Populate sets to get distinct values and O(1) lookup times
        for (int num : nums1) set1.add(num);
        for (int num : nums2) set2.add(num);
        
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        
        // Find elements in set1 that are not in set2
        for (int num : set1) {
            if (!set2.contains(num)) {
                l1.add(num);
            }
        }
        
        // Find elements in set2 that are not in set1
        for (int num : set2) {
            if (!set1.contains(num)) {
                l2.add(num);
            }
        }
        
        List<List<Integer>> result = new ArrayList<>();
        result.add(l1);
        result.add(l2);
        
        return result;
    }
}