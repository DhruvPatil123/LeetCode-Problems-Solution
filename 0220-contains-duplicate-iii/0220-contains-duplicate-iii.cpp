class Solution {
public:
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int indexDiff, int valueDiff) {
        multiset<long long> window;
        
        for (int i = 0; i < nums.size(); ++i) {
            // Find the smallest element in the window that is >= nums[i] - valueDiff
            auto it = window.lower_bound((long long)nums[i] - valueDiff);
            
            // Check if that element is within nums[i] + valueDiff
            if (it != window.end() && *it <= (long long)nums[i] + valueDiff) {
                return true;
            }
            
            // Add current element to the window
            window.insert(nums[i]);
            
            // Maintain the window size to be at most indexDiff
            if (i >= indexDiff) {
                window.erase(window.find(nums[i - indexDiff]));
            }
        }
        
        return false;
    }
};