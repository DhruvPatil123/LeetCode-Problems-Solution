class Solution {
private:
    int robLinear(const vector<int>& nums, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;
        
        for (int i = start; i <= end; ++i) {
            int current = max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }

public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return nums[0];
        
        // Case 1: Exclude the last house (0 to n-2)
        int robFirst = robLinear(nums, 0, n - 2);
        
        // Case 2: Exclude the first house (1 to n-1)
        int robLast = robLinear(nums, 1, n - 1);
        
        return max(robFirst, robLast);
    }
};