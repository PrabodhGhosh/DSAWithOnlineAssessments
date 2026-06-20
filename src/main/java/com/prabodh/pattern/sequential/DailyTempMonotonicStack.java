package com.prabodh.pattern.sequential;

import java.util.Stack;

public class DailyTempMonotonicStack {

    public int[] findNextHotDay(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length]; // Automatically initialized to 0s

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                int poppedIndex = stack.pop();
                result[poppedIndex] = i - poppedIndex; // Calculates distance between days
            }
            stack.push(i); // Push the index, not the value
        }

        return result;
    }

    public static void main(String[] args) {
        DailyTempMonotonicStack engine = new DailyTempMonotonicStack();
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = engine.findNextHotDay(temps);

        System.out.print("Wait Days Output: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i < result.length - 1 ? ", " : ""));
        }
        System.out.println("]"); // Expected Output: [1, 1, 4, 2, 1, 1, 0, 0]
    }
}