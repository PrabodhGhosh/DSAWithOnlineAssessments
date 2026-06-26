package com.prabodh.ds.hierarchical;

public class CustomGraph {
    // The internal linked list element representing a path to a neighbor node
    private static class Edge{
        int targetVertex;
        Edge next;

        public Edge(int targetVertex)
        {
            this.targetVertex=targetVertex;
            this.next=null;
        }
    }
    private Edge[] adjList; // Array where each index represents a vertex slot
    private int numVertices; // Track total fixed vertex size limits

    public CustomGraph (int numVertices){
        this.numVertices=numVertices;
        this.adjList= new Edge[numVertices];
    }

    public void addEdge(int source, int destination) {
        // 1. Create a new line box whose target destination is '1' (Delhi)
        Edge newEdge = new Edge(destination);

        // 2. Thread this new line into the origin's existing list of lines
        newEdge.next = adjList[source];

        // 3. Update the origin vertex array slot to point to this new line as the head
        adjList[source] = newEdge;
    }

    public static void main(String[] args) {
        // Initialize our flight network with 4 vertex slots:
        // Index 0: Bangalore (BLR)
        // Index 1: Delhi (DEL)
        // Index 2: Mumbai (BOM)
        // Index 3: Kolkata (CCU)
        CustomGraph graph = new CustomGraph(4);

        System.out.println("--- Constructing Airport Network Paths ---");

        // Add flights out of Bangalore (0)
        graph.addEdge(0, 1); // BLR -> DEL
        graph.addEdge(0, 2); // BLR -> BOM
        graph.addEdge(0, 3); // BLR -> CCU

        // Add a flight out of Delhi (1)
        graph.addEdge(1, 3); // DEL -> CCU

        System.out.println("Flights successfully mapped in memory.");
        System.out.println("\n--- Traversing Adjacency Array & Edge Chains ---");

        // Loop through each index slot in our adjList array
        for (int i = 0; i < graph.numVertices; i++) {
            System.out.print("Airport [" + i + "] Outbound Lines: ");

            // Grab the first line box (head of the chain) sitting at this slot
            Edge currentLine = graph.adjList[i];

            // Walk down the chain using the 'next' variable until we hit null
            while (currentLine != null) {
                System.out.print("-> Destination: " + currentLine.targetVertex + " ");
                currentLine = currentLine.next; // Move to the next line box linked behind it
            }

            System.out.println("-> [No more flights]");
        }
    }
}


