class Solution:
    # Change the 'O' and 'R' to lowercase so it matches the test runner
    def uniqueXorTriplets(self, nums: List[int]) -> int:
        n = len(nums)
        
        # Handle the special base cases described in the hints
        if n == 1:
            return 1
        if n == 2:
            return 2
        
        # For n >= 3, find the next power of 2 strictly greater than n.
        return 1 << n.bit_length()