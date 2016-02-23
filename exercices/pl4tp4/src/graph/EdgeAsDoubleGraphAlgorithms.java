package graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author DEI-ESINF
 */
public class EdgeAsDoubleGraphAlgorithms {

    /**
     * Determine the shortest path to all vertices from a vertex using
     * Dijkstra's algorithm To be called by public short method
     *
     * @param graph Graph object
     * @param sourceIdx Source vertex
     * @param knownVertices previously discovered vertices
     * @param verticesIndex index of vertices in the minimum path
     * @param minDist minimum distances in the path
     *
     */
    private static <V> void shortestPath(AdjacencyMatrixGraph<V, Double> graph, int sourceIdx, boolean[] knownVertices, int[] verticesIndex, double[] minDist) {
        for (int vertex = 0; vertex < graph.numVertices; vertex++) {
            minDist[vertex] = Double.POSITIVE_INFINITY;
            knownVertices[vertex] = false;
            verticesIndex[vertex] = -1;
        }

        minDist[sourceIdx] = 0;

        Double edge;
        while (sourceIdx != -1) {
            knownVertices[sourceIdx] = true;

            for (V vAdj : graph.directConnections(graph.vertices.get(sourceIdx))) {
                int vAdjIdx = graph.vertices.indexOf(vAdj);
                edge = graph.privateGet(vAdjIdx, sourceIdx);
                if (!knownVertices[vAdjIdx] && minDist[vAdjIdx] > minDist[sourceIdx] + edge) {
                    minDist[vAdjIdx] = minDist[sourceIdx] + edge;
                    verticesIndex[vAdjIdx] = sourceIdx;
                }
            }
            sourceIdx = getVertMinDist(minDist, knownVertices);
        }
    }

    private static int getVertMinDist(double[] minDist, boolean[] knownVertices) {
        int result = -1;
        double lowest = Double.POSITIVE_INFINITY;
        for (int i = 0; i < minDist.length; i++) {
            if (!knownVertices[i] && minDist[i] < lowest) {
                lowest = minDist[i];
                result = i;
            }
        }
        return result;
    }

    /**
     * Determine the shortest path between two vertices using Dijkstra's
     * algorithm
     *
     * @param <V>
     * @param graph Graph object
     * @param source Source vertex
     * @param dest Destination vertices
     * @param path Returns the vertices in the path (empty if no path)
     * @return minimum distance, -1 if vertices not in graph or no path
     *
     */
    public static <V> double shortestPath(AdjacencyMatrixGraph<V, Double> graph, V source, V dest, LinkedList<V> path) {
        double result = -1;

        if (graph.checkVertex(source) && graph.checkVertex(dest)) {
            boolean knownVertices[] = new boolean[graph.numVertices];
            int verticesIndex[] = new int[graph.numVertices];
            double minDist[] = new double[graph.numVertices];

            int sourceIdx = graph.vertices.indexOf(source);
            int destIdx = graph.vertices.indexOf(dest);
            path.clear();
            shortestPath(graph, sourceIdx, knownVertices, verticesIndex, minDist);

            if (minDist[destIdx] != Double.POSITIVE_INFINITY) {
                recreatePath(graph, sourceIdx, destIdx, verticesIndex, path);

                result = minDist[destIdx];
            }
        }

        return result;
    }

    /**
     * Recreates the minimum path between two vertex, from the result of
     * Dikstra's algorithm
     *
     * @param graph Graph object
     * @param sourceIdx Source vertex
     * @param destIdx Destination vertices
     * @param verticesIndex index of vertices in the minimum path
     * @param Queue Vertices in the path (empty if no path)
     */
    private static <V> void recreatePath(AdjacencyMatrixGraph<V, Double> graph, int sourceIdx, int destIdx, int[] verticesIndex, LinkedList<V> path) {
        path.addFirst(graph.vertices.get(destIdx));
        if (sourceIdx != destIdx) {
            destIdx = verticesIndex[destIdx];
            recreatePath(graph, sourceIdx, destIdx, verticesIndex, path);
        }
    }

    /**
     * Creates new graph with minimum distances between all pairs uses the
     * Floyd-Warshall algorithm
     *
     * @param graph Graph object
     * @return the new graph
     */
    public static <V> AdjacencyMatrixGraph<V, Double> minDistGraph(AdjacencyMatrixGraph<V, Double> graph) throws CloneNotSupportedException {
        AdjacencyMatrixGraph<V, Double> newGraph = (AdjacencyMatrixGraph<V, Double>) graph.clone();

        for (int i = 0; i < newGraph.numVertices; i++) {
            newGraph = GraphAlgorithms.transitiveClosure(newGraph, i + 0.0);
        }
        
        return newGraph;
    }

}
