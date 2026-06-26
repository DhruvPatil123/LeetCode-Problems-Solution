class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        
        // Base cases: costs to step 0 and step 1 are 0 because we can start there
        int downTwo = 0;
        int downOne = 0;
        
        // Iteratively compute the minimum cost for each step up to 'n'
        for (int i = 2; i <= n; i++) {
            int currentCost = Math.min(downOne + cost[i - 1], downTwo + cost[i - 2]);
            
            // Shift our window forward
            downTwo = downOne;
            downOne = currentCost;
        }
        
        return downOne;
    }
}