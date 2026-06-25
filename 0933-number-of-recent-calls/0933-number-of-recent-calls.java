import java.util.LinkedList;
import java.util.Queue;

class RecentCounter {
    private Queue<Integer> queue;

    public RecentCounter() {
        // Initialize the queue to store timestamps
        this.queue = new LinkedList<>();
    }
    
    public int ping(int t) {
        // Step 1: Add the current timestamp to the queue
        queue.add(t);
        
        // Step 2: Remove any timestamps that are older than t - 3000
        while (!queue.isEmpty() && queue.peek() < t - 3000) {
            queue.poll();
        }
        
        // Step 3: The remaining items in the queue are within the valid window
        return queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */