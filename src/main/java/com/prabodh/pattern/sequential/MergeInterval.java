package com.prabodh.pattern.sequential;

import java.util.ArrayList;
import java.util.List;

// Simple Interval tracking model
class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}

public class MergeInterval {

    public List<Interval> merge(List<Interval> interval) {
        // Base case configuration guard
        if (interval == null || interval.isEmpty() || interval.size() == 1) {
            return interval;
        }

        //Sort the intervals based on their start time
        interval.sort((a, b) -> Integer.compare(a.start, b.start));

        List<Interval> mergedInterval = new ArrayList<>();
        Interval currentClip = interval.get(0);
        //Add the first interval to the list
        mergedInterval.add(currentClip);

        for (int i = 1; i < interval.size(); i++) {
            Interval nextClip = interval.get(i);

            // Scenario A: Overlap detected -> Stretch the active frame right edge
            if (nextClip.start <= currentClip.end) {
                currentClip.end = Math.max(currentClip.end, nextClip.end);
            }
            // Scenario B: Gap detected -> Shift workspace focus to the new clip block
            else {
                currentClip = nextClip;
                mergedInterval.add(currentClip);
            }
        }
        return mergedInterval;
    }

    public static void main(String[] args) {
        MergeInterval engine = new MergeInterval();

        List<Interval> pipeline = new ArrayList<>();
        pipeline.add(new Interval(1, 3));
        pipeline.add(new Interval(2, 6));
        pipeline.add(new Interval(8, 10));
        pipeline.add(new Interval(15, 18));

        List<Interval> output = engine.merge(pipeline);
        System.out.println("Consolidated Timeline: " + output);
        // Expected Output: [[1, 6], [8, 10], [15, 18]]
    }
}