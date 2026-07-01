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
    // Tracks the minimum absolute difference found so far
    private int minDifference = Integer.MAX_VALUE;
    // Tracks the node visited immediately before the current node
    private TreeNode prevNode = null;

    public int getMinimumDifference(TreeNode root) {
        inorderTraversal(root);
        return minDifference;
    }

    private void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        // 1. Traverse the left subtree
        inorderTraversal(node.left);

        // 2. Process the current node
        if (prevNode != null) {
            // Since it's a BST, node.val is guaranteed to be >= prevNode.val
            minDifference = Math.min(minDifference, node.val - prevNode.val);
        }
        // Update the previous node pointer to the current node
        prevNode = node;

        // 3. Traverse the right subtree
        inorderTraversal(node.right);
    }
}