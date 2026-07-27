class Solution:
    def maximumProduct(self, nums: list[int]) -> int:
        # Initialize the top 3 maximums
        max1 = float('-inf') # Largest
        max2 = float('-inf') # Second largest
        max3 = float('-inf') # Third largest
        
        # Initialize the top 2 minimums
        min1 = float('inf')  # Smallest
        min2 = float('inf')  # Second smallest
        
        for n in nums:
            # Update minimums
            if n <= min1:
                min2 = min1
                min1 = n
            elif n <= min2:
                min2 = n
                
            # Update maximums
            if n >= max1:
                max3 = max2
                max2 = max1
                max1 = n
            elif n >= max2:
                max3 = max2
                max2 = n
            elif n >= max3:
                max3 = n
                
        # Compare the two scenarios and return the maximum
        return max(min1 * min2 * max1, max1 * max2 * max3)