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
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case: both nodes are null, so they are structurally identical
        if (p == null && q == null) {
            return true;
        }
        
        // If only one node is null, or the values mismatch, they aren't the same
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        
        // Recursively check if the left subtrees and right subtrees match
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}