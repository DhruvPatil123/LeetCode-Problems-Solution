import java.util.*;

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        // Convert the bank into a HashSet for O(1) lookups
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        
        // If the end gene isn't even in the bank, it's an impossible mutation
        if (!bankSet.contains(endGene)) {
            return -1;
        }
        
        // Character choices allowed for a valid gene mutation
        char[] genes = {'A', 'C', 'G', 'T'};
        
        // BFS Setup
        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);
        
        // Track visited genes so we don't get stuck in cycles
        Set<String> visited = new HashSet<>();
        visited.add(startGene);
        
        int mutations = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all gene strings at the current mutation level
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                
                // If we've reached the target gene string, return the step count
                if (curr.equals(endGene)) {
                    return mutations;
                }
                
                char[] currArray = curr.toCharArray();
                // Try mutating every position (0 to 7) of the 8-char gene string
                for (int j = 0; j < 8; j++) {
                    char oldChar = currArray[j];
                    
                    for (char g : genes) {
                        if (g == oldChar) continue; // Skip mutating to the same character
                        
                        currArray[j] = g;
                        String mutatedString = new String(currArray);
                        
                        // If it's a valid mutation and hasn't been visited yet
                        if (bankSet.contains(mutatedString) && !visited.contains(mutatedString)) {
                            visited.add(mutatedString);
                            queue.offer(mutatedString);
                        }
                    }
                    // Revert the character back for the next position's mutation attempts
                    currArray[j] = oldChar;
                }
            }
            mutations++; // Advance to the next level of mutations
        }
        
        return -1; // Unable to reach the target gene string
    }
}