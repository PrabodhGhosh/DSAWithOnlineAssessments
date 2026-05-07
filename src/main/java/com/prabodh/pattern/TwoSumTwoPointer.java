package com.prabodh.pattern;

public class TwoSumTwoPointer {

    public int[] solveTwoSum(int[] num, int target)
    {
        int[] result = new int[2];
        int begin =0;
        int end = num.length-1;
        while (begin < end) {
            // Optimization: Calculate sum once
            int currentSum = num[begin] + num[end];

            if (currentSum == target) {
                // Returns indices. Change to values if that was your specific intent.
                return new int[]{begin, end};
            } else if (currentSum > target) {
                end--;
            } else {
                begin++;
            }
        }
     return new int[]{-1,-1};
    }
}
