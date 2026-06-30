import java.util.Stack;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentResult = 0;
        int currentNumber = 0;
        int sign = 1; // 1 means '+', -1 means '-'

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                // Form the multi-digit number
                currentNumber = currentNumber * 10 + (ch - '0');
            } else if (ch == '+') {
                // Apply the previous number to the result before moving to the next sign
                currentResult += sign * currentNumber;
                currentNumber = 0;
                sign = 1;
            } else if (ch == '-') {
                currentResult += sign * currentNumber;
                currentNumber = 0;
                sign = -1;
            } else if (ch == '(') {
                // Push the current result and sign onto the stack for later
                stack.push(currentResult);
                stack.push(sign);
                
                // Reset for the inner sub-expression
                currentResult = 0;
                sign = 1;
            } else if (ch == ')') {
                // Add the last number inside the parentheses to the inner result
                currentResult += sign * currentNumber;
                currentNumber = 0;

                // Pop the sign applied to this sub-expression context
                int groupSign = stack.pop();
                currentResult *= groupSign;

                // Pop the previous result before the parenthesis context started and add it
                int historicalResult = stack.pop();
                currentResult += historicalResult;
            }
        }

        // Add any remaining number at the end of the string
        if (currentNumber != 0) {
            currentResult += sign * currentNumber;
        }

        return currentResult;
    }
}