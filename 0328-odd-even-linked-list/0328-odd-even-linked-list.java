/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        // Edge case: If the list is empty or has only one node, return as is
        if (head == null || head.next == null) {
            return head;
        }
        
        // Heads of the two lists
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even; // Save the starting point of the even list
        
        // Loop runs until we run out of even nodes or the last even node's next pointer
        while (even != null && even.next != null) {
            odd.next = even.next;     // Connect current odd to the next odd node
            odd = odd.next;           // Move odd pointer forward
            
            even.next = odd.next;     // Connect current even to the next even node
            even = even.next;         // Move even pointer forward
        }
        
        // Connect the end of the odd list to the head of the even list
        odd.next = evenHead;
        
        return head;
    }
}