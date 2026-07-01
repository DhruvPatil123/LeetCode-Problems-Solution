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
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        
        Queue<TreeNode> queue = new LinkedList<>();
        // Seed the queue with the left and right children
        queue.add(root.left);
        queue.add(root.right);
        
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            
            // If both are null, this pair is symmetric; move to the next pair
            if (t1 == null && t2 == null) continue;
            // If only one is null or their values don't match, it's asymmetric
            if (t1 == null || t2 == null || t1.val != t2.val) return false;
            
            // Add next pairs to be compared in mirror order
            queue.add(t1.left);
            queue.add(t2.right); // t1.left matches with t2.right
            
            queue.add(t1.right);
            queue.add(t2.left);  // t1.right matches with t2.left
        }
        
        return true;
    }
}