package com.prabodh.ds.sequential;

public class TestResizeCapacity {
    public static void main(String[] arg) {

        MyDynamicArray dynamicArray = new MyDynamicArray(2);
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(3);
        System.out.println(dynamicArray.get(2));

    }
}
