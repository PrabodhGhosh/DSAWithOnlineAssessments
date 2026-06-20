package com.prabodh.pattern.sequential;
import com.prabodh.ds.sequential.MyLinkedList.Node;

public class DetectStartOfCycleSlowFast {

    public Node detectStartOfCycle(Node head)
    {
        if(head == null || head.next ==null)
            return null;
        Node slow = head;
        Node fast = head;
        boolean hasCycle = false;
        // Phase 1: Determine if a cycle exists
        while( fast!=null && fast.next !=null)
        {
            slow=slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        // If no cycle was found, return null
        if (!hasCycle) return null;
        // Phase 2: Find the entry point of the cycle
        // Reset fast to head, keep slow at the meeting point
        fast=head;
        while(slow!=fast)
        {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
