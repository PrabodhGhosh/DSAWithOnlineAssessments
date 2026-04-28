package com.prabodh.ds.sequential;

public class MyLinkedList {

    // 1. Encapsulate the Node
    private class Node{
        int data;
        Node next;

        Node(int data)
        {
            this.data=data;
            this.next=null;
        }
    }

    private Node head; // The entry point to the list
    private int size; //Keeping track of size is an O(1) optimization


    // 2. Add: The "Append" Operation

    public void add(int data)
    {
        Node newNode = new Node(data);
        // Scenario A: The list is empty
        if (head==null)
        {
            head=newNode;
        }
        // Scenario B: Traverse to the end
        else{
            Node current = head;
            while(current.next!=null)
            {
                current=current.next; // Follow the pointer
            }
            current.next = newNode; // Re-point the last node to the new node
        }
        size++;

    }

    // 3. Get: The "Traversal" Operation

    public int get(int index)
    {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next; // Move the pointer forward
        }
        return current.data;
    }



}
