package com.prabodh.pattern;

public class RangeSumQueryPrefixSum {

    public int[] prefixSums = null;

    public RangeSumQueryPrefixSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            // Safeguard against downstream NullPointerExceptions
            this.prefixSums = new int[1];
            return;
        }

        // Initialize with size N + 1 to elegantly avoid out-of-bounds checks for index 0
        this.prefixSums = new int[nums.length + 1];
        // Accumulate running totals
        for (int i = 0; i < nums.length; i++) {
            this.prefixSums[i + 1] = this.prefixSums[i] + nums[i];
        }
    }

        public int sumRange(int left, int right)
        {
            // Map original 0-indexed bounds to our 1-indexed prefix mapping
            return prefixSums[right+1]-prefixSums[left];
        }

    public static void main(String[] args) {
        System.out.println(new RangeSumQueryPrefixSum(new int[]{1,2,3,4,5}).sumRange(2,3));
    }
    }



