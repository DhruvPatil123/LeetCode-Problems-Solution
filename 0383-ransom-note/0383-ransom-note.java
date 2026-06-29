class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        // Quick optimization: If the note requires more letters than available, it's impossible
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // Frequency array for lowercase English letters
        int[] charCounts = new int[26];

        // Count frequencies of each character in the magazine
        for (int i = 0; i < magazine.length(); i++) {
            charCounts[magazine.charAt(i) - 'a']++;
        }

        // Match the characters needed for the ransom note
        for (int i = 0; i < ransomNote.length(); i++) {
            int index = ransomNote.charAt(i) - 'a';
            charCounts[index]--;

            // If we ran out of this specific letter
            if (charCounts[index] < 0) {
                return false;
            }
        }

        return true;
    }
}