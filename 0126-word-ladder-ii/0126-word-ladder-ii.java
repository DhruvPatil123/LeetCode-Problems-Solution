import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        
        if (!dict.contains(endWord)) {
            return result;
        }
        
        // Maps a word to its optimal parent nodes in the shortest path tree
        Map<String, List<String>> parentMap = new HashMap<>();
        Map<String, Integer> distanceMap = new HashMap<>();
        
        // 1. BFS step to compute shortest distances and reverse links
        bfs(beginWord, endWord, dict, parentMap, distanceMap);
        
        // 2. DFS step from endWord back to beginWord to collect valid paths
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(endWord, beginWord, parentMap, distanceMap, path, result);
        
        return result;
    }
    
    private void bfs(String beginWord, String endWord, Set<String> dict, 
                     Map<String, List<String>> parentMap, Map<String, Integer> distanceMap) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        distanceMap.put(beginWord, 0);
        
        boolean foundEnd = false;
        
        while (!queue.isEmpty() && !foundEnd) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                String currWord = queue.poll();
                int currDist = distanceMap.get(currWord);
                
                char[] wordChars = currWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        
                        wordChars[j] = c;
                        String nextWord = new String(wordChars);
                        
                        if (dict.contains(nextWord)) {
                            // First time discovering this word
                            if (!distanceMap.containsKey(nextWord)) {
                                distanceMap.put(nextWord, currDist + 1);
                                queue.add(nextWord);
                                parentMap.computeIfAbsent(nextWord, k -> new ArrayList<>()).add(currWord);
                                
                                if (nextWord.equals(endWord)) {
                                    foundEnd = true;
                                }
                            } 
                            // Word discovered via another equally short optimal path
                            else if (distanceMap.get(nextWord) == currDist + 1) {
                                parentMap.computeIfAbsent(nextWord, k -> new ArrayList<>()).add(currWord);
                            }
                        }
                    }
                    wordChars[j] = originalChar;
                }
            }
        }
    }
    
    private void dfs(String currWord, String beginWord, Map<String, List<String>> parentMap, 
                     Map<String, Integer> distanceMap, List<String> path, List<List<String>> result) {
        if (currWord.equals(beginWord)) {
            // Since we collected backwards, reverse the list to get front-to-back order
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            result.add(validPath);
            return;
        }
        
        if (!parentMap.containsKey(currWord)) {
            return;
        }
        
        for (String parent : parentMap.get(currWord)) {
            // Strictly follow optimal step decreases
            if (distanceMap.get(parent) == distanceMap.get(currWord) - 1) {
                path.add(parent);
                dfs(parent, beginWord, parentMap, distanceMap, path, result);
                path.remove(path.size() - 1); // Backtrack
            }
        }
    }
}