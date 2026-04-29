package com.prabodh.ds.sequential;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestMyLinkedList {

    @Test

    public void testAddAndGet() {
        MyLinkedList list = new MyLinkedList();
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(list.get(0), 10);
        assertEquals(list.get(1), 20);
        assertEquals(list.get(2), 30);
    }
}
