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
    public ListNode sortList(ListNode head) {
        // Base Case: If the list is empty or has only one element, it's already sorted
        if (head == null || head.next == null) {
            return head;
        }
        
        // 1. Split the list into two halves
        ListNode mid = getMid(head);
        ListNode nextToMid = mid.next;
        mid.next = null; // Break the link to create two standalone lists
        
        // 2. Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(nextToMid);
        
        // 3. Merge the sorted halves back together
        return merge(left, right);
    }
    
    // Helper function to find the middle node using slow and fast pointers
    private ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next; // Offsetting fast by 1 helps find the perfect left-middle
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    // Helper function to merge two sorted linked lists in-place
    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        
        // Append any remaining elements
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }
        
        return dummy.next;
    }
}