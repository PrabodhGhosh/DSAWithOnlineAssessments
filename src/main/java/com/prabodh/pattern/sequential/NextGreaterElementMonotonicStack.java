package com.prabodh.pattern.sequential;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Pure Monotonic Stack Pattern: Next Greater Element I.
 * * Mental Model: The "Hollywood Lineup"
 * - The stack enforces a strict decreasing trend (taller elements at the bottom).
 * - Elements wait inside the stack frame until a larger element arrives.
 * - The act of popping represents the exact moment a "Next Greater" discovery is made.
 * * Time Complexity: O(N) - Every element is pushed once and popped at most once.
 * Space Complexity: O(N) - Memory allocation for the tracking stack and ledger map.
 */
public class NextGreaterElementMonotonicStack {

    public int[] findNextGreaterElement(int[] nums) {
        // Guard configuration against uninitialized or empty datasets
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        // The "Hallway": Stores elements awaiting their discovery spike
        Stack<Integer> stack = new Stack<>();

        // The "Ledger": Records discoveries as [Popped Element -> Next Greater Element]
        Map<Integer, Integer> map = new HashMap<>();

        int[] result = new int[nums.length];

        // =========================================================================
        // PASS 1: MONOTONIC TRACKING ENGINE
        // =========================================================================
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];

            /*
             * EVICTION INVARIANT VALIDATION:
             * While the stack contains items AND the incoming element is strictly
             * larger than the item waiting at the top of the stack.
             */
            while (!stack.empty() && currentNum > stack.peek()) {
                // Evict the smaller element from the stack frame
                int poppedNum = stack.pop();

                // Immediately log that the incoming element is its next greater match
                map.put(poppedNum, currentNum);
            }

            // Once the hallway is cleared of smaller items, the current element settles in
            stack.push(currentNum);
        }

        // =========================================================================
        // PASS 2: FINAL OUTPUT TRANSCRIPTION
        // =========================================================================
        for (int i = 0; i < nums.length; i++) {
            // If the element found its match in our ledger database, fetch it
            if (map.containsKey(nums[i])) {
                result[i] = map.get(nums[i]);
            }
            // Otherwise, it remained stuck in the stack frame, meaning no match exists (-1)
            else {
                result[i] = -1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        NextGreaterElementMonotonicStack engine = new NextGreaterElementMonotonicStack();

        int[] testArray = {2, 1, 3, 4};
        int[] result = engine.findNextGreaterElement(testArray);

        System.out.print("Input Array:  [2, 1, 3, 4]\nOutput Ledger: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i < result.length - 1 ? ", " : ""));
        }
        System.out.println("]");
        // Expected Output: [3, 3, 4, -1]
    }
}