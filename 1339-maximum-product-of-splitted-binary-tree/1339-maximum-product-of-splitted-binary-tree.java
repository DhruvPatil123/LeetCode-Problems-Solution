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
    private List<Long> allSubtreeSums = new ArrayList<>();

    public int maxProduct(TreeNode root) {
        // Step 1: Compute all subtree sums and find the absolute total sum
        long totalSum = calculateSubtreeSums(root);
        
        long maxProduct = 0;
        
        // Step 2: Iterate through all recorded subtree sums to find the maximum product
        for (long subtreeSum : allSubtreeSums) {
            long currentProduct = subtreeSum * (totalSum - subtreeSum);
            maxProduct = Math.max(maxProduct, currentProduct);
        }
        
        // Step 3: Return the maximized result modulo 10^9 + 7
        return (int) (maxProduct % 1_000_000_007);
    }

    private long calculateSubtreeSums(TreeNode node) {
        if (node == null) {
            return 0;
        }

        long leftSum = calculateSubtreeSums(node.left);
        long rightSum = calculateSubtreeSums(node.right);
        
        long currentSubtreeSum = node.val + leftSum + rightSum;
        
        // Store the sum of the subtree rooted at the current node
        allSubtreeSums.add(currentSubtreeSum);
        
        return currentSubtreeSum;
    }
}