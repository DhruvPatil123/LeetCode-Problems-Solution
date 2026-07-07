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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        
        // If one of the subtrees is empty, we must take the path of the non-empty subtree
        if (root.left == null || root.right == null) {
            return left + right + 1; // Since one side is 0, this adds the non-zero side + 1
        }
        
        // If both subtrees exist, take the minimum of both paths
        return Math.min(left, right) + 1;
    }
}