class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode pred = null; // Tracks the previously visited node in inorder sequence
        
        TreeNode curr = root;
        
        while (curr != null) {
            if (curr.left == null) {
                // Process the current node
                if (pred != null && pred.val > curr.val) {
                    if (first == null) first = pred;
                    second = curr;
                }
                pred = curr;
                
                // Move to right child
                curr = curr.right;
            } else {
                // Find the predecessor of curr (rightmost node in left subtree)
                TreeNode predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }
                
                if (predecessor.right == null) {
                    // Create a temporary thread to the root
                    predecessor.right = curr;
                    curr = curr.left;
                } else {
                    // Thread already exists; break it to restore original tree structure
                    predecessor.right = null;
                    
                    // Process the current node
                    if (pred != null && pred.val > curr.val) {
                        if (first == null) first = pred;
                        second = curr;
                    }
                    pred = curr;
                    
                    // Move to right child
                    curr = curr.right;
                }
            }
        }
        
        // Correct the tree by swapping the values of the two identified nodes
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
}