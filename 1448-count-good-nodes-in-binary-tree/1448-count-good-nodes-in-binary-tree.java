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
    public int goodNodes(TreeNode root) {
        // Start DFS from the root. The initial maximum value in the path is the root's value.
        return dfs(root, root.val);
    }

    private int dfs(TreeNode node, int maxSoFar) {
        // Base Case: If we reach an empty subtree, it contributes 0 good nodes
        if (node == null) {
            return 0;
        }

        int goodCount = 0;

        // If the current node's value is greater than or equal to the max value 
        // seen along the path so far, it is a "good node".
        if (node.val >= maxSoFar) {
            goodCount = 1;
            // Update the maximum value for the subsequent path
            maxSoFar = node.val;
        }

        // Add the good nodes found in both left and right subtrees
        goodCount += dfs(node.left, maxSoFar);
        goodCount += dfs(node.right, maxSoFar);

        return goodCount;
    }
}