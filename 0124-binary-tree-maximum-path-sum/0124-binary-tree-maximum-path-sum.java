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
    private int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        gainFromSubtree(root);
        return maxSum;
    }

    private int gainFromSubtree(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively compute the max path sum branch contributions from left and right subtrees.
        // If a subtree path sum is negative, we drop it by taking Math.max(..., 0).
        int leftGain = Math.max(gainFromSubtree(node.left), 0);
        int rightGain = Math.max(gainFromSubtree(node.right), 0);

        // Price of a new path with the current node as the highest turning point
        int currentPathSum = node.val + leftGain + rightGain;

        // Update the global maximum path sum found so far
        maxSum = Math.max(maxSum, currentPathSum);

        // Return the max branch sum this node can contribute to its parent
        return node.val + Math.max(leftGain, rightGain);
    }
}