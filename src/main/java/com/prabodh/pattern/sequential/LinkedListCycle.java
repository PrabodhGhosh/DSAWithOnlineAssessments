package com.prabodh.pattern.sequential;
import com.prabodh.ds.sequential.MyLinkedList.Node;

public class LinkedListCycle {

    public boolean hasCycle(Node head)
    {
        if(head == null || head.next==null)
        {
           return false;
        }

        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null) // handles both odd and even numbered nodes
            //in the linked list
        {
            slow=slow.next; // 1 step
            fast = fast.next.next; // 2 steps
            // The moment they meet, a cycle is confirmed
            if (slow == fast) {
                return true;
            }
        }
        return false; // Fast reached the end, no cycle
    }
}
