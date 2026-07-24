class Solution:
    def uniqueXorTriplets(self, nums: list[int]) -> int:
        # Step 1: Remove duplicates as they don't contribute to new unique XOR values
        unique_nums = set(nums)
        
        # Step 2: Start with 0 (identity for XOR)
        current_xors = {0}
        
        # Step 3: Expand 3 times to simulate picking 3 elements (i, j, k)
        for _ in range(3):
            # Generate new XOR combinations
            current_xors = {
                curr ^ num 
                for curr in current_xors 
                for num in unique_nums
            }
            
        # Step 4: The size of the set is our final answer
        return len(current_xors)