import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        long[] prefixGcd = new long[n];
        
        long currentMax = 0;
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            prefixGcd[i] = gcd(nums[i], currentMax);
        }
        
        // Sort in non-decreasing order
        Arrays.sort(prefixGcd);
        
        long totalSum = 0;
        int left = 0;
        int right = n - 1;
        
        // Two-pointer approach to pair the smallest and largest elements
        while (left < right) {
            totalSum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return totalSum;
    }
    
    // Helper method to calculate Greatest Common Divisor using Euclidean algorithm
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}