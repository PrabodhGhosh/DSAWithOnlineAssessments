package com.prabodh.pattern.sequential;

public class MinSizeSubarraySumVariableSlidingWindow {

    public int findMinSizeSubarray(int[] arr, int target)
    {
        int left = 0;
        int currentSum=0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0;right<arr.length;right++)
        {
            currentSum +=arr[right];
            // Shrink the window as much as possible while the condition holds true
            while (currentSum>=target)
            {
                int length  = right-left+1;
                minLength = Math.min(length,minLength);
                currentSum-=arr[left];
                left++;
            }
        }

        // Handle edge case where no subarray meets the target criteria
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        System.out.println(new MinSizeSubarraySumVariableSlidingWindow().
                findMinSizeSubarray(new int[]{2, 3, 1, 2, 4, 3}, 7));
    }
}
