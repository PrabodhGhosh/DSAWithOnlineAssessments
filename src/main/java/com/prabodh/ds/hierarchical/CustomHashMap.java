package com.prabodh.ds.hierarchical;

public class CustomHashMap {

    // Internal linked list node layout for Separate Chaining collision handling
    private static class Entry {
        final String key;
        int value;
        Entry next; // Pointers to subsequent collided items sharing this bucket slot

        public Entry(String key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry[] buckets;
    private int capacity; // Total physical slot spaces available
    private int size;     // Total active key-value records tracked

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new Entry[capacity];
        this.size = 0;
    }

    /**
     * The Transformer Engine (Hash Function)
     * Maps any variable-length String key down to a strict bounded bucket index.
     */
    private int getBucketIndex(String key) {
        if (key == null) return 0;
        // Math.abs strips negative bits to protect against out-of-bounds array crashes
        return Math.abs(key.hashCode()) % capacity;
    }

    /**
     * Insertion / Modification Engine
     * Time Complexity: O(1) Amortized
     */
    public void put(String key, int value) {
        int index = getBucketIndex(key);
        Entry head = buckets[index];

        // Scenario A: Check if the key already exists in this chain to overwrite it
        Entry current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // Key match found. Perform update and exit.
                return;
            }
            current = current.next;
        }

        // Scenario B: Handle a brand-new insertion or an active collision.
        // We use the Front-Insertion technique to attach the new element in constant O(1) time.
        Entry newEntry = new Entry(key, value);
        newEntry.next = head;      // Hook the old chain head onto our new element's back
        buckets[index] = newEntry; // Establish our new element as the new front of the line
        size++;
    }

    /**
     * Retrieval Engine
     * Time Complexity: O(1) Amortized
     */
    public int get(String key) {
        // 1. Instantly jump straight to the correct bucket row via our hash math
        int index = getBucketIndex(key);
        Entry current = buckets[index];

        // 2. Linear scan down ONLY the small collided list sequence in this specific slot
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value; // Target identified! Return the stored value.
            }
            current = current.next; // Move to the next link in the chain
        }

        // 3. Chain exhausted without matching the key
        return -1;
    }

    public int getSize() {
        return this.size;
    }

    public static void main(String[] args) {
        // We initialize a small map with only 4 buckets to guarantee collisions happen easily
        CustomHashMap map = new CustomHashMap(4);

        System.out.println("--- Executing Custom Hash Map Verification Loop ---");

        // 1. Populate clean baseline key-value mappings
        map.put("Prabodh", 192);
        map.put("Ganesh", 450);

        System.out.println("Retrieved Prabodh (Expected 192): " + map.get("Prabodh"));
        System.out.println("Retrieved Ganesh  (Expected 450): " + map.get("Ganesh"));

        // 2. Perform an inline Update evaluation
        map.put("Prabodh", 500);
        System.out.println("Retrieved Prabodh Post-Update (Expected 500): " + map.get("Prabodh"));

        // 3. Test Missing Key Logic
        System.out.println("Retrieved Unknown Key (Expected -1): " + map.get("Suresh"));

        // 4. Trace a structural Collision under the hood
        // Let's print out the target bucket indices for these two keys to see if they collide:
        int index1 = map.getBucketIndex("Ganesh");
        int index2 = map.getBucketIndex("Banana");

        System.out.println("\n--- Collision Diagnostics ---");
        System.out.println("Ganesh mapping index: " + index1);
        System.out.println("Banana mapping index: " + index2);

        if (index1 == index2) {
            System.out.println("Status: Collision Detected! Forcing Separate Chaining allocation...");
            map.put("Banana", 888);

            // Thanks to Separate Chaining, both items safely co-exist at the same index slot!
            System.out.println("Retrieved Ganesh (Still intact! Expected 450): " + map.get("Ganesh"));
            System.out.println("Retrieved Banana (Successfully chained! Expected 888): " + map.get("Banana"));
        }
    }
}