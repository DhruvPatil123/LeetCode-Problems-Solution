import java.util.Stack;

class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] result = new int[n];
        // Stack will store the indices (or values) of people to the right
        Stack<Integer> stack = new Stack<>();
        
        // Iterate from right to left
        for (int i = n - 1; i >= 0; i--) {
            int visibleCount = 0;
            
            // Pop all elements from the stack that are shorter than the current person
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.pop();
                visibleCount++; // Current person can see this shorter person
            }
            
            // If the stack is not empty, the person at the top is taller than heights[i]
            // Current person can see this taller person, but no one behind them
            if (!stack.isEmpty()) {
                visibleCount++;
            }
            
            // Record the answer for person i
            result[i] = visibleCount;
            
            // Push the current person's height onto the stack
            stack.push(heights[i]);
        }
        
        return result;
    }
}