package com.prabodh.ds.sequential;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMyQueue {

    @Test
    public void testCircularBehavior() {
        MyQueue queue = new MyQueue(3);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3); // Full: [1, 2, 3]
        queue.enqueue(10); //resize

        queue.dequeue(); // Removes 1. Front is now at index 1.
        queue.dequeue(); // Removes 2. Front is now at index 2.

        // Rear was at index 0 (after 3), can we add more?
        queue.enqueue(4);
        queue.enqueue(5);

        Assert.assertEquals(queue.getSize(), 4);
        Assert.assertEquals(queue.peek(), 5); // The oldest remaining element
    }
}
