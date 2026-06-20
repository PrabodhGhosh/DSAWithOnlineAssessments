package com.prabodh.pattern.sequential;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeInterval2 {

    public int[][] merge(int[][] intervals) {
        if(intervals.length==0 || intervals.length==1)
        {
            return intervals;
        }

        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentClip = intervals[0];
        merged.add(currentClip);

        for(int i=1;i<intervals.length;i++)
        {
            int[] nextClip = intervals[i];
            if(nextClip[0]<=currentClip[1])
            {
                currentClip[1]=Math.max(currentClip[1],nextClip[1]);
            }

            else

            {
                currentClip=nextClip;
                merged.add(currentClip);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        MergeInterval2 engine = new MergeInterval2();

        // Test Input: Mix of overlapping, touching, and distant gaps
        int[][] meetingSlots = {
                {1, 4},
                {4, 5},
                {1, 3},
                {7, 9}
        };

        int[][] consolidated = engine.merge(meetingSlots);

        System.out.println("Input Matrix Slots: [[1, 4], [4, 5], [1, 3], [7, 9]]");
        System.out.print("Consolidated Track: [");
        for (int i = 0; i < consolidated.length; i++) {
            System.out.print("[" + consolidated[i][0] + ", " + consolidated[i][1] + "]"
                    + (i < consolidated.length - 1 ? ", " : ""));
        }
        System.out.println("]");
        // Expected Output: [[1, 5], [7, 9]]
    }
}
