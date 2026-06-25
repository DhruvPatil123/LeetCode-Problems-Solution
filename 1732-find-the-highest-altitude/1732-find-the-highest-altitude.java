class Solution {
    public int largestAltitude(int[] gain) {
        int altitude = 0, maxAltitude = 0;
        for (int g : gain) {
            altitude += g;
            if (altitude > maxAltitude) maxAltitude = altitude;
        }
        return maxAltitude;
    }
}