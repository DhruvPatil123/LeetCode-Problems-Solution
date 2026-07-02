import java.util.PriorityQueue;
import java.util.Collections;

class MedianFinder {
    private PriorityQueue<Integer> smallHalf; // Max-Heap
    private PriorityQueue<Integer> largeHalf; // Min-Heap

    public MedianFinder() {
        // Max-heap for the lower half of numbers
        smallHalf = new PriorityQueue<>(Collections.reverseOrder());
        // Min-heap for the upper half of numbers
        largeHalf = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // Step 1: Add to max-heap
        smallHalf.offer(num);
        
        // Step 2: Balancing step - ensure all elements in smallHalf <= largeHalf
        largeHalf.offer(smallHalf.poll());
        
        // Step 3: Maintain size constraint (smallHalf.size() >= largeHalf.size())
        if (smallHalf.size() < largeHalf.size()) {
            smallHalf.offer(largeHalf.poll());
        }
    }
    
    public double findMedian() {
        if (smallHalf.size() > largeHalf.size()) {
            return smallHalf.peek();
        }
        // Even number of elements: average the two middle components
        return (smallHalf.peek() + largeHalf.peek()) / 2.0;
    }
}