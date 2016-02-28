/*
* A collection of graph algorithms.
 */
package graphbase;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithms {

    /**
     * Performs breadth-first search of a Graph starting in a Vertex
     *
     * @param g Graph instance
     * @param vInf information of the Vertex that will be the source of the
     * search
     * @return qbfs a queue with the vertices of breadth-first search
     */
    public static <V, E> Deque<V> BreadthFirstSearch(Graph<V, E> g, V vInf) {
        Vertex<V, E> vOrig = g.getVertex(vInf);

        if (vOrig == null) {
            return null;
        }

        Deque<V> qbfs = new LinkedList<>();
        Deque<Vertex<V, E>> qaux = new LinkedList<>();
        boolean[] visited = new boolean[g.numVertices()];  //default initializ.: false

        qbfs.add(vOrig.getElement());
        qaux.add(vOrig);
        int vKey = vOrig.getKey();
        visited[vKey] = true;

        while (!qaux.isEmpty()) {
            vOrig = qaux.remove();
            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {
                Vertex<V, E> vAdj = g.opposite(vOrig, edge);
                vKey = vAdj.getKey();
                if (!visited[vKey]) {
                    qbfs.add(vAdj.getElement());
                    qaux.add(vAdj);
                    visited[vKey] = true;
                }
            }
        }
        return qbfs;
    }

    /**
     * Performs depth-first search starting in a Vertex
     *
     * @param g Graph instance
     * @param vOrig Vertex of graph g that will be the source of the search
     * @param visited set of discovered vertices
     * @param qdfs queue with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, Vertex<V, E> vOrig, boolean[] visited, Deque<V> qdfs) {
        qdfs.add(vOrig.getElement());
        visited[vOrig.getKey()] = true;
        for (Edge<V, E> edge : vOrig.getOutgoing().values()) {
            if (!visited[edge.getVDest().getKey()]) {
                DepthFirstSearch(g, edge.getVDest(), visited, qdfs);
            }
        }
    }

    /**
     * @param g Graph instance
     * @param vInf information of the Vertex that will be the source of the
     * search
     * @return qdfs a queue with the vertices of depth-first search
     */
    public static <V, E> Deque<V> DepthFirstSearch(Graph<V, E> g, V vInf) {
        Vertex<V, E> vOrig = g.getVertex(vInf);

        if (vOrig == null) {
            return null;
        }

        Deque<V> qdfs = new ArrayDeque<>();
        DepthFirstSearch(g, vOrig, new boolean[g.numVertices()], qdfs);

        return qdfs;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vDest Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path stack with vertices of the current path (the path is in
     * reverse order)
     * @param paths ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, Vertex<V, E> vOrig, Vertex<V, E> vDest,
            boolean[] visited, Deque<V> path, ArrayList<Deque<V>> paths) {
        visited[vOrig.getKey()] = true;
        path.add(vOrig.getElement());

        for (Edge<V, E> edge : vOrig.getOutgoing().values()) {
            if (edge.getVDest().equals(vDest)) {
                path.add(vDest.getElement());
                paths.add(new LinkedList(path));
                path.removeLast();
            } else if (!visited[edge.getVDest().getKey()]) {
                allPaths(g, edge.getVDest(), vDest, visited, path, paths);
            }
        }

        visited[vOrig.getKey()] = false;
        path.removeLast();
    }

    /**
     * @param g Graph instance
     * @param voInf information of the Vertex origin
     * @param vdInf information of the Vertex destination
     * @return paths ArrayList with all paths from voInf to vdInf
     */
    public static <V, E> ArrayList<Deque<V>> allPaths(Graph<V, E> g, V voInf, V vdInf) {
        ArrayList<Deque<V>> paths = new ArrayList<Deque<V>>();

        allPaths(g, g.getVertex(voInf), g.getVertex(vdInf), new boolean[g.numVertices()], new ArrayDeque<V>(), paths);

        return paths;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights This implementation
     * uses Dijkstra's algorithm
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param visited set of discovered vertices
     * @param pathkeys minimum path vertices keys
     * @param dist minimum distances
     */
    private static <V, E> void shortestPathLength(Graph<V, E> g, Vertex<V, E> vOrig,
            boolean[] visited, int[] pathKeys, double[] dist) {
        int origIndex = vOrig.getKey();
        dist[origIndex] = 0;

        while (origIndex != -1) {
            visited[origIndex] = true;
            vOrig = g.getVertex(origIndex);

            for (Edge<V, E> focus : g.outgoingEdges(vOrig)) {
                int focusIndex = focus.getVDest().getKey();
                Double edgeWeight = focus.getWeight();

                if (!visited[focusIndex] && dist[focusIndex] > dist[origIndex] + edgeWeight) {
                    dist[focusIndex] = dist[origIndex] + edgeWeight;
                    pathKeys[focusIndex] = origIndex;
                }
            }

            origIndex = getVertMinDist(dist, visited);
        }
    }

    private static int getVertMinDist(double[] dist, boolean[] visited) {
        double mindist = Double.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < dist.length; i++) {
            if (mindist > dist[i] && visited[i] == false) {
                mindist = dist[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param g Graph instance
     * @param voInf information of the Vertex origin
     * @param vdInf information of the Vertex destination
     * @param pathkeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(Graph<V, E> g, V voInf, V vdInf, int[] pathKeys, Deque<V> path) {
        if (voInf != vdInf) {
            path.push(vdInf);

            Vertex<V, E> vert = g.getVertex(vdInf);
            int vKey = vert.getKey();
            int prevVKey = pathKeys[vKey];
            vert = g.getVertex(prevVKey);
            vdInf = vert.getElement();

            getPath(g, voInf, vdInf, pathKeys, path);
        } else {
            path.push(voInf);
        }
    }

    //shortest-path between voInf and vdInf
    public static <V, E> double shortestPath(Graph<V, E> g, V voInf, V vdInf, Deque<V> shortPath) {
        Vertex<V, E> vOrig = g.getVertex(voInf);
        Vertex<V, E> vDest = g.getVertex(vdInf);

        if (vOrig == null || vDest == null) {
            return 0;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, visited, pathKeys, dist);

        double lengthPath = dist[vDest.getKey()];

        if (lengthPath != Double.MAX_VALUE) {
            getPath(g, voInf, vdInf, pathKeys, shortPath);
            return lengthPath;
        }
        return 0;
    }

    /**
     * Reverses the path
     *
     * @param path stack with path
     */
    private static <V, E> Deque<V> revPath(Deque<V> path) {

        Deque<V> pathrev = new ArrayDeque<>();

        while (!path.isEmpty()) {
            pathrev.push(path.pop());
        }

        return pathrev;
    }
}
