class Solution:
    def maxProduct(self, n: int) -> int:
        # Convert integer to a list of digits, sort in descending order
        digits = sorted([int(d) for d in str(n)], reverse=True)
        
        # Multiply the two largest digits
        return digits[0] * digits[1]