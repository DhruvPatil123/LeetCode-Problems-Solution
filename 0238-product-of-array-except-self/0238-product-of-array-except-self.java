class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        
        // Step 1: Calculate the prefix products.
        // answer[i] will store the product of all elements to the left of i.
        answer[0] = 1; // There are no elements to the left of index 0
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        
        // Step 2: Calculate the suffix products on the fly and multiply them.
        // 'suffixProduct' tracks the accumulated product of all elements to the right of i.
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * suffixProduct;
            suffixProduct *= nums[i]; // Update the suffix product for the next element to the left
        }
        
        return answer;
    }
}