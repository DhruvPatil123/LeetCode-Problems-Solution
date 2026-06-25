import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        
        // Harvest leaf values for both trees
        dfs(root1, leaves1);
        dfs(root2, leaves2);
        
        // Compare the two sequences
        return leaves1.equals(leaves2);
    }
    
    private void dfs(TreeNode node, List<Integer> leaves) {
        if (node == null) {
            return;
        }
        
        // Base case: If it's a leaf node, record its value
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
            return;
        }
        
        // Recursive step: Traverse left first, then right to maintain left-to-right order
        dfs(node.left, leaves);
        dfs(node.right, leaves);
    }
}