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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }
        
        // Dummy node to easily handle left = 1
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Step 1: Reach the node right before the 'left' position
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        
        // Step 2: Initialize curr at the start of the sublist to be reversed
        ListNode curr = prev.next;
        
        // Step 3: Reverse the sublist link by link
        for (int i = 0; i < right - left; i++) {
            ListNode nextNode = curr.next;
            
            // Adjust pointers to shift nextNode to the front of the sublist
            curr.next = nextNode.next;
            nextNode.next = prev.next;
            prev.next = nextNode;
        }
        
        return dummy.next;
    }
}