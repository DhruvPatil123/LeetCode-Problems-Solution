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
    public ListNode removeElements(ListNode head, int val) {
        // Base case: if the list is empty
        if (head == null) return null;
        
        // Recursively process the rest of the list
        head.next = removeElements(head.next, val);
        
        // If the current head matches the value, return its next node instead
        return head.val == val ? head.next : head;
    }
}