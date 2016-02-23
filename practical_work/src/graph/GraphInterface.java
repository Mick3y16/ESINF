package graph;

/**
 *
 * @param <V> Generic element which refers to what is contained by the Vertex.
 * @param <E> Generic element which refers to what is considered as weight.
 */
public interface GraphInterface<V, E> {

    /**
     * Returns the number of vertices of the graph.
     *
     * @return the number of vertices of the graph.
     */
    int getNumVertices();

    /**
     * Returns all the vertices of the graph as an iterable collection.
     *
     * @return all the vertices of graph as an iterable collection.
     */
    Iterable<Vertex<V, E>> getVertices();

    /**
     * Returns the number of edges of the graph.
     *
     * @return the number of edges of the graph.
     */
    int getNumEdges();

    /**
     * Returns all the edges of the graph as an iterable collection.
     *
     * @return all the edges of the graph as an iterable collection.
     */
    Iterable<Edge<V, E>> getEdges();

    /**
     * Returns the edge from vOrig to vDest, or null if vertices are not
     * adjacent.
     *
     * @param vOrig Vertex of origin.
     * @param vDest Vertex of destination.
     * @return the edge or null if getVertices are not adjacent or don't exist
     */
    Edge<V, E> getEdge(Vertex<V, E> vOrig, Vertex<V, E> vDest);

    /**
     * Returns the vertices of the edge as an array of length two.
     * If the graph is directed, the first vertex is the origin, and
     * the second is the destination. 
     * If the graph is undirected, the order is arbitrary.
     * 
     * @param edge Edge.
     * @return array of two vertices or null if edge doesn't exist 
     */
    Vertex<V, E>[] endVertices(Edge<V, E> edge);

    /**
     * Returns the vertex that is opposite the vertex on the edge. 
     * 
     * @param vertex Vertex.
     * @param edge Edge.
     * @return opposite vertex, or null if vertex or edge don't exist.
     */
    Vertex<V, E> opposite(Vertex<V, E> vertex, Edge<V, E> edge);

    /**
     * Returns the number of edges leaving the vertex. For an undirected graph,
     * this is the same result returned by inDegree.
     *
     * @param vertex Vertex.
     * @return number of edges leaving the vertex, -1 if vertex doesn't exist.
     */
    int outDegree(Vertex<V, E> vertex);

    /**
     * Returns the number of edges for which the vertex is the destination. 
     * For an undirected graph, this is the same result returned by outDegree.
     *
     * @param vertex Vertex.
     * @return number of edges leaving the vertex, -1 if vertex doesn't exist.
     */
    int inDegree(Vertex<V, E> vertex);

    /**
     * Returns an iterable collection of edges for which the vertex is the origin.
     * For an undirected graph, this is the same result returned by incomingEdges.
     * 
     * @param vertex Vertex.
     * @return iterable collection of edges, null if vertex doesn't exist
     */
    Iterable<Edge<V, E>> outgoingEdges(Vertex<V, E> vertex);

    /**
     * Returns an iterable collection of edges for which the vertex is the destination.
     * For an undirected graph this is the same result as returned by incomingEdges.
     * 
     * @param vertex Vertex.
     * @return iterable collection of edges reaching vertex, null if vertex doesn't exist.
     */
    Iterable<Edge<V, E>> incomingEdges(Vertex<V, E> vertex);

    /**
     * Inserts and returns a new vertex with some specific comparable type.
     * 
     * @param vInf the vertex contents.
     * @return a new vertex.
     */
    Vertex<V, E> insertVertex(V vInf);

    /**
     * Adds and returns a new edge between vertices vOrigInf and vDestInf, with some 
     * specific comparable type. 
     * If getVertices vOrigInf and vDestInf don't exist in the graph they are inserted.
     * 
     * @param vOrigInf Information of vertex source.
     * @param vDestInf Information of vertex destination.
     * @param eInf edge information.
     * @param eWeight edge weight.
     * @return new edge, or null if an edge already exists between the two verts.
     */
    Edge<V, E> insertEdge(V vOrigInf, V vDestInf, E eInf, double eWeight);

    /**
     * Removes a vertex and all its incident edges from the graph.
     * 
     * @param vInf Information of vertex source.
     */
    void removeVertex(V vInf);

    /**
     * Removes the edge.
     * 
     * @param edge Edge to remove.
     */
    void removeEdge(Edge<V, E> edge);

}
