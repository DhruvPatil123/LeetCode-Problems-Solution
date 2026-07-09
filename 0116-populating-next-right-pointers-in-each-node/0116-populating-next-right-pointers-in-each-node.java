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
        
        // Start with the root node. The level below it will be connected.
        Node leftmost = root;
        
        // While there is a next level to connect
        while (leftmost.left != null) {
            Node curr = leftmost;
            
            // Traverse the current level to connect the children of the next level
            while (curr != null) {
                // Connection 1: Connect left child to right child
                curr.left.next = curr.right;
                
                // Connection 2: Connect right child to the neighbor's left child
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                
                // Move to the next node on the same level
                curr = curr.next;
            }
            
            // Move down to the next level
            leftmost = leftmost.left;
        }
        
        return root;
    }
}