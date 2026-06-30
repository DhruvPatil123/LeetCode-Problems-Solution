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
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // Sentinel/Dummy node to easily handle head deletions
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy; // Tracks the last guaranteed distinct node
        
        while (head != null) {
            // If it's a start of a duplicate sublist
            if (head.next != null && head.val == head.next.val) {
                // Skip all nodes that have the same value
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // Link the last distinct node to the node after the duplicates
                prev.next = head.next;
            } else {
                // No duplicate found for this node, move prev forward
                prev = prev.next;
            }
            // Move head forward for the next iteration
            head = head.next;
        }
        
        return dummy.next;
    }
}