package com.prabodh.pattern.nonlinear;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

    /**
     * OA Pattern: Topological Sort via Kahn's Algorithm (BFS In-Degree Framework)
     * Time Complexity: O(V + E) - Linear check over vertices and dependency vectors.
     * Space Complexity: O(V + E) - To maintain our built adjacency mappings.
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Initialize adjacency mapping blueprint and an array to track incoming dependencies
        List<Integer>[] adjList = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adjList[i] = new ArrayList<>();
        }

        // Step 2: Build the dependency network layout
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];

            // Directed Edge path: Prerequisite -> Course
            adjList[prerequisite].add(course);

            // Increment dependency count for the target course
            inDegree[course]++;
        }

        // Step 3: Seed the FIFO queue with courses that have 0 prerequisites
        Queue<QueueElement> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(new QueueElement(i));
            }
        }

        // Track how many courses we are successfully able to clear out
        int processedCoursesCount = 0;

        // Step 4: Process the courses layer-by-layer
        while (!queue.isEmpty()) {
            QueueElement current = queue.poll();
            processedCoursesCount++;

            // For every downstream course that depends on this cleared prerequisite
            for (int dependentCourse : adjList[current.courseId]) {
                // Decrement its remaining prerequisite requirements count
                inDegree[dependentCourse]--;

                // If all prerequisites are cleared, it is now unlocked! Add it to the queue
                if (inDegree[dependentCourse] == 0) {
                    queue.add(new QueueElement(dependentCourse));
                }
            }
        }

        // Structural Invariant: If we processed all courses, no deadlocked cycles exist!
        return processedCoursesCount == numCourses;
    }

    // A lightweight helper wrapper class instead of raw primitives to model clear intent
    private static class QueueElement {
        int courseId;
        public QueueElement(int courseId) {
            this.courseId = courseId;
        }
    }

    public static void main(String[] args) {
        CourseSchedule solver = new CourseSchedule();

        int numCourses = 2;
        // Course 1 requires Course 0. (Valid scheduling: Take 0 -> Take 1)
        int[][] validPrereqs = {{1, 0}};

        System.out.println("\n--- Topological Sort Pattern: Dependency Cycle Validation ---");
        System.out.println("Can complete valid schedule? " + solver.canFinish(numCourses, validPrereqs)); // Expected: true

        // Deadlocked Cycle: 0 requires 1, and 1 requires 0!
        int[][] cyclicPrereqs = {{1, 0}, {0, 1}};
        System.out.println("Can complete cyclic deadlocked schedule? " + solver.canFinish(numCourses, cyclicPrereqs)); // Expected: false
    }
}