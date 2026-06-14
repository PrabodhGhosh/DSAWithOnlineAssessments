package com.prabodh.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure 3-Phase Interval Partitioning Pattern: Insert Interval.
 * * Mental Model: "Splitting the Timeline"
 * - Phase 1: Pass through and add all early intervals that end before the new interval begins.
 * - Phase 2: Continuously absorb and merge overlapping intervals into the new interval boundaries.
 * - Phase 3: Append all downstream, leftover intervals that start after the new interval ends.
 * * Time Complexity: O(N) - Linear single-pass execution since the input track is pre-sorted.
 * Space Complexity: O(N) - Memory allocated to compile the dynamic output array structure.
 */
public class MergeNewInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Guard configuration checking against null inputs or empty additions
        if (intervals == null || newInterval == null || newInterval.length == 0) {
            return intervals;
        }

        List<int[]> results = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // =========================================================================
        // PHASE 1: CHRONOLOGICAL PRE-BUFFER
        // =========================================================================
        // Collect all intervals that finish completely before the new interval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            results.add(intervals[i]);
            i++;
        }

        // =========================================================================
        // PHASE 2: DYNAMIC ABSORPTION / MERGE ZONE
        // =========================================================================
        // Melt overlapping items into the new interval as long as their start <= current end
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]); // Establish earliest start boundary
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]); // Establish furthest end boundary
            i++;
        }

        // Commit the fully consolidated/stretched new interval to our results ledger
        results.add(newInterval);

        // =========================================================================
        // PHASE 3: TRAILING COLD APPENDS
        // =========================================================================
        // Safely push all downstream remaining intervals directly into the output track
        while (i < n) {
            results.add(intervals[i]);
            i++;
        }

        // Convert the dynamic list structure back into a primitive 2D matrix frame
        return results.toArray(new int[results.size()][]);
    }
}