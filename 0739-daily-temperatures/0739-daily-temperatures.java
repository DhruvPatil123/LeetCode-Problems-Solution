import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        
        // Monotonic stack to store indices of temperatures
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            // While the stack is not empty and today's temperature is warmer
            // than the temperature at the index on top of the stack
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                // Number of days to wait is the difference between indices
                answer[prevIndex] = i - prevIndex;
            }
            // Push the current day's index onto the stack
            stack.push(i);
        }
        
        return answer;
    }
}