class Solution {
    public boolean isBalanced(TreeNode root) {
        // If checkHeight returns -1, the tree is unbalanced
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        // Base case: an empty tree has a height of 0
        if (node == null) {
            return 0;
        }

        // 1. Check the height of the left subtree
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1; // Left subtree is already unbalanced
        }

        // 2. Check the height of the right subtree
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1; // Right subtree is already unbalanced
        }

        // 3. Check if the current node violates the balance condition
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; 
        }

        // Return the actual height of this node's subtree
        return Math.max(leftHeight, rightHeight) + 1;
    }
}