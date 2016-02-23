package graph;

import java.util.Objects;

/**
 * Represents the Edge of a Graph
 *
 * @param <V> Generic element which refers to what is contained by the Vertex.
 * @param <E> Generic element which refers to what is considered as weight.
 */
public class Edge<V, E> implements Comparable {

    /**
     * Generic element of the edge.
     */
    private E element;

    /**
     * Weight of the edge.
     */
    private double weight;

    /**
     * Vertex of Origin of the edge.
     */
    private Vertex<V, E> vOrig;

    /**
     * Vertex of Destination of the edge.
     */
    private Vertex<V, E> vDest;

    /**
     * Creates an instance of edge with all its attributes set to default
     * values.
     *
     * Element = null Weight = 0.0 vOrig = null vDest = null
     */
    public Edge() {
        this.element = null;
        this.weight = 0.0;
        this.vOrig = null;
        this.vDest = null;
    }

    /**
     * Creates an instance of edge receiving an element, a weight, a vertex of
     * origin and a vertex of destination.
     *
     * @param element Generic element of the edge.
     * @param weight Weight of the edge.
     * @param vOrig Vertex of origin.
     * @param vDest Vertex of destination.
     */
    public Edge(E element, double weight, Vertex<V, E> vOrig,
            Vertex<V, E> vDest) {
        this.element = element;
        this.weight = weight;
        this.vOrig = vOrig;
        this.vDest = vDest;
    }

    /**
     * Returns the generic element of the edge.
     *
     * @return Generic element of the edge.
     */
    public E getElement() {
        return this.element;
    }

    /**
     * Returns the weight of the edge.
     *
     * @return Weight of the edge.
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Returns the vertex of origin of the edge.
     *
     * @return Vertex of origin of the edge.
     */
    public Vertex<V, E> getVOrig() {
        return this.vOrig;
    }

    /**
     * Returns the vertex of destination of the edge.
     *
     * @return Vertex of destination of the edge.
     */
    public Vertex<V, E> getVDest() {
        return this.vDest;
    }

    /**
     * Sets the element of the edge.
     *
     * @param element New element of the edge.
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Sets the weight of the edge.
     *
     * @param weight New weight of the edge.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Sets the vertex of origin of the edge.
     *
     * @param vOrig New vertex of origin of the edge.
     */
    public void setVOrig(Vertex<V, E> vOrig) {
        this.vOrig = vOrig;
    }

    /**
     * Sets the vertex of destination of the edge.
     *
     * @param vDest New vertex of destination of the edge.
     */
    public void setVDest(Vertex<V, E> vDest) {
        this.vDest = vDest;
    }

    /**
     * Returns the end points of the edge, the vertex of origin and the vertex
     * of destination.
     *
     * @return Array with the end points of the edge.
     */
    public Vertex<V, E>[] getEndpoints() {
        Vertex<V, E>[] endverts = new Vertex[2];

        endverts[0] = this.vOrig;
        endverts[1] = this.vDest;

        return endverts;
    }

    /**
     * Returns the information of an edge.
     *
     * @return The information about the edge.
     */
    @Override
    public String toString() {
        String st = "";
        if (element != null) {
            st = "\t- (" + element + ")";
        } else {
            st = "\t- ";
        }

        if (weight != 0) {
            st += weight + " - " + vDest.getElement();
        } else {
            st += vDest.getElement();
        }

        return st;
    }

    /**
     * Compares two edges, first it looks at their location in the memory, then
     * if different it ensures that both the classes are the same and if one of
     * them is not null and finally compares all their attributes.
     *
     * @param obj Edge that will be compared.
     * @return True if both edges are equals or false if not.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Edge<V, E> otherEdge = (Edge<V, E>) obj;

        return (this.weight == otherEdge.weight
                && this.element != null && otherEdge.element != null
                && this.element.equals(otherEdge.element));
    }

    /**
     * Alters the hash code of an instanced object, speeding up its storage or
     * retrieval.
     *
     * @return Hash code of the instanced object.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.element);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        return hash;
    }

    /**
     * Compares the weight of two edges determining which one has the lowest
     * cost.
     *
     * @param otherObj Edge that will be compared.
     * @return The result of the comparison: -1 if the weight of the first edge
     * is lower. 0 if the weights are the same. 1 if the weight of the first
     * edge is higher.
     */
    @Override
    public int compareTo(Object otherObj) {
        Edge<V, E> other = (Edge<V, E>) otherObj;

        if (this.weight < other.weight) {
            return -1;
        }

        if (this.weight == other.weight) {
            return 0;
        }

        return 1;
    }

}
