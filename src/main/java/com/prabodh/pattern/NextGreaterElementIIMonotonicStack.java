package com.prabodh.pattern;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElementIIMonotonicStack {

    public int[] findNextGreaterElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n];

        // 1. Seed the entire array with -1 as our structural default
        Arrays.fill(result, -1);

        // Our stack stores INDICES, not raw values
        Stack<Integer> stack = new Stack<>();

        // 2. Loop through a virtual doubled array length
        for (int i = 0; i < n * 2; i++) {
            int realIndex = i % n;
            int currentNum = nums[realIndex];

            // 3. Eviction Check: Is the current number hotter than the top index's value?
            while (!stack.empty() && currentNum > nums[stack.peek()]) {
                int poppedIndex = stack.pop();

                // Write the discovery directly into the final array slot!
                result[poppedIndex] = currentNum;
            }

            // 4. Invariant Guard: Only push original indices during the first pass
            if (i < n) {
                stack.push(realIndex);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        NextGreaterElementIIMonotonicStack engine = new NextGreaterElementIIMonotonicStack();
        int[] testArray = {1, 2, 1};
        int[] output = engine.findNextGreaterElement(testArray);

        System.out.print("Circular Ledger Output: [");
        for (int i = 0; i < output.length; i++) {
            System.out.print(output[i] + (i < output.length - 1 ? ", " : ""));
        }
        System.out.println("]"); // Expected Output: [2, -1, 2]
    }
}