import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        for (int ast : asteroids) {
            boolean exploded = false;
            
            // Collision happens ONLY if top of stack goes right (+) and current goes left (-)
            while (!stack.isEmpty() && stack.peek() > 0 && ast < 0) {
                if (Math.abs(stack.peek()) < Math.abs(ast)) {
                    // Top asteroid is smaller; it explodes. Pop it and keep checking.
                    stack.pop();
                    continue;
                } else if (Math.abs(stack.peek()) == Math.abs(ast)) {
                    // Both are same size; both explode.
                    stack.pop();
                    exploded = true;
                    break;
                } else {
                    // Top asteroid is larger; current one explodes.
                    exploded = true;
                    break;
                }
            }
            
            // If the current asteroid didn't explode, push it to the stack
            if (!exploded) {
                stack.push(ast);
            }
        }
        
        // Convert the stack back to an array
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        
        return result;
    }
}