package com.prabodh.ds.sequential;

public class MyQueue {

    private int[] QueueData;
    private int front; // Points to the head (for dequeue)
    private int rear; // Points to the tail (for enqueue)
    private int size;
    private int capacity;


    public MyQueue(int initialCapacity)
    {
        this.capacity=initialCapacity;
        QueueData = new int[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public void enqueue(int element)
    {
        if (size == capacity)
        {
            resize();
        }

        QueueData[rear]=element;
        // Circular increment: (0+1)%5 = 1... (4+1)%5 = 0
        rear = (rear+1)%capacity;
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            throw new RuntimeException("Queue Underflow");
        }
        int value = QueueData[front];
        // Circular increment for front as well
        front= (front+1)%capacity;
        size--;
        return value;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("Queue is empty");
        }
        return QueueData[front];
    }

    public int getSize() {
        return size;
    }

    public void resize()
    {
        int newCapacity = capacity*2;
        int[] newQueueData = new int[newCapacity];
        for (int i= 0; i<QueueData.length;i++)
        {
            newQueueData[i]=QueueData[i];
        }
        QueueData=newQueueData;
        capacity=newCapacity;
    }


}
