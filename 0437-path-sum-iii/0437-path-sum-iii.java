/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.HashMap;

class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        // Map to store (prefix_sum -> count)
        HashMap<Long, Integer> prefixSumMap = new HashMap<>();
        // Base case: a prefix sum of 0 has been seen once (empty path)
        prefixSumMap.put(0L, 1);
        
        return dfs(root, 0L, targetSum, prefixSumMap);
    }
    
    private int dfs(TreeNode node, long currentSum, int targetSum, HashMap<Long, Integer> prefixSumMap) {
        if (node == null) {
            return 0;
        }
        
        // Update the running prefix sum
        currentSum += node.val;
        
        // Number of valid paths ending at the current node
        int count = prefixSumMap.getOrDefault(currentSum - targetSum, 0);
        
        // Add the current prefix sum to the map for child nodes to use
        prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        
        // Explore left and right subtrees
        count += dfs(node.left, currentSum, targetSum, prefixSumMap);
        count += dfs(node.right, currentSum, targetSum, prefixSumMap);
        
        // Backtrack: remove the current prefix sum so it doesn't affect other branches
        prefixSumMap.put(currentSum, prefixSumMap.get(currentSum) - 1);
        
        return count;
    }
}