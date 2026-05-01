package com.prabodh.ds.sequential;

public class MyDynamicArray {
    private int[] arr;
    private int size;
    private int capacity;

    public MyDynamicArray(int initialCapacity) {
        this.capacity = initialCapacity;
        this.arr = new int[capacity];
        this.size = 0;
    }

    /**
     * Adds an element to the array.
     * If the array is full, it triggers a resize.
     */
    public void add(int element) {

        if (size == capacity)
        {
            resize();
        }

        arr[size] = element;
        size++;
    }

    /**
     * Doubles the capacity and copies existing elements.
     * This is an O(n) operation.
     */

    public void resize() {
        int newCapacity = capacity * 2;
        int[] newArr = new int[newCapacity];
        //Copy data from old array to new array
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];

        }

        arr = newArr;
        capacity = newCapacity;

    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return arr[index];
    }
}
