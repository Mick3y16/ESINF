package project;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents a class which contains a graph of a project, linking all of its
 * activities from start to finish. This class allows the user to perform
 * various calculus common to the PERT or CPM methodologies.
 */
public class PertCpm {

    /**
     * DummyClass that is used to represent the source and finish nodes of the
     * graph.
     */
    private class DummyActivity extends Activity {

        public DummyActivity(String Id) {
            super(Id, null, "Dummy Node", 0, null);
        }

        @Override
        public String getTypeAndCost() {
            return null;
        }

    }

    /**
     * Graph containing the activities of a project correctly linked with each
     * other.
     */
    private Graph<Activity, String> graph;

    /**
     * Source node which precedes the first activity or activities of the
     * project.
     */
    private Vertex<Activity, String> sourceNode;

    /**
     * Finish node which succedes the last activity or activities of the
     * project.
     */
    private Vertex<Activity, String> finishNode;

    /**
     * Creates an instance of a PertCpm through an empty graph and the start and
     * finish nodes.
     */
    public PertCpm() {
        this.graph = new Graph(true);
        this.sourceNode = graph.insertVertex(new DummyActivity("Start"));
        this.finishNode = graph.insertVertex(new DummyActivity("Finish"));
    }

    /**
     * Returns the graph of the project.
     *
     * @return Graph of the project.
     */
    public Graph<Activity, String> getGraph() {
        return this.graph;
    }

    /**
     * Returns the information about all the activities of the entire project.
     *
     * @return Information about the project.
     */
    @Override
    public String toString() {
        String output = String.format(
                "| %-5s | %-15s | %-5s | %-10s | %-5s | %-5s | %-5s | %-5s | %-5s |\n",
                "Activity", "Cost", "Duration", "Predecessors", "ES", "LS", "EF", "LF", "Slack");

        String predecessors = "";
        for (Vertex<Activity, String> vertex : this.graph.getVertices()) {
            if (!vertex.equals(this.sourceNode) && !vertex.equals(this.finishNode)) {
                Activity activity = vertex.getElement();

                for (Edge<Activity, String> edge : this.graph.incomingEdges(vertex)) {
                    predecessors += edge.getVOrig().getElement().getActivity_ID();
                }

                output += String.format(
                        "| %-5s | %-15s | %-5s | %-10s | %-5s | %-5s | %-5s | %-5s | %-5s |\n",
                        activity.getActivity_ID(), activity.getTypeAndCost(),
                        activity.getDuration(), predecessors,
                        activity.getEarliestStart(), activity.getLatestStart(),
                        activity.getEarliestFinish(), activity.getLatestFinish(),
                        activity.getSlack());
            }
        }

        return output;
    }

    /**
     * Loads a set of activities into a graph forming a project, the algorithm
     * inserts each activity as a vertex and links all with edges. After doing
     * so the algorithm checks the in and out degrees of each activity, linking
     * them to a dummy node if they are the start of finish activities. In the
     * end we verify the existence of cicles, as their existence is prohibited.
     *
     * @param vertexes Arraylist of the activities of the project.
     * @param edges HashMap which states the activities that precede another.
     *
     * @return True if the graph is loaded successfuly and has no cicles within
     * or false if it has cicles.
     */
    public boolean loadGraph(ArrayList<Activity> vertexes,
            HashMap<String, ArrayList<String>> edges) {
        // Insert every activity in the arraylist as a vertex in the graph.
        for (Activity vertex : vertexes) {
            this.graph.insertVertex(vertex);
        }

        /* Link every vertex according to the HashMap which contains the 
           activities that precede others. */
        for (Map.Entry<String, ArrayList<String>> activKeys : edges.entrySet()) {
            Activity vDest = getActivity(activKeys.getKey());

            if (!activKeys.getValue().isEmpty()) {
                for (String destKey : activKeys.getValue()) {
                    Activity vOrig = getActivity(destKey);

                    this.graph.insertEdge(vOrig, vDest, vOrig.getActivity_ID()
                            + "->" + vOrig.getActivity_ID(), vOrig.getDuration());
                }
            }
        }

        // Link all vertices whose in degree is 0 to the start node.
        for (Vertex<Activity, String> vertex : this.graph.getVertices()) {
            if (this.graph.inDegree(vertex) == 0
                    && !vertex.equals(this.sourceNode)
                    && !vertex.equals(this.finishNode)) {
                this.graph.insertEdge(
                        this.sourceNode.getElement(),
                        vertex.getElement(),
                        this.sourceNode.getElement().getActivity_ID() + "->"
                        + vertex.getElement().getActivity_ID(),
                        this.sourceNode.getElement().getDuration());
            }
        }

        // Link all vertices whose out degree is 0 to the finish node.
        for (Vertex<Activity, String> vertex : this.graph.getVertices()) {
            if (this.graph.outDegree(vertex) == 0
                    && !vertex.equals(this.sourceNode)
                    && !vertex.equals(this.finishNode)) {
                this.graph.insertEdge(
                        vertex.getElement(),
                        this.finishNode.getElement(),
                        vertex.getElement().getActivity_ID() + "->"
                        + this.finishNode.getElement().getActivity_ID(),
                        vertex.getElement().getDuration());
            }
        }

        // Validate if the graph has no cicles.
        boolean result = isAcyclic();

        /* If the graph has cicles, reset it to the point where it only had the
           dummy nodes */
        if (!result) {
            for (int i = this.graph.getNumVertices() - 1; i > 1; i--) {
                this.graph.removeVertex(this.graph.getVertex(i).getElement());
            }
        }

        return result;
    }

