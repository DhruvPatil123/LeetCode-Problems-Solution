class Solution {
public:
    vector<vector<int>> getSkyline(vector<vector<int>>& buildings) {
        vector<pair<int, int>> events;
        
        // Step 1: Create start and end events
        for (const auto& b : buildings) {
            events.push_back({b[0], -b[2]}); // Start event with negative height
            events.push_back({b[1], b[2]});  // End event with positive height
        }
        
        // Step 2: Sort events
        sort(events.begin(), events.end());
        
        vector<vector<int>> result;
        multiset<int> activeHeights = {0}; // Track current active heights, initialized with ground (0)
        int prevMaxHeight = 0;
        
        // Step 3: Process each event
        for (const auto& event : events) {
            int x = event.first;
            int height = event.second;
            
            if (height < 0) {
                // Start of a building -> insert height (make it positive)
                activeHeights.insert(-height);
            } else {
                // End of a building -> remove one instance of height
                activeHeights.erase(activeHeights.find(height));
            }
            
            // Current max height among all active buildings
            int currentMaxHeight = *activeHeights.rbegin();
            
            // If the max height changes, we found a key point
            if (currentMaxHeight != prevMaxHeight) {
                result.push_back({x, currentMaxHeight});
                prevMaxHeight = currentMaxHeight;
            }
        }
        
        return result;
    }
};