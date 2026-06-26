import java.util.PriorityQueue;
import java.util.HashSet;

class SmallestInfiniteSet {
    private int currentSmallest;
    private PriorityQueue<Integer> addedBackQueue;
    private HashSet<Integer> addedBackSet;

    public SmallestInfiniteSet() {
        this.currentSmallest = 1;
        this.addedBackQueue = new PriorityQueue<>();
        this.addedBackSet = new HashSet<>();
    }
    
    public int popSmallest() {
        // If there are elements that were added back, the smallest one will be in the heap
        if (!addedBackQueue.isEmpty()) {
            int smallest = addedBackQueue.poll();
            addedBackSet.remove(smallest);
            return smallest;
        }
        
        // Otherwise, return the threshold element and advance it
        int smallest = currentSmallest;
        currentSmallest++;
        return smallest;
    }
    
    public void addBack(int num) {
        // We only add it back if it's strictly smaller than our current threshold
        // and it hasn't already been added back.
        if (num < currentSmallest && !addedBackSet.contains(num)) {
            addedBackQueue.offer(num);
            addedBackSet.add(num);
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */