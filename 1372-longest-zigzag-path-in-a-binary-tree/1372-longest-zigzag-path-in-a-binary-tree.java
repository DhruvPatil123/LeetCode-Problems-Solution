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
    private int maxPath = 0;

    public int longestZigZag(TreeNode root) {
        if (root == null) return 0;
        
        // Start DFS going left and right from the root node.
        // Initial length is 0 because we haven't made a step yet.
        // Let's define: true = came from left / heading right next, false = came from right / heading left next.
        dfs(root.left, true, 1);
        dfs(root.right, false, 1);
        
        return maxPath;
    }

    private void dfs(TreeNode node, boolean goRight, int currentLength) {
        if (node == null) return;

        // Update the maximum ZigZag length found so far
        maxPath = Math.max(maxPath, currentLength);

        if (goRight) {
            // Pattern matches: We needed to go right, so length increases
            dfs(node.right, false, currentLength + 1);
            // Pattern breaks: We went left instead, reset path length to 1
            dfs(node.left, true, 1);
        } else {
            // Pattern matches: We needed to go left, so length increases
            dfs(node.left, true, currentLength + 1);
            // Pattern breaks: We went right instead, reset path length to 1
            dfs(node.right, false, 1);
        }
    }
}