    /**
     * Returns a topologicaly sorted list of all the vertices in the graph. This
     * means the sort will be done by their order of completion
     *
     * @return Topologicaly sorted list.
     */
    public LinkedList<Vertex<Activity, String>> topologicalSort() {
        if (this.graph.getNumVertices() == 2) {
            // We can't topological sort a graph without vertices.
            throw new UnsupportedOperationException();
        }
        
        LinkedList<Vertex<Activity, String>> sortedList = new LinkedList<>();
        LinkedList<Vertex<Activity, String>> queue = new LinkedList<>();
        LinkedList<Edge<Activity, String>> removedEdges = new LinkedList<>();

        // Add the only node into the graph without in degree.
        queue.add(this.sourceNode);

        while (!queue.isEmpty()) {
            Vertex<Activity, String> vOrig = queue.remove();
            sortedList.add(vOrig);

            for (Edge<Activity, String> edge : vOrig.getOutgoing().values()) {
                Vertex<Activity, String> vDest = edge.getVDest();

                // "Remove" edge.
                removedEdges.add(edge);

                if (this.graph.inDegree(vDest) - countRemovedEdgesFromVertex(
                        removedEdges, vDest) == 0) {
                    queue.add(vDest);
                }
            }
        }

        /* Remove both source and finish nodes as they are not real activities
           of the project. */
        sortedList.removeFirst();
        sortedList.removeLast();

        return sortedList;
    }

    /**
     * Calculates the ES of each activity in the graph, showing how early each
     * activity can start in the project. The finish node will contain the
     * project's duration.
     */
    public void calculateEarliestStart() {
        if (this.graph.getNumVertices() == 2) {
            // We can't calculate the earliest start in a graph without vertices.
            throw new UnsupportedOperationException();
        }
        
        LinkedList<Vertex<Activity, String>> sortedList = topologicalSort();
        // Adding the finish node to the list just so its ES gets updated.
        sortedList.add(this.finishNode);

        while (!sortedList.isEmpty()) {
            Vertex<Activity, String> vDest = sortedList.remove();

            for (Edge<Activity, String> edge : this.graph.incomingEdges(vDest)) {
                Vertex<Activity, String> vOrig = edge.getVOrig();

                double ESTime = vOrig.getElement().getEarliestStart()
                        + vOrig.getElement().getDuration();
                if (vDest.getElement().getEarliestStart() < ESTime) {
                    vDest.getElement().setEarliestStart(ESTime);
                }
            }
        }
    }

    /**
     * Calculates the LF of each activity in the graph, showing how early each
     * activity can finish in the project.
     */
    public void calculateLatestFinish() {
        if (this.graph.getNumVertices() == 2) {
            // We can't calculate the latest finish in a graph without vertices.
            throw new UnsupportedOperationException();
        }
        
        LinkedList<Vertex<Activity, String>> sortedList = topologicalSort();
        sortedList.add(this.finishNode);
        Collections.reverse(sortedList);
        sortedList.add(this.sourceNode);

        /* Set the latest finish of the finish node to be the same as the
           earliest finish. */
        this.finishNode.getElement().setLatestFinish(
                this.finishNode.getElement().getEarliestFinish());

        while (!sortedList.isEmpty()) {
            Vertex<Activity, String> vOrig = sortedList.remove();

            for (Edge<Activity, String> edge : this.graph.outgoingEdges(vOrig)) {
                Vertex<Activity, String> vDest = edge.getVDest();

                double LFTime = vDest.getElement().getEarliestFinish()
                        - vDest.getElement().getDuration();
                if (vOrig.getElement().getLatestFinish() < LFTime) {
                    vOrig.getElement().setLatestFinish(LFTime);
                }
            }
        }
    }

    /**
     * Returns a list of activities by their order of completion, which means
     * the ones with the lowest EF come first.
     *
     * @return List of activities by their order of completion.
     */
    public LinkedList<Activity> completionSort() {
        if (this.graph.getNumVertices() == 2) {
            // We can't calculate the sort a graph without vertices.
            throw new UnsupportedOperationException();
        }

        LinkedList<Activity> sortedList = new LinkedList<>();

        for (Vertex<Activity, String> vertex : this.graph.getVertices()) {
            if (!vertex.equals(sourceNode) && !vertex.equals(finishNode)) {
                sortedList.add(vertex.getElement());
            }
        }

        Collections.sort(sortedList, new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                if (o1.getEarliestFinish() > o2.getEarliestFinish()) {
                    return 1;
                } else if (o1.getEarliestFinish() < o2.getEarliestFinish()) {
                    return -1;
                } else if (o1.getEarliestStart() < o2.getEarliestStart()) {
                    return -1;
                } else if (o1.getEarliestStart() > o2.getLatestFinish()) {
                    return 1;
                }
                
                return 0;
            }
        });

        return sortedList;
    }

    /**
     * Returns all the Paths between the start and finish of the project.
     *
     * @return All the paths between the start and finish of the project.
     */
    public LinkedHashMap<LinkedList<Activity>, Double> allPathsAndSize() {
        if (this.graph.getNumVertices() == 2) {
            // We can't calculate all the paths in a graph without vertices.
            throw new UnsupportedOperationException();
        }
        
        LinkedHashMap<LinkedList<Activity>, Double> paths = new LinkedHashMap<>();
        Vertex<Activity, String> vOrig = this.sourceNode;
        Vertex<Activity, String> vDest = this.finishNode;

        allPathsAndSize(vOrig, vDest, new boolean[this.graph.getNumVertices()],
                new LinkedList<Activity>(), paths);

        return paths;
    }

    /**
     *
     * @return paths ArrayList with all paths from one activity to the other one
     */
    public ArrayList<LinkedList<Activity>> allCriticalPaths() {
        if (this.graph.getNumVertices() == 2) {
            // We can't calculate all the critical paths in a graph without vertices.
            throw new UnsupportedOperationException();
        }
        
        ArrayList<LinkedList<Activity>> paths = new ArrayList<>();
        Vertex<Activity, String> vOrig = this.sourceNode;
        Vertex<Activity, String> vDest = this.finishNode;

        allCriticalPaths(vOrig, vDest, new boolean[this.graph.getNumVertices()],
                new LinkedList<Activity>(), paths);

        return paths;
    }

    /**
     * Returns all the Paths between two activities.
     *
     * @param visited Array of boolean to defined the already visited vertexes.
     * @param path LinkedList used to contain intire path between two
     * activities.
     * @param paths LinkedHashMap that will contain all the paths between two
     * activities and their duration.
     */
    private void allPathsAndSize(Vertex<Activity, String> vOrig,
            Vertex<Activity, String> vDest, boolean[] visited,
            LinkedList<Activity> path,
            LinkedHashMap<LinkedList<Activity>, Double> paths) {
        visited[vOrig.getKey()] = true;
        path.add(vOrig.getElement());

        for (Edge<Activity, String> edge : vOrig.getOutgoing().values()) {
            if (edge.getVDest().equals(vDest)) {
                path.add(vDest.getElement());
                double duration = calculatePathDuration(path);
                paths.put(new LinkedList(path), duration);
                path.removeLast();
            } else if (!visited[edge.getVDest().getKey()]) {
                allPathsAndSize(edge.getVDest(), vDest, visited, path, paths);
            }
        }

        visited[vOrig.getKey()] = false;
        path.removeLast();
    }

    /**
     * Calculates and returns the duration of a path of activities.
     *
     * @param path Full path with activities.
     * @return Returns the duration of a path of activities.
     */
    private double calculatePathDuration(LinkedList<Activity> path) {
        double duration = 0;

        for (Activity activity : path) {
            duration += activity.getDuration();
        }

        return duration;
    }

    /**
     * Returns all critical paths (slack=0) from one activity(vOrig) to other
     * (vDest).
     *
     * @param vOrig Activity that will be the source of the path
     * @param vDest Activity that will be the end of the path
     * @param visited set of discovered activities
     * @param path stack with activities of the current path (the path is in
     * reverse order)
     * @param paths ArrayList with all the paths (in correct order)
     */
    private void allCriticalPaths(Vertex<Activity, String> vOrig,
            Vertex<Activity, String> vDest, boolean[] visited,
            LinkedList<Activity> path, ArrayList<LinkedList<Activity>> paths) {
        visited[vOrig.getKey()] = true;
        path.add(vOrig.getElement());

        for (Edge<Activity, String> edge : vOrig.getOutgoing().values()) {
            if (edge.getVDest().equals(vDest)) {
                path.add(vDest.getElement());
                paths.add(new LinkedList(path));
                path.removeLast();
            } else if (!visited[edge.getVDest().getKey()] && edge.getVDest().getElement().getSlack() == 0) {
                allCriticalPaths(edge.getVDest(), vDest, visited, path, paths);
            }
        }

        visited[vOrig.getKey()] = false;
        path.removeLast();
    }

    /**
     * Checks if a graph is acyclic or not, by jumping between all the vertices
     * that have no in degree, removing the edges that connect them to other
     * vertices.
     *
     * @return True if the graph has no cicles or false if atleast one is found.
     */
    private boolean isAcyclic() {
        LinkedList<Edge<Activity, String>> removedEdges
                = new LinkedList<Edge<Activity, String>>();
        LinkedList<Vertex<Activity, String>> vertexList
                = new LinkedList<Vertex<Activity, String>>();

        // The only node without incoming edges.
        vertexList.add(this.sourceNode);

        while (!vertexList.isEmpty()) {
            Vertex<Activity, String> vOrig = vertexList.remove();

            for (Edge<Activity, String> edge : vOrig.getOutgoing().values()) {
                Vertex<Activity, String> vDest = edge.getVDest();

                removedEdges.add(edge);

                if (this.graph.inDegree(vDest) - countRemovedEdgesFromVertex(
                        removedEdges, vDest) == 0) {
                    vertexList.add(vDest);
                }
            }
        }

        return this.graph.getNumEdges() - removedEdges.size() == 0;
    }

    /**
     * Counts the number of edges in the list which have the vertex as the
     * vertex of destination.
     *
     * @param removedEdgesList List that contains the edges "removed" from the
     * graph.
     * @param vertex Vertex that will serve to count the number of edges
     * "removed", associated with it.
     * @return The number of edges in the list which have the vertex as the
     * vertex of destination.
     */
    private int countRemovedEdgesFromVertex(
            List<Edge<Activity, String>> removedEdgesList,
            Vertex<Activity, String> vertex) {
        int removedEdges = 0;

        for (Edge<Activity, String> edge : removedEdgesList) {
            if (edge.getVDest().equals(vertex)) {
                removedEdges++;
            }
        }

        return removedEdges;
    }

    /**
     * Returns the vertex which contains a specific activity with the ID given.
     *
     * @param activityID ID of the activity to return.
     * @return Returns the activity which contains the ID recieved by parameter
     * or null if the activity is not found.
     */
    private Activity getActivity(String activityID) {
        for (Vertex<Activity, String> vertex : this.graph.getVertices()) {
            if (vertex.getElement().getActivity_ID().equals(activityID)) {
                return vertex.getElement();
            }
        }

        return null;
    }

}
