class Solution:
    def maxProduct(self, nums: list[int]) -> int:
        max1 = 0  # The largest number
        max2 = 0  # The second largest number
        
        for num in nums:
            if num > max1:
                # Current max1 becomes the new max2
                max2 = max1
                # Update max1 to the new highest number
                max1 = num
            elif num > max2:
                # Update max2 if num is less than max1 but greater than current max2
                max2 = num
                
        return (max1 - 1) * (max2 - 1)