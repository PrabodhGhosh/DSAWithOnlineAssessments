package com.prabodh.pattern.sequential;

public class MaxSumSubarrayFixedSlidingWindow {

    public int findMaxSumSuArray(int[] arr, int k)
    {
        if (arr == null || arr.length < k || k <= 0) {
            return 0;
        }

        //Phase 1 : Compute the sum of the first window of size K
        int maxSum = 0;
        int windowSum=0;

        for (int i=0;i<k;i++)
        {
            windowSum+=arr[i];
        }
        maxSum=windowSum;

        //Phase 2: Slide the window across the array
        for(int i=k; i<arr.length;i++)
        {
            windowSum+=arr[i]-arr[i-k];
            // Track the maximum sum seen so far
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {

        MaxSumSubarrayFixedSlidingWindow maxSumSubarrayFixedSlidingWindow = new MaxSumSubarrayFixedSlidingWindow();
        System.out.println(maxSumSubarrayFixedSlidingWindow.findMaxSumSuArray(new int[]{2, 1, 5, 1, 3, 2},3));

    }

}
