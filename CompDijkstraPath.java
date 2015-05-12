import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A class to represent a DijkstraPath, the shortest way
 * from a start node to an end node. Can be compared to
 * other paths based on the total weight of the path.
 * @author Jesper Olsson, Oskar Jonefors, Group 0
 * @param <E>
 */
public class CompDijkstraPath<E extends Edge>implements
        Comparable<CompDijkstraPath<E>>{

    private int to, from;
    private double weight;
    private List<E> edgeList;

    /**
     * Creates a DijkstraPath with only a starting point and no edges
     * @param from
     *              the starting point
     */
    public CompDijkstraPath(int from) {
        this.from = from;
        this.to = from;
        this.weight = 0;
        this.edgeList = new LinkedList<E>();
    }

    /**
     * Creates a DijkstraPath from an existing instance
     * and adds another edge to it
     * @param copiedInstance
     *            the copy of the instance
     * @param edge
     *            the edge to be added
     */
    public CompDijkstraPath(CompDijkstraPath<E> copiedInstance, E edge) {
        this.edgeList = new LinkedList<E>();
        this.edgeList.addAll(copiedInstance.edgeList);
        this.from = copiedInstance.from;
        this.weight = copiedInstance.weight;
        this.addEdge(edge);

    }

    /**
     * Adds another edge to the list
     * @param edge
     *            the edge to be added
     */
    public void addEdge(E edge){
        edgeList.add(edge);
        this.weight += edge.getWeight();
        this.to = edge.to;
    }

    /**
     * The total weight of the DijkstraPath
     * @return
     *        the total weight
     */
    public double getWeight(){
        return this.weight;
    }

    /**
     * The starting node of the DijkstraPath
     * @return
     *        the starting node
     */
    public int getFrom(){
        return from;
    }

    /**
     * The end node of the DijkstraPath
     * @return
     *        the end node
     */
    public int getTo(){
        return to;
    }

    /**
     * Compares the total weight of two DijkstraPaths
     */
    @Override
    public int compareTo(CompDijkstraPath<E> other){
        if (other == null) {
            throw new NullPointerException("compareTo: other object null");
        } else {
            return Double.compare(this.getWeight(), other.getWeight());
        }
    }

    /**
     * Iterator of the list of edges in this DijkstraPath
     * @return
     *        iterator over edgeList
     */
    public Iterator<E> iterator(){
        return edgeList.iterator();
    }
}
