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
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(TreeNode node, int remainingSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        // Include the current node in our tracking path
        currentPath.add(node.val);

        // Check if it's a leaf node and if the path matches targetSum
        if (node.left == null && node.right == null && remainingSum == node.val) {
            result.add(new ArrayList<>(currentPath)); // Snapshot copy of the valid path
        } else {
            // Continue exploring subtrees with the updated remaining sum
            backtrack(node.left, remainingSum - node.val, currentPath, result);
            backtrack(node.right, remainingSum - node.val, currentPath, result);
        }

        // Backtrack: remove the current node before popping out of this recursive frame
        currentPath.remove(currentPath.size() - 1);
    }
}