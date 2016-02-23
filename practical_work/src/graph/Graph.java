package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents the Graph through a number of vertices, a number of edges, a
 * specification about if it is oriented and a list of vertices.
 *
 * @param <V> Generic element which refers to what is contained by the Vertex.
 * @param <E> Generic element which refers to what is considered as weight.
 */
public class Graph<V, E> implements GraphInterface<V, E> {

    /**
     * Number of the vertices of the graph.
     */
    private int numVert;

    /**
     * Number of the edges of the graph.
     */
    private int numEdge;

    /**
     * Specifies if the graph is oriented.
     */
    private final boolean isDirected;

    /**
     * List of the vertices of the graph.
     */
    private ArrayList<Vertex<V, E>> listVert;

    /**
     * Constructs an empty graph recieving a boolean stating if the same is
     * oriented or not.
     *
     * @param directed Boolean value which states if the graph is oriented or
     * not.
     */
    public Graph(boolean directed) {
        this.numVert = 0;
        this.numEdge = 0;
        this.isDirected = directed;
        this.listVert = new ArrayList<>();
    }

    /**
     * Returns the number of vertices of the graph.
     *
     * @return the number of vertices of the graph.
     */
    @Override
    public int getNumVertices() {
        return this.numVert;
    }

    /**
     * Returns all the vertices of the graph as an iterable collection.
     *
     * @return all the vertices of graph as an iterable collection.
     */
    @Override
    public Iterable<Vertex<V, E>> getVertices() {
        return this.listVert;
    }

    /**
     * Returns the number of edges of the graph.
     *
     * @return the number of edges of the graph.
     */
    @Override
    public int getNumEdges() {
        return this.numEdge;
    }

    /**
     * Returns all the edges of the graph as an iterable collection.
     *
     * @return all the edges of the graph as an iterable collection.
     */
    @Override
    public Iterable<Edge<V, E>> getEdges() {
        ArrayList<Edge<V, E>> edges = new ArrayList<>();

        for (Vertex<V, E> vertex : this.listVert) {
            edges.addAll(vertex.getOutgoing().values());
        }

        return edges;
    }

    /**
     * Returns the edge from vOrig to vDest, or null if vertices are not
     * adjacent.
     *
     * @param vOrig Vertex of origin.
     * @param vDest Vertex of destination.
     * @return the edge or null if getVertices are not adjacent or don't exist
     */
    @Override
    public Edge<V, E> getEdge(Vertex<V, E> vOrig, Vertex<V, E> vDest) {
        if (this.listVert.contains(vOrig) && this.listVert.contains(vDest)) {
            return vOrig.getOutgoing().get(vDest);
        }

        return null;
    }

    /**
     * Returns the vertices of the edge as an array of length two. If the graph
     * is directed, the first vertex is the origin, and the second is the
     * destination. If the graph is undirected, the order is arbitrary.
     *
     * @param edge Edge.
     * @return array of two vertices or null if edge doesn't exist
     */
    @Override
    public Vertex<V, E>[] endVertices(Edge<V, E> edge) {
        for (Vertex<V, E> vertex : this.listVert) {
            Map<Vertex<V, E>, Edge<V, E>> outVerts = vertex.getOutgoing();

            for (Edge<V, E> e : outVerts.values()) {
                if (edge.equals(e)) {
                    return e.getEndpoints();
                }
            }
        }
        return null;
    }

    /**
     * Returns the vertex that is opposite the vertex on the edge.
     *
     * @param vertex Vertex.
     * @param edge Edge.
     * @return opposite vertex, or null if vertex or edge don't exist.
     */
    @Override
    public Vertex<V, E> opposite(Vertex<V, E> vertex, Edge<V, E> edge) {
        for (Vertex<V, E> v : this.listVert) {
            if (v.equals(vertex)) {
                Map<Vertex<V, E>, Edge<V, E>> outVerts = vertex.getOutgoing();

                for (Edge<V, E> e : outVerts.values()) {
                    if (edge.equals(e)) {
                        return e.getVDest();
                    }
                }

                return null;
            }
        }

        return null;
    }

    /**
     * Returns the number of edges leaving the vertex. For an undirected graph,
     * this is the same result returned by inDegree.
     *
     * @param vertex Vertex.
     * @return number of edges leaving the vertex, -1 if vertex doesn't exist.
     */
    @Override
    public int outDegree(Vertex<V, E> vertex) {
        if (this.listVert.contains(vertex)) {
            return vertex.getOutgoing().size();
        } else {
            return -1;
        }
    }

    /**
     * Returns the number of edges for which the vertex is the destination. For
     * an undirected graph, this is the same result returned by outDegree.
     *
     * @param vertex Vertex.
     * @return number of edges leaving the vertex, -1 if vertex doesn't exist.
     */
    @Override
    public int inDegree(Vertex<V, E> vertex) {
        int counter = -1;

        if (!this.listVert.contains(vertex)) {
            return counter;
        }

        if (!this.isDirected) {
            counter = this.outDegree(vertex);
        } else {
            counter = 0;

            for (Vertex<V, E> v : this.listVert) {
                if (v.getOutgoing().containsKey(vertex)) {
                    counter++;
                }
            }
        }

        return counter;
    }

    /**
     * Returns an iterable collection of edges for which the vertex is the
     * origin. For an undirected graph, this is the same result returned by
     * incomingEdges.
     *
     * @param vertex Vertex.
     * @return iterable collection of edges, null if vertex doesn't exist
     */
    @Override
    public Iterable<Edge<V, E>> outgoingEdges(Vertex<V, E> vertex) {
        if (!this.listVert.contains(vertex)) {
            return null;
        }

        ArrayList<Edge<V, E>> edges = new ArrayList<>();

        Map<Vertex<V, E>, Edge<V, E>> map = vertex.getOutgoing();
        Iterator<Map.Entry<Vertex<V, E>, Edge<V, E>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            edges.add(it.next().getValue());
        }

        return edges;
    }

    /**
     * Returns an iterable collection of edges for which the vertex is the
     * destination. For an undirected graph this is the same result as returned
     * by incomingEdges.
     *
     * @param vertex Vertex.
     * @return iterable collection of edges reaching vertex, null if vertex
     * doesn't exist.
     */
    @Override
    public Iterable<Edge<V, E>> incomingEdges(Vertex<V, E> vertex) {

        if (this.listVert.contains(vertex)) {
            if (!this.isDirected) {
                return this.outgoingEdges(vertex);
            } else {
                ArrayList<Edge<V, E>> incomingEdges = new ArrayList<>();

                for (Vertex<V, E> v : this.listVert) {
                    if (v.getOutgoing().containsKey(vertex)) {
                        incomingEdges.add(v.getOutgoing().get(vertex));
                    }
                }

                return incomingEdges;
            }
        }

        return null;
    }

    /**
     * Inserts and returns a new vertex with some specific comparable type.
     *
     * @param vInf the vertex contents.
     * @return a new vertex.
     */
    @Override
    public Vertex<V, E> insertVertex(V vInf) {
        Vertex<V, E> vert = getVertex(vInf);
        if (vert == null) {
            Vertex<V, E> newvert = new Vertex<>(this.numVert, vInf);
            this.listVert.add(newvert);
            this.numVert++;
            return newvert;
        }
        return vert;
    }

    /**
     * Adds and returns a new edge between vertices vOrigInf and vDestInf, with
     * some specific comparable type. If getVertices vOrigInf and vDestInf don't
     * exist in the graph they are inserted.
     *
     * @param vOrigInf Information of vertex source.
     * @param vDestInf Information of vertex destination.
     * @param eInf edge information.
     * @param eWeight edge weight.
     * @return new edge, or null if an edge already exists between the two
     * verts.
     */
    @Override
    public Edge<V, E> insertEdge(V vOrigInf, V vDestInf, E eInf, double eWeight) {
        Vertex<V, E> vorig = getVertex(vOrigInf);
        if (vorig == null) {
            vorig = insertVertex(vOrigInf);
        }

        Vertex<V, E> vdest = getVertex(vDestInf);
        if (vdest == null) {
            vdest = insertVertex(vDestInf);
        }

        if (getEdge(vorig, vdest) == null) {
            Edge<V, E> newEdge = new Edge<>(eInf, eWeight, vorig, vdest);
            vorig.getOutgoing().put(vdest, newEdge);
            this.numEdge++;

            //if graph is not direct insert other edge in the opposite direction 
            if (!this.isDirected) {
                if (getEdge(vdest, vorig) == null) {
                    Edge<V, E> otherEdge = new Edge<>(eInf, eWeight, vdest, vorig);
                    vdest.getOutgoing().put(vorig, otherEdge);
                    this.numEdge++;
                }
            }

            return newEdge;
        }
        return null;
    }

    /**
     * Removes a vertex and all its incident edges from the graph.
     *
     * @param vInf Information of vertex source.
     */
    @Override
    public void removeVertex(V vInf) {
        Vertex<V, E> vertex = getVertex(vInf);

        if (vertex != null) {
            for (Edge edge : getEdges()) {
                if (edge.getVDest().equals(vertex) || edge.getVOrig().equals(vertex)) {
                    removeEdge(edge);
                }
            }

            int index = this.listVert.indexOf(vertex);
            this.listVert.remove(vertex);
            this.numVert--;

            for (int i = index; i < this.listVert.size(); i++) {
                this.listVert.get(i).setKey(this.listVert.get(i).getKey() - 1);
            }
        }
    }

    /**
     * Removes the edge.
     *
     * @param edge Edge to remove.
     */
    @Override
    public void removeEdge(Edge<V, E> edge) {

        Vertex<V, E>[] endpoints = endVertices(edge);

        Vertex<V, E> vorig = endpoints[0];
        Vertex<V, E> vdest = endpoints[1];

        if (vorig != null && vdest != null) {
            if (edge.equals(getEdge(vorig, vdest))) {
                vorig.getOutgoing().remove(vdest);
                this.numEdge--;
            }
        }
    }

    /**
     * Returns the vertex with the information of vInf. Returns null if it
     * doesn't exists.
     *
     * @param vInf Information of the vertex.
     * @return the vertex or null if it doesn't exists.
     */
    public Vertex<V, E> getVertex(V vInf) {

        for (Vertex<V, E> vert : this.listVert) {
            if (vInf.equals(vert.getElement())) {
                return vert;
            }
        }

        return null;
    }

    /**
     * Returns the vertex with the key. Returns null if it doesn't exists.
     *
     * @param vKey key of the vertex.
     * @return the vertex or null if it doesn't exists.
     */
    public Vertex<V, E> getVertex(int vKey) {

        if (vKey < this.listVert.size()) {
            return this.listVert.get(vKey);
        }

        return null;
    }

    /**
     * Returns a clone of the graph.
     *
     * @return clone of the graph.
     */
    @Override
    public Graph<V, E> clone() {

        Graph<V, E> newObject = new Graph<>(this.isDirected);

        newObject.numVert = this.numVert;

        newObject.listVert = new ArrayList<>();
        for (Vertex<V, E> v : this.listVert) {
            newObject.listVert.add(new Vertex<V, E>(v.getKey(), v.getElement()));
        }

        for (Vertex<V, E> v1 : this.listVert) {
            for (Edge<V, E> e : this.outgoingEdges(v1)) {
                if (e != null) {
                    Vertex<V, E> v2 = this.opposite(v1, e);
                    newObject.insertEdge(v1.getElement(), v2.getElement(),
                            e.getElement(), e.getWeight());
                }
            }
        }
        return newObject;
    }

    /**
     * Compares two graphs, first it looks at their location in the memory, then
     * if different it ensures that both the classes are the same and if one of
     * them is not null and finally compares all their attributes.
     *
     *
     * @param oth other graph to test for equality.
     * @return true if both objects represent the same graph.
     */
    public boolean equals(Object oth) {

        if (oth == null) {
            return false;
        }

        if (this == oth) {
            return true;
        }

        if (!(oth instanceof Graph<?, ?>)) {
            return false;
        }

        Graph<?, ?> other = (Graph<?, ?>) oth;

        if (this.numVert != other.numVert || this.numEdge != other.numEdge) {
            return false;
        }

        //graph must have same getVertices
        boolean eqvertex;
        for (Vertex<V, E> v1 : this.getVertices()) {
            eqvertex = false;
            for (Vertex<?, ?> v2 : other.getVertices()) {
                if (v1.equals(v2)) {
                    eqvertex = true;
                }
            }

            if (!eqvertex) {
                return false;
            }
        }

        //graph must have same getEdges
        boolean eqedge;
        for (Edge<V, E> e1 : this.getEdges()) {
            eqedge = false;
            for (Edge<?, ?> e2 : other.getEdges()) {
                if (e1.equals(e2)) {
                    eqedge = true;
                }
            }

            if (!eqedge) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the information of the Graph.
     *
     * @return the information of the Graph.
     */
    @Override
    public String toString() {
        String s = "";
        if (this.numVert == 0) {
            s = "\nGraph not defined!!";
        } else {
            s = "Graph: " + this.numVert + " vertices, " + this.numEdge + " edges\n";
            for (Vertex<V, E> vert : this.listVert) {
                s += vert + "\n";
                if (!vert.getOutgoing().isEmpty()) {
                    for (Map.Entry<Vertex<V, E>, Edge<V, E>> entry : vert.getOutgoing().entrySet()) {
                        s += entry.getValue() + "\n";
                    }
                } else {
                    s += "\n";
                }
            }
        }
        return s;
    }

}
