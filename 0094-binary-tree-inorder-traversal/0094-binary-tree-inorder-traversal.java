import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        // Continue running if we have active nodes or nodes left on the stack
        while (current != null || !stack.isEmpty()) {
            // 1. Travel down to the leftmost node of the current subtree
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // 2. Current is now null, meaning we pop the last visited node
            current = stack.pop();
            result.add(current.val); // Add the "Root"

            // 3. We've dealt with Left and Root; now turn to the Right subtree
            current = current.right;
        }

        return result;
    }
}