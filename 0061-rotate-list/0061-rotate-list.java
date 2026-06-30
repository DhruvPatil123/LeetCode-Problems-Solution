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
    public ListNode rotateRight(ListNode head, int k) {
        // Base cases: empty list, single element, or no rotation needed
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        // Step 1: Find the length of the list and the tail node
        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        
        // Step 2: Normalize k
        k = k % len;
        if (k == 0) {
            return head; // No effective rotation needed
        }
        
        // Step 3: Link tail to head to form a circular list
        tail.next = head;
        
        // Step 4: Find the new tail node, which is at (len - k) steps from head
        ListNode newTail = head;
        for (int i = 1; i < len - k; i++) {
            newTail = newTail.next;
        }
        
        // Step 5: Break the circle and set the new head
        ListNode newHead = newTail.next;
        newTail.next = null;
        
        return newHead;
    }
}