
import java.util.*;

/**
 * A class to represent a directed graph, with methods to calculate
 * a shortest path and a minimum spanning tree.
 * @author Jesper Olsson, Oskar Jonefors, Group 0
 */
public class DirectedGraph<E extends Edge> {

    private List<E>[] nodeArray;

    /**
     * Create a directed graph with the given number of nodes.
     * @param noOfNodes  A positive number.
     */
    public DirectedGraph(int noOfNodes) {
        if (noOfNodes < 1) {
            throw new IllegalArgumentException("DirectedGraph: noOfNodes were " +
                    noOfNodes + ". May not be zero or negative!");
        }

        nodeArray = (List<E>[])new LinkedList[noOfNodes];
        for (int i = 0; i < nodeArray.length; i++) {
            nodeArray[i] = new LinkedList<E>();
        }
    }

    /**
     * Add the given edge to this graph
     * @param e
     */
    public void addEdge(E e) {
        if (e == null) {
            throw new IllegalArgumentException("addEdge: e was null!");
        }
        nodeArray[e.from].add(e);
    }

    /**
     * Get an iterator that iterates over the shortest path between the two
     * given node indices. If no such path can be found, null will be returned.
     *
     * @param from   The index of the starting node.
     * @param to  The index of the end node.
     * @return  An iterator that iterates through the shortest path between
     *          from and to, or null if no route can be found.
     */
    public Iterator<E> shortestPath(int from, int to) {

        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("shortestPath: Node indices " +
                    "cannot be negative! Given indices: From: " + from +
                    " To: " + to);
        } else if (from >= nodeArray.length || to >= nodeArray.length) {
            throw new IllegalArgumentException(
                    "shortestPath: Node indices were out of bounds. " +
                            "Number of nodes: " + nodeArray.length + "Given indices:" +
                            " From: " + from + " To: " + to);
        }

        boolean[] visited = new boolean[nodeArray.length];
        PriorityQueue<CompDijkstraPath<E>> edges = new PriorityQueue<CompDijkstraPath<E>>();
        edges.add(new CompDijkstraPath<E>(from));
        while (!edges.isEmpty()) {
            CompDijkstraPath<E> shortest = edges.poll();
            int shortestTo = shortest.getTo();
            if (!visited[shortestTo]) {
                if (shortestTo == to) {
                    return shortest.iterator();
                } else {
                    visited[shortestTo] = true;
                    for (E edge: nodeArray[shortestTo]) {
                        if (!visited[edge.to]) {
                            edges.add(new CompDijkstraPath<E>(shortest, edge));
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Get an iterator that iterates over all edges present in a minimum
     * spanning tree of the graph.
     * @return
     */
    public Iterator<E> minimumSpanningTree() {
        PriorityQueue<CompKruskalEdge<E>> pq = new PriorityQueue<CompKruskalEdge<E>>();
        for (List<E> edgeList:  nodeArray) {
            for(E edge: edgeList){
                pq.add(new CompKruskalEdge<E>(edge));
            }
        }
        List<E>[] nodes = (List<E>[]) new LinkedList[this.nodeArray.length];
        for(int i = 0; i<nodes.length; i++) {
            nodes[i] = new LinkedList<E>();
        }
        while (!pq.isEmpty()) {
            E lightestEdge = pq.poll().getEdge();
            int shortListIndex, longListIndex;
            if (nodes[lightestEdge.from] != nodes[lightestEdge.to]) {
                if (nodes[lightestEdge.from].size() >= nodes[lightestEdge.to].size()) {
                    longListIndex = lightestEdge.from;
                    shortListIndex = lightestEdge.to;
                } else {
                    longListIndex = lightestEdge.to;
                    shortListIndex = lightestEdge.from;
                }
                for (E edge: nodes[shortListIndex]) {
                    nodes[edge.from] = nodes[longListIndex];
                    nodes[edge.to] = nodes[longListIndex];
                    nodes[longListIndex].add(edge);
                }
                nodes[shortListIndex] = nodes[longListIndex];
                nodes[longListIndex].add(lightestEdge);

                /* When we have n - 1 edges for our n nodes,
                we have a minimum spanning tree */
                if (nodes[longListIndex].size() == (nodes.length - 1)) {
                    return nodes[longListIndex].iterator();
                }
            }
        }
        return null;
    }
}
  
