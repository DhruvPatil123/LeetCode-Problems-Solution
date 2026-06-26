import java.util.Stack;

class StockSpanner {
    
    // The stack will store integer arrays where:
    // element[0] = price of the stock
    // element[1] = accumulated span of that price
    private Stack<int[]> stack;

    public StockSpanner() {
        this.stack = new Stack<>();
    }
    
    public int next(int price) {
        // Every day starts with an initial span of 1 (the day itself)
        int currentSpan = 1;
        
        // Pop elements from the stack while the top price is less than or equal to the current price
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            // Accumulate the span of the popped elements
            currentSpan += stack.pop()[1];
        }
        
        // Push the current price and its calculated span onto the stack
        stack.push(new int[]{price, currentSpan});
        
        return currentSpan;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */