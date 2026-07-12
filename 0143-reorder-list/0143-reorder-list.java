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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        // Step 1: Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Step 2: Reverse the second half of the list
        // slow is currently at the middle node
        ListNode prev = null;
        ListNode curr = slow;
        ListNode nextNode = null;
        
        while (curr != null) {
            nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        
        // Step 3: Interleave the two halves
        // first half starts at head; second half starts at prev (the new head of the reversed portion)
        ListNode first = head;
        ListNode second = prev;
        
        while (second.next != null) {
            // Keep track of the next nodes to process
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;
            
            // Re-route links to interleave
            first.next = second;
            second.next = tmp1;
            
            // Move pointers forward
            first = tmp1;
            second = tmp2;
        }
    }
}