import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> anagramGroups = new HashMap<>();
        
        for (String str : strs) {
            // Frequency array for lowercase English letters (26 characters)
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            
            // Convert the frequency array into a unique string key
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#'); // Delimiter to avoid ambiguous counting boundaries
                sb.append(count[i]);
            }
            String key = sb.toString();
            
            // Group the original string under its unique frequency key
            if (!anagramGroups.containsKey(key)) {
                anagramGroups.put(key, new ArrayList<>());
            }
            anagramGroups.get(key).add(str);
        }
        
        return new ArrayList<>(anagramGroups.values());
    }
}