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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);

        // If left and right heights match, it's a perfect binary tree
        if (leftHeight == rightHeight) {
            // Formula: (2^height) - 1. Using bitwise shift for speed: (1 << height) - 1
            return (1 << leftHeight) - 1;
        }

        // If they don't match, count the root (1) + left subtree + right subtree
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // Helper to calculate height of the leftmost path
    private int getLeftHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    // Helper to calculate height of the rightmost path
    private int getRightHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }
}