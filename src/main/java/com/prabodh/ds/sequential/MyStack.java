package com.prabodh.ds.sequential;

public class MyStack {
    private int[] stackData; // Reuse your array logic
    private int top; // Pointer to the top of the stack
    private int capacity;

    public MyStack(int initialCapacity)
    {
        this.capacity=initialCapacity;
        stackData = new int[capacity];
        this.top=-1;
    }

    public void push (int element)
    {
        // 1. Boundary Check: Is it full?
        if(top==stackData.length-1)
        {
            resize();
        }
        // 2. Increment pointer
        top++;
        //3. Assign data at the new index
        stackData[top]=element;
    }

    public int pop() {
        // 1. Boundary Check: Is it empty? (Underflow)
        System.out.println(top);
        if (top == -1) {
            throw new RuntimeException("Stack Underflow: Cannot pop from an empty stack.");
        }

        // 2. Retrieve data
        int value = stackData[top];

        // 3. Decrement pointer (effectively removing the element)
        top--;

        return value;
    }

    public int peek() {
        if (top == -1) {
            throw new RuntimeException("Stack is empty.");
        }
        return stackData[top]; // Just look, don't move the pointer
    }

    public void resize()
    {
        int newCapacity = capacity*2;
        int[] newStackData = new int[newCapacity];
        for(int i = 0; i <= top; i++)
        {
            newStackData[i]=stackData[i];
        }
        stackData=newStackData;
        capacity=newCapacity;
    }
}
