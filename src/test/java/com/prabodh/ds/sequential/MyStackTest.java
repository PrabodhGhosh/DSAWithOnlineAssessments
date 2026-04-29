package com.prabodh.ds.sequential;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MyStackTest {

    @Test

    public void testStackLifecycle()
    {
        MyStack myStack = new MyStack(2);
        myStack.push(5);
        myStack.push(10);
        myStack.push(15);
        // Assert: Verify state (LIFO Order)
        Assert.assertEquals(myStack.peek(), 15, "Peek should return the last pushed element");

        // Act & Assert: Verify pop sequence
        Assert.assertEquals(myStack.pop(), 15, "First pop should be 15");
        Assert.assertEquals(myStack.pop(), 10, "Second pop should be 10");
        Assert.assertEquals(myStack.pop(), 5, "Third pop should be 5");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testStackUnderflow() {
        // Arrange
        MyStack myStack = new MyStack(5);

        // Act: Pop from empty stack
        myStack.pop();
    }
}
