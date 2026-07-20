class Solution:
    def shiftGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        total_elements = m * n
        
        # Optimize k if it's larger than the total number of elements
        k = k % total_elements
        
        # Initialize the result grid with zeros
        result = [[0] * n for _ in range(m)]
        
        for i in range(m):
            for j in range(n):
                # Convert 2D coordinate to a 1D index
                flat_index = i * n + j
                
                # Calculate the new 1D position after shift
                new_flat_index = (flat_index + k) % total_elements
                
                # Convert the new 1D index back into 2D coordinates
                new_r = new_flat_index // n
                new_c = new_flat_index % n
                
                result[new_r][new_c] = grid[i][j]
                
        return result