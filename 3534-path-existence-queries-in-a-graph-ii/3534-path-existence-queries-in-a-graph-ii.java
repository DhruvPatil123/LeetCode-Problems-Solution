import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Pair structure to keep track of the original index after sorting
        int[][] sortedNodes = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedNodes[i][0] = nums[i];
            sortedNodes[i][1] = i;
        }
        
        // Sort primarily by value
        Arrays.sort(sortedNodes, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Map from original index to sorted index position
        int[] originalToSorted = new int[n];
        for (int i = 0; i < n; i++) {
            originalToSorted[sortedNodes[i][1]] = i;
        }
        
        // Binary lifting table dimensions
        int LOG = 18; // Since n <= 10^5, 2^17 = 131072 > 10^5
        int[][] up = new int[n][LOG];
        
        // Compute the greedy immediate step for each sorted node
        int right = 0;
        for (int left = 0; left < n; left++) {
            while (right < n && sortedNodes[right][0] - sortedNodes[left][0] <= maxDiff) {
                right++;
            }
            // The furthest node left can jump to is right - 1
            up[left][0] = right - 1;
        }
        
        // Fill the binary lifting sparse table
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }
        
        int numQueries = queries.length;
        int[] answer = new int[numQueries];
        
        for (int q = 0; q < numQueries; q++) {
            int uOriginal = queries[q][0];
            int vOriginal = queries[q][1];
            
            if (uOriginal == vOriginal) {
                answer[q] = 0;
                continue;
            }
            
            int u = originalToSorted[uOriginal];
            int v = originalToSorted[vOriginal];
            
            // Ensure u is always the smaller element value-wise
            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }
            
            // Check if v is fundamentally reachable from u by doing the max possible jump
            int current = u;
            for (int j = LOG - 1; j >= 0; j--) {
                current = up[current][j];
            }
            // If even after max-jumping we cannot cross or reach v, they are disconnected
            if (current < v) {
                answer[q] = -1;
                continue;
            }
            
            // Count the minimum number of steps to reach or pass v
            int steps = 0;
            current = u;
            for (int j = LOG - 1; j >= 0; j--) {
                if (up[current][j] < v) {
                    steps += (1 << j);
                    current = up[current][j];
                }
            }
            
            // One final step is required to bridge over or exactly hit v
            answer[q] = steps + 1;
        }
        
        return answer;
    }
}