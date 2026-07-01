import java.util.ArrayList;
import java.util.List;

class Solution {
    
    // Define an optimized Trie Node structure
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // Holds the complete word at leaf level
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        
        // 1. Build the Trie
        TrieNode root = buildTrie(words);
        
        // 2. Step through every cell on the board to kick off DFS
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, result);
            }
        }
        
        return result;
    }
    
    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> result) {
        char ch = board[r][c];
        
        // Base case or Trie miss: if visited ('#') or character path doesn't exist in Trie
        if (ch == '#' || node.children[ch - 'a'] == null) {
            return;
        }
        
        TrieNode nextNode = node.children[ch - 'a'];
        
        // If we hit a leaf matching a word, add it to our results
        if (nextNode.word != null) {
            result.add(nextNode.word);
            nextNode.word = null; // Prevent duplicates without using a Set
        }
        
        // Mark the current cell as visited
        board[r][c] = '#';
        
        // Explore 4-directionally
        if (r > 0) dfs(board, r - 1, c, nextNode, result);
        if (r < board.length - 1) dfs(board, r + 1, c, nextNode, result);
        if (c > 0) dfs(board, r, c - 1, nextNode, result);
        if (c < board[0].length - 1) dfs(board, r, c + 1, nextNode, result);
        
        // Backtrack: restore the original character
        board[r][c] = ch;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode curr = root;
            for (char ch : w.toCharArray()) {
                int index = ch - 'a';
                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.word = w; // Save the full word at the final node
        }
        return root;
    }
}