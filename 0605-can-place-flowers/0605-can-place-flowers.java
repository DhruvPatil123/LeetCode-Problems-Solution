class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // If no flowers need to be planted, it's always possible
        if (n == 0) {
            return true;
        }

        for (int i = 0; i < flowerbed.length; i++) {
            // Check if current, previous, and next plots are empty
            if (flowerbed[i] == 0) {
                boolean leftEmpty = (i == 0 || flowerbed[i - 1] == 0);
                boolean rightEmpty = (i == flowerbed.length - 1 || flowerbed[i + 1] == 0);

                if (leftEmpty && rightEmpty) {
                    flowerbed[i] = 1; // Plant the flower
                    n--; // One less flower left to plant
                    
                    if (n == 0) {
                        return true;
                    }
                }
            }
        }

        return n <= 0;
    }
}