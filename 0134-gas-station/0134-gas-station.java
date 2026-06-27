class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;   // Tracks net gas across the entire trip
        int currentTank = 0; // Tracks gas in the tank for the current trial run
        int startingStation = 0;
        
        for (int i = 0; i < gas.length; i++) {
            int netGas = gas[i] - cost[i];
            totalTank += netGas;
            currentTank += netGas;
            
            // If the current tank goes negative, station i cannot be reached.
            // Reset the trial run starting from the next station (i + 1).
            if (currentTank < 0) {
                startingStation = i + 1;
                currentTank = 0; // Reset fuel gauge for the new starting point
            }
        }
        
        // If total tank is negative, a full loop is impossible
        return totalTank >= 0 ? startingStation : -1;
    }
}