public class Solution {
    // Array to store the precomputed set bits for numbers 0 to 255
    private final int[] bitCache = new int[256];
    private boolean isCacheInitialized = false;

    private void initializeCache() {
        for (int i = 0; i < 256; i++) {
            bitCache[i] = (i & 1) + bitCache[i >>> 1];
        }
        isCacheInitialized = true;
    }

    public int hammingWeight(int n) {
        // Initialize the lookup table only once on the first call
        if (!isCacheInitialized) {
            initializeCache();
        }

        // Break the 32-bit integer into 4 chunks of 8-bits (1 byte each)
        return bitCache[n & 0xFF] +
               bitCache[(n >>> 8) & 0xFF] +
               bitCache[(n >>> 16) & 0xFF] +
               bitCache[(n >>> 24) & 0xFF];
    }
}