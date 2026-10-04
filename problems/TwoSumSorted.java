package problems;

import java.util.Arrays;

/**
 * Problem: Two Sum II - Input Array Is Sorted (LeetCode 167)
 * 
 * Target: Senior Software Engineering Interview Practice
 * Pattern: Two Pointers (Opposite-End Convergence)
 * 
 * 1. Restate & Constraints:
 *    - Given a 1-indexed array of integers 'numbers' already sorted in non-decreasing order.
 *    - Find two numbers such that they add up to a specific 'target'.
 *    - Return the indices of the two numbers, added by one (1-indexed), as an integer array [index1, index2].
 *    - Constraints: Exactly one valid solution exists; cannot use the same element twice.
 *    - Extra Space: Constant O(1) space required.
 * 
 * 2. Invariant & Mental Model:
 *    - Maintain two pointers: 'left' at index 0, 'right' at index n - 1.
 *    - Current sum = numbers[left] + numbers[right].
 *    - If sum == target: found the solution.
 *    - If sum < target: because array is sorted, any pair with numbers[left] and elements to the
 *      left of 'right' will produce an even smaller sum. Thus, numbers[left] can NEVER be part of
 *      the answer with any remaining candidate. We safely discard 'left' by incrementing: left++.
 *    - If sum > target: symmetrically, numbers[right] cannot be paired with any remaining element.
 *      Discard 'right' by decrementing: right--.
 *    - Every step reduces the search space by 1 element monotonically.
 * 
 * 3. Complexity:
 *    - Time: O(N) single pass.
 *    - Space: O(1) no extra heap allocation.
 */
public class TwoSumSorted {

    public int[] twoSum(int[] numbers, int target) {
        // Handle invalid inputs defensively when the problem contract permits it
        if (numbers == null || numbers.length < 2) {
            return new int[]{};
        }

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            // Note on potential integer overflow:
            // If numbers[left] and numbers[right] can be large, using long for sum is defensive.
            long currentSum = (long) numbers[left] + numbers[right];

            if (currentSum == target) {
                // Problem requires 1-based indexing
                return new int[]{left + 1, right + 1};
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }

        // Guaranteed one solution per problem constraints, but return empty array if none found
        return new int[]{};
    }

    // ==========================================
    // Verification Test Harness (Self-Testing)
    // ==========================================
    public static void main(String[] args) {
        System.out.println("Running TwoSumSorted verification tests...");
        TwoSumSorted solver = new TwoSumSorted();

        // Test 1: Standard case
        int[] res1 = solver.twoSum(new int[]{2, 7, 11, 15}, 9);
        assert Arrays.equals(res1, new int[]{1, 2}) : "Test 1 Failed: Expected [1, 2], got " + Arrays.toString(res1);

        // Test 2: Negative numbers
        int[] res2 = solver.twoSum(new int[]{-5, -2, 1, 4, 9}, -1); // -5 + 4 = -1 -> indices [1, 4]
        assert Arrays.equals(res2, new int[]{1, 4}) : "Test 2 Failed: Expected [1, 4], got " + Arrays.toString(res2);

        // Test 3: Duplicates
        int[] res3 = solver.twoSum(new int[]{0, 0, 3, 4}, 0);
        assert Arrays.equals(res3, new int[]{1, 2}) : "Test 3 Failed: Expected [1, 2], got " + Arrays.toString(res3);

        // Test 4: Two elements boundary
        int[] res4 = solver.twoSum(new int[]{-1, 0}, -1);
        assert Arrays.equals(res4, new int[]{1, 2}) : "Test 4 Failed: Expected [1, 2], got " + Arrays.toString(res4);

        System.out.println("✅ All TwoSumSorted assertions passed cleanly!");
    }
}
