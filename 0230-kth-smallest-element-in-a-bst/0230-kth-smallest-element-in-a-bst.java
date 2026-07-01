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
import java.util.Stack;

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        int count = 0;
        
        while (curr != null || !stack.isEmpty()) {
            // 1. Go as deep left as possible
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            // 2. Process the current node
            curr = stack.pop();
            count++;
            
            // If we have hit the k-th smallest element, return it
            if (count == k) {
                return curr.val;
            }
            
            // 3. Move to the right subtree
            curr = curr.right;
        }
        
        return -1; // Fallback statement
    }
}