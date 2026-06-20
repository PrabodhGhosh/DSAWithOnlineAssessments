package com.prabodh.ds.hierarchical;

public class CustomBSTTest {
    public static void main(String[] args) {
        CustomBST tree = new CustomBST();

        System.out.println("--- Initializing Hierarchical Lab ---");
        // Inserting values out-of-order to verify structural self-sorting
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Expected Total Tracked Elements: 7");
        System.out.println("Actual Total Tracked Elements:   " + tree.getSize());

        System.out.print("Verifying In-Order Traversal (Should output sorted values): ");
        tree.printInOrder();
    }
}