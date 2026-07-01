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
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        
        while (curr != null) {
            // If the current node has a left child, we need to rewire it
            if (curr.left != null) {
                // Find the rightmost node in the left subtree (pre-order predecessor)
                TreeNode predecessor = curr.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                
                // Connect the predecessor's right to the current node's right subtree
                predecessor.right = curr.right;
                
                // Move the left subtree to become the right subtree
                curr.right = curr.left;
                curr.left = null;
            }
            
            // Move on to the next node on the right
            curr = curr.right;
        }
    }
}