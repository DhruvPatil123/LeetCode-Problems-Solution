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
    public TreeNode sortedArrayToBST(int[] nums) {
        // Kick off the recursive helper with boundaries tracking the full array
        return constructBST(nums, 0, nums.length - 1);
    }
    
    private TreeNode constructBST(int[] nums, int left, int right) {
        // Base Case: If the left boundary exceeds the right boundary, 
        // there are no elements left to process.
        if (left > right) {
            return null;
        }
        
        // Find the middle element to serve as the root of this subtree
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        
        // Recursively build the left and right subtrees
        root.left = constructBST(nums, left, mid - 1);
        root.right = constructBST(nums, mid + 1, right);
        
        return root;
    }
}