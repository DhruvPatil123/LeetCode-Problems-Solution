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
    public int sumNumbers(TreeNode root) {
        // Start DFS traversal with an initial path value of 0
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentSum) {
        // Base case: if the node is null, it contributes 0 to the total sum
        if (node == null) {
            return 0;
        }

        // Calculate the number represented by the path up to the current node
        currentSum = currentSum * 10 + node.val;

        // If it's a leaf node, return the completed number
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // Recursively find the sum of paths in left and right subtrees
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}