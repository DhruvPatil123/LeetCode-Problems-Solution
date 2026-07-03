class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        // Clear the rightmost set bit of 'right' until it is <= left
        while (right > left) {
            right = right & (right - 1);
        }
        
        return right;
    }
}