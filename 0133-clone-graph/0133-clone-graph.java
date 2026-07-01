/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

class Solution {
    // Map to keep track of already cloned nodes to avoid cycles
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // If the node has already been cloned, return its clone from the map
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Instantiate a clone for the current node (neighbors list starts empty)
        Node cloneNode = new Node(node.val, new ArrayList<>());
        // Register it immediately to break circular dependencies/cycles
        visited.put(node, cloneNode);

        // Recursively clone all neighbors
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }
}