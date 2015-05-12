/**
 * A class to represent an edge which can be compared
 * to other edges based on it's weight.
 * @author Jesper Olsson, Oskar Jonefors, Group 0
 */
public class CompKruskalEdge<E extends Edge>
        implements Comparable<CompKruskalEdge<E>> {

    private E edge;

    public CompKruskalEdge(E edge) {
        if (edge == null) {
            throw new IllegalArgumentException(
                    "CompKruskalEdge: edge was null!");
        }
        this.edge = edge;
    }


    @Override
    public int compareTo(CompKruskalEdge<E> o) {
        if (o == null) {
            throw new NullPointerException("Object was null!");
        }
        return Double.compare(edge.getWeight(), o.getWeight());
    }

    public int getSource() {
        return edge.getSource();
    }

    public int getDest() {
        return edge.getDest();
    }

    public double getWeight() {
        return edge.getWeight();
    }

    public E getEdge() {
        return edge;
    }
}
