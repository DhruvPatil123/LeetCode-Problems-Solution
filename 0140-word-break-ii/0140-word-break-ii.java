import java.util.*;

class Solution {
    // Memoization map to store the valid sentences formed by a suffix string
    private Map<String, List<String>> memo = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        // Convert the list to a HashSet for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordDict);
        return backtrack(s, wordSet);
    }

    private List<String> backtrack(String s, Set<String> wordSet) {
        // If this suffix has already been computed, return the cached result
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> results = new ArrayList<>();

        // Base case: If the string is empty, return a list containing an empty string
        // to signify a successfully completed path
        if (s.isEmpty()) {
            results.add("");
            return results;
        }

        // Try breaking the string at every possible index
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);

            // If the current prefix is a valid word, process the remaining suffix
            if (wordSet.contains(prefix)) {
                String suffix = s.substring(i);
                List<String> suffixSubstrings = backtrack(suffix, wordSet);

                // Append the current prefix to each valid sentence formed by the suffix
                for (String sub : suffixSubstrings) {
                    if (sub.isEmpty()) {
                        results.add(prefix);
                    } else {
                        results.add(prefix + " " + sub);
                    }
                }
            }
        }

        // Cache the result for the current string before returning
        memo.put(s, results);
        return results;
    }
}