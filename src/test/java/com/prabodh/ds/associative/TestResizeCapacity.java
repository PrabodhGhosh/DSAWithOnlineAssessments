package com.prabodh.ds.associative;

import com.prabodh.ds.sequential.MyDynamicArray;

public class TestResizeCapacity {
    public static void main() {

        MyDynamicArray dynamicArray = new MyDynamicArray(2);
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(3);
        System.out.println(dynamicArray.get(2));

    }
}
