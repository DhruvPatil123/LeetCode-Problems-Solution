/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        // Start with the root level
        Node curr = root; 
        
        while (curr != null) {
            // Dummy node acts as a placeholder before the start of the next level
            Node dummyHead = new Node(0);
            // 'prev' tracks the last processed node on the next level
            Node prev = dummyHead; 
            
            // Traverse the current level using its established 'next' pointers
            while (curr != null) {
                if (curr.left != null) {
                    prev.next = curr.left;
                    prev = prev.next;
                }
                if (curr.right != null) {
                    prev.next = curr.right;
                    prev = prev.next;
                }
                // Move to the next node in the current level
                curr = curr.next;
            }
            
            // Move down to the beginning of the newly formed level
            curr = dummyHead.next;
        }
        
        return root;
    }
}