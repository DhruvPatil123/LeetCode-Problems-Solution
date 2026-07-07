/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    // Global pointer to track our current position in the linked list
    private ListNode currentHead;

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        // Step 1: Count the total number of nodes in the linked list
        int size = countNodes(head);
        this.currentHead = head;

        // Step 2: Construct the tree using simulated inorder traversal
        return convertListToBST(0, size - 1);
    }

    private int countNodes(ListNode head) {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    private TreeNode convertListToBST(int start, int end) {
        // Base case: no elements left in the current range
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        // 1. Recursively construct the left subtree
        TreeNode leftChild = convertListToBST(start, mid - 1);

        // 2. Process the current root node
        TreeNode root = new TreeNode(this.currentHead.val);
        root.left = leftChild;

        // Advance the global linked list pointer to the next element
        this.currentHead = this.currentHead.next;

        // 3. Recursively construct the right subtree
        root.right = convertListToBST(mid + 1, end);

        return root;
    }
}