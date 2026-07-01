class WordDictionary {

    // Define the Trie Node structure
    private static class TrieNode {
        private TrieNode[] children;
        private boolean isEndOfWord;

        public TrieNode() {
            // 26 English lowercase letters
            this.children = new TrieNode[26];
            this.isEndOfWord = false;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }
    
    /** Returns true if there is any string in the data structure that matches word. */
    public boolean search(String word) {
        return dfsSearch(word, 0, root);
    }

    private boolean dfsSearch(String word, int index, TrieNode curr) {
        // Base Case: If we've matched all characters, check if it forms a complete word
        if (index == word.length()) {
            return curr.isEndOfWord;
        }

        char ch = word.charAt(index);

        if (ch == '.') {
            // Wildcard match: Explore all possible paths at the current level
            for (int i = 0; i < 26; i++) {
                if (curr.children[i] != null) {
                    if (dfsSearch(word, index + 1, curr.children[i])) {
                        return true;
                    }
                }
            }
            return false; // No path matched the wildcard sequence
        } else {
            // Exact match: Standard trie traversal step
            int childIndex = ch - 'a';
            if (curr.children[childIndex] == null) {
                return false;
            }
            return dfsSearch(word, index + 1, curr.children[childIndex]);
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */