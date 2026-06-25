import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        
        // Step 1: Separate the senators into their respective queues by initial turn index
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.add(i);
            } else {
                dire.add(i);
            }
        }
        
        // Step 2: Simulate the round-based ban procedure
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int rIndex = radiant.poll();
            int dIndex = dire.poll();
            
            // The senator with the smaller index acts first and bans the other
            if (rIndex < dIndex) {
                // Radiant wins this matchup; re-enqueue for the next round
                radiant.add(rIndex + n);
            } else {
                // Dire wins this matchup; re-enqueue for the next round
                dire.add(dIndex + n);
            }
        }
        
        // Step 3: Determine the winner based on which queue still has active senators
        return radiant.isEmpty() ? "Dire" : "Radiant";
    }
}