import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String largestNumber(int[] nums) {
        // Convert the integer array into a String array
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        // Sort using the custom concatenation comparator in descending order
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                // Compare in reverse order to get descending sorting
                return order2.compareTo(order1);
            }
        });

        // Edge case: If the largest number is "0", the whole number is 0
        // (e.g., nums = [0, 0])
        if (strs[0].equals("0")) {
            return "0";
        }

        // Combine the sorted strings
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }

        return sb.toString();
    }
}