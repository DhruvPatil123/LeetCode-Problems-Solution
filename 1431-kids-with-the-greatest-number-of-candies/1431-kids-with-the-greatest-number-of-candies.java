import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;
        
        // Step 1: Find the maximum candies any kid currently has
        for (int candy : candies) {
            if (candy > maxCandies) {
                maxCandies = candy;
            }
        }
        
        List<Boolean> result = new ArrayList<>();
        
        // Step 2: Check if adding extraCandies makes the current kid's total >= maxCandies
        for (int candy : candies) {
            result.add(candy + extraCandies >= maxCandies);
        }
        
        return result;
    }
}