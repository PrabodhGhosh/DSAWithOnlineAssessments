package com.prabodh.pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * Advanced Prefix Sum Pattern Variation: Subarray Sum Equals K.
 * Time Complexity: O(N) - Single pass traversal with O(1) average lookup times.
 * Space Complexity: O(N) - Worst-case auxiliary space allocation for prefix map tracking.
 */
public class SubarraySumEqualsKPrefixSum {

    public int subarraySum(int[] arr, int k) {
        // EDGE CASE SAFEGUARD
        // If the array reference is null or contains absolutely no elements,
        // it is mathematically impossible to form any valid continuous subarray.
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // Total counter to track the number of matching continuous subarrays discovered.
        int count = 0;

        // Cumulative running sum (Acts like an odometer tracking total distance from the start line).
        int currentSum = 0;

        /*
         * HISTORICAL LOOKBACK MAP (The "Notebook")
         * Key:   The cumulative prefix sum encountered up to a specific index.
         * Value: The total frequency (number of times) that exact prefix sum has been seen so far.
         * * Why a frequency count instead of a boolean flag?
         * Because elements can be zero or negative, the exact same 'currentSum' value can occur
         * multiple times in history. If it does, each occurrence represents a distinct starting boundary.
         */
        Map<Integer, Integer> prefixSumMap = new HashMap<>();

        /*
         * THE SEED / CRITICAL BASE CASE
         * Mathematically states: "Before looking at a single element, our cumulative sum is 0,
         * and we have observed this empty state exactly 1 time."
         * * Why? If a subarray starting at index 0 perfectly equals 'k', 'currentSum - k' evaluates to 0.
         * Without this seed milestone in our map, the lookback lookup would fail, blinding our
         * engine to any matching subarrays that originate from the absolute beginning of the array.
         */
        prefixSumMap.put(0, 1);

        // Begin processing the array sequentially
        for (int i = 0; i < arr.length; i++) {

            /*
             * STEP 1: ADVANCE THE CUMULATIVE ACCUMULATOR
             * Incorporate the current element into our running total. This represents the total
             * distance covered from the absolute start of the array up to the current index 'i'.
             */
            currentSum += arr[i];

            /*
             * STEP 2: THE CUMULATIVE DIFFERENCE LOOKBACK LOOKUP
             * Core Mathematical Identity:
             * If (currentSum_at_index_i) - (oldPrefixSum_at_some_index_j) == k
             * Then (currentSum_at_index_i) - k == oldPrefixSum_at_some_index_j
             * * We calculate 'targetLookback' to see if 'oldPrefixSum_at_some_index_j' exists in our map.
             * If it does, the segment of elements *between* index j and index i must sum exactly to k.
             */
            int targetLookback = currentSum - k;

            if (prefixSumMap.containsKey(targetLookback)) {
                /*
                 * If the targetLookback prefix sum exists in our notebook history, we have found
                 * valid matching continuous subarrays ending at our current index 'i'.
                 * We increment our total count by the exact number of times that specific
                 * lookup sum occurred in our history, as each represents a unique starting point.
                 */
                count += prefixSumMap.get(targetLookback);
            }

            /*
             * STEP 3: UPDATE HISTORICAL RECORDS
             * Log the currentSum into our notebook map so future iterations can look back at it.
             * If this prefix sum was seen before, increment its counter by 1.
             * If it is a new prefix sum, initialize its frequency counter to 1.
             */
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }

        /*
         * TRACE SUMMARY FOR YOUR MAIN METHOD EXECUTION CONTEXT:
         * k = 7, initial map = {0:1}
         * * i=0 (arr[0]=3): currentSum = 3. Lookback = 3-7 = -4. Not in map. Map updates to: {0:1, 3:1}
         * * i=1 (arr[1]=4): currentSum = 3+4 = 7. Lookback = 7-7 = 0. Found in map! (freq=1).
         * count becomes 1. (Valid subarray captured: arr[0..1] -> [3, 4]).
         * Map updates to: {0:1, 3:1, 7:1}
         * * i=2 (arr[2]=7): currentSum = 7+7 = 14. Lookback = 14-7 = 7. Found in map! (freq=1).
         * count becomes 2. (Valid subarray captured: arr[2..2] -> [7]).
         * Map updates to: {0:1, 3:1, 7:1, 14:1}
         * * i=3 (arr[3]=2): currentSum = 14+2 = 16. Lookback = 16-7 = 9. Not in map. Map updates to: {0:1, 3:1, 7:1, 14:1, 16:1}
         * * i=4 (arr[4]=-3): currentSum = 16+(-3) = 13. Lookback = 13-7 = 6. Not in map. Map updates to: {0:1, 3:1, 7:1, 14:1, 16:1, 13:1}
         * * Loop terminates. Final count = 2.
         */
        return count;
    }

    public static void main(String[] args) {
        SubarraySumEqualsKPrefixSum engine = new SubarraySumEqualsKPrefixSum();
        int[] testArray = {3, 4, 7, 2, -3};
        int target = 7;
        int result = engine.subarraySum(testArray, target);
        System.out.println("Total matching subarrays found: " + result); // Prints: 2
    }
}