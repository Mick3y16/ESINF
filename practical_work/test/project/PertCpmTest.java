package project;

import graph.Vertex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class PertCpmTest {

    VariableCostActivity activityA = new VariableCostActivity("A", ActivityCategory.VCA, "High level analysis", 1, TimeCategory.Week, 30, 112);
    FixedCostActivity activityB = new FixedCostActivity("B", ActivityCategory.FCA, "Order Hardware platform", 4, TimeCategory.Week, 2500);
    FixedCostActivity activityC = new FixedCostActivity("C", ActivityCategory.FCA, "Installation and commissioning of hardware", 2, TimeCategory.Week, 1250); // B
    VariableCostActivity activityD = new VariableCostActivity("D", ActivityCategory.VCA, "Detailed analysis of core modules", 3, TimeCategory.Week, 30, 162); // A
    VariableCostActivity activityE = new VariableCostActivity("E", ActivityCategory.VCA, "Detailed analysis of supporting modules", 2, TimeCategory.Week, 30, 108); // D
    VariableCostActivity activityF = new VariableCostActivity("F", ActivityCategory.VCA, "Programming of core modules", 4, TimeCategory.Week, 20, 108); // C, D
    VariableCostActivity activityG = new VariableCostActivity("G", ActivityCategory.VCA, "Programming of supporting modules", 3, TimeCategory.Week, 20, 54); // E, F
    VariableCostActivity activityH = new VariableCostActivity("H", ActivityCategory.VCA, "Quality assurance of core modules", 2, TimeCategory.Week, 30, 54); // F
    VariableCostActivity activityI = new VariableCostActivity("I", ActivityCategory.VCA, "Quality assurance of supporting modules", 1, TimeCategory.Week, 30, 27); // F
    FixedCostActivity activityJ = new FixedCostActivity("J", ActivityCategory.FCA, "Application Manual", 1, TimeCategory.Week, 550); // G
    FixedCostActivity activityK = new FixedCostActivity("K", ActivityCategory.FCA, "User Manual", 1, TimeCategory.Week, 750); // G
    FixedCostActivity activityL = new FixedCostActivity("L", ActivityCategory.FCA, "Core and supporting module training", 2, TimeCategory.Week, 1500); // H, I, K

    public PertCpmTest() {
    }

    /**
     * Test of loadGraph method, of class PertCpm.
     */
    @Test
    public void testLoadGraph() {
        System.out.println("loadGraph");

        ArrayList<Activity> vertexes = new ArrayList<>();
        vertexes.add(activityA);
        vertexes.add(activityB);

        HashMap<String, ArrayList<String>> edges = new HashMap<>();
        edges.put("A", new ArrayList<String>());
        edges.put("B", new ArrayList<String>());

        PertCpm instance = new PertCpm();
        assertTrue("The graph should be loaded and contain no cicles", instance.loadGraph(vertexes, edges));

        ArrayList<String> precedencesOfA = new ArrayList<String>();
        precedencesOfA.add("B");
        edges.replace("A", precedencesOfA);

        ArrayList<String> precedencesOfB = new ArrayList<String>();
        precedencesOfB.add("A");
        edges.replace("B", precedencesOfB);

        assertTrue("The graph should have 4 vertices.", instance.getGraph().getNumVertices() == 4);
        assertTrue("The graph should have 4 edges.", instance.getGraph().getNumEdges() == 4);

        instance = new PertCpm();
        assertFalse("The graph should be loaded and contain a cicle", instance.loadGraph(vertexes, edges));
        assertTrue("And should have been reset", instance.getGraph().getNumVertices() == 2);
    }

    /**
     * Test of topologicalSort method, of class PertCpm.
     */
    @Test
    public void testTopologicalSort() {
        System.out.println("topologicalSort");
        PertCpm instance = new PertCpm();
        
        try {
            instance.topologicalSort();
            assertTrue("An exception should have been thrown as there are no vertices in the graph.", false);
        } catch (UnsupportedOperationException ex) {
        }

        ArrayList<Activity> vertexes = new ArrayList<>();
        vertexes.add(activityA);
        vertexes.add(activityB);
        vertexes.add(activityC);
        vertexes.add(activityD);
        vertexes.add(activityE);
        vertexes.add(activityF);
        vertexes.add(activityG);
        vertexes.add(activityH);
        vertexes.add(activityI);
        vertexes.add(activityJ);
        vertexes.add(activityK);
        vertexes.add(activityL);

        HashMap<String, ArrayList<String>> edges = new HashMap<>();
        edges.put("A", new ArrayList<String>());
        edges.put("B", new ArrayList<String>());
        edges.put("C", new ArrayList<String>(Arrays.asList("B")));
        edges.put("D", new ArrayList<String>(Arrays.asList("A")));
        edges.put("E", new ArrayList<String>(Arrays.asList("D")));
        edges.put("F", new ArrayList<String>(Arrays.asList("C", "D")));
        edges.put("G", new ArrayList<String>(Arrays.asList("E", "F")));
        edges.put("H", new ArrayList<String>(Arrays.asList("F")));
        edges.put("I", new ArrayList<String>(Arrays.asList("G")));
        edges.put("J", new ArrayList<String>(Arrays.asList("G")));
        edges.put("K", new ArrayList<String>(Arrays.asList("G")));
        edges.put("L", new ArrayList<String>(Arrays.asList("H", "I", "K")));

        assertTrue("Safe test, the graph should be loaded with success", instance.loadGraph(vertexes, edges));

        LinkedList<Vertex<Activity, String>> sortedList = instance.topologicalSort();

        // Lets compare the result.
        Iterator<Vertex<Activity, String>> iterator = sortedList.iterator();

        Activity activity = iterator.next().getElement();
        assertTrue("First Activity should be A or B", activity.equals(activityA) || activity.equals(activityB));
        activity = iterator.next().getElement();
        assertTrue("Second Activity should be A or B", activity.equals(activityA) || activity.equals(activityB));
        activity = iterator.next().getElement();
        assertTrue("Third Activity should be D", activity.equals(activityC) || activity.equals(activityD));
        activity = iterator.next().getElement();
        assertTrue("Fourth Activity should be C", activity.equals(activityC) || activity.equals(activityD));
        assertTrue("Fifth Activity should be E", iterator.next().getElement().equals(activityE));
        assertTrue("Sixth Activity should be F", iterator.next().getElement().equals(activityF));
        activity = iterator.next().getElement();
        assertTrue("Seventh Activity should be H or G", activity.equals(activityG) || activity.equals(activityH));
        activity = iterator.next().getElement();
        assertTrue("Eight Activity should be H or G", activity.equals(activityG) || activity.equals(activityH));
        activity = iterator.next().getElement();
        assertTrue("Ninth Activity should be J, I or K", activity.equals(activityJ) || activity.equals(activityI) || activity.equals(activityK));
        activity = iterator.next().getElement();
        assertTrue("Tenth Activity should be J, I or K", activity.equals(activityJ) || activity.equals(activityI) || activity.equals(activityK));
        activity = iterator.next().getElement();
        assertTrue("Eleventh Activity should be J, I or K", activity.equals(activityJ) || activity.equals(activityI) || activity.equals(activityK));
        assertTrue("Twelfth Activity should be L", iterator.next().getElement().equals(activityL));

        // The asserts have to be like this as I found no way to determine which edge would pop out of the map first. :(
    }

    /**
     * Test of calculateEarliestStart method, of class PertCpm.
     */
    @Test
    public void testCalculateEarliestStart() {
        System.out.println("calculateEarliestStart");
        PertCpm instance = new PertCpm();
        
        try {
            instance.topologicalSort();
            assertTrue("An exception should have been thrown as there are no vertices in the graph.", false);
        } catch (UnsupportedOperationException ex) {
        }

        ArrayList<Activity> vertexes = new ArrayList<>();
        vertexes.add(activityA);
        vertexes.add(activityB);
        vertexes.add(activityC);
        vertexes.add(activityD);
        vertexes.add(activityE);
        vertexes.add(activityF);
        vertexes.add(activityG);
        vertexes.add(activityH);
        vertexes.add(activityI);
        vertexes.add(activityJ);
        vertexes.add(activityK);
        vertexes.add(activityL);

        HashMap<String, ArrayList<String>> edges = new HashMap<>();
        edges.put("A", new ArrayList<String>());
        edges.put("B", new ArrayList<String>());
        edges.put("C", new ArrayList<String>(Arrays.asList("B")));
        edges.put("D", new ArrayList<String>(Arrays.asList("A")));
        edges.put("E", new ArrayList<String>(Arrays.asList("D")));
        edges.put("F", new ArrayList<String>(Arrays.asList("C", "D")));
        edges.put("G", new ArrayList<String>(Arrays.asList("E", "F")));
        edges.put("H", new ArrayList<String>(Arrays.asList("F")));
        edges.put("I", new ArrayList<String>(Arrays.asList("G")));
        edges.put("J", new ArrayList<String>(Arrays.asList("G")));
        edges.put("K", new ArrayList<String>(Arrays.asList("G")));
        edges.put("L", new ArrayList<String>(Arrays.asList("H", "I", "K")));

        assertTrue("Safe test, the graph should be loaded with success", instance.loadGraph(vertexes, edges));

        instance.calculateEarliestStart();

        // Lets compare the result.
        Iterator<Vertex<Activity, String>> iterator = instance.getGraph().getVertices().iterator();

        Activity activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 0 and EF to be 0", activity.getEarliestStart() == 0 && activity.getEarliestFinish() == 0);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 16 and EF to be 16", activity.getEarliestStart() == 16 && activity.getEarliestFinish() == 16);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 0 and EF to be 1", activity.getEarliestStart() == 0 && activity.getEarliestFinish() == 1);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 0 and EF to be 4", activity.getEarliestStart() == 0 && activity.getEarliestFinish() == 4);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 4 and EF to be 6", activity.getEarliestStart() == 4 && activity.getEarliestFinish() == 6);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 1 and EF to be 4", activity.getEarliestStart() == 1 && activity.getEarliestFinish() == 4);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 4 and EF to be 6", activity.getEarliestStart() == 4 && activity.getEarliestFinish() == 6);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 6 and EF to be 10", activity.getEarliestStart() == 6 && activity.getEarliestFinish() == 10);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 10 and EF to be 13", activity.getEarliestStart() == 10 && activity.getEarliestFinish() == 13);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 10 and EF to be 12", activity.getEarliestStart() == 10 && activity.getEarliestFinish() == 12);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 13 and EF to be 14", activity.getEarliestStart() == 13 && activity.getEarliestFinish() == 14);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 13 and EF to be 14", activity.getEarliestStart() == 13 && activity.getEarliestFinish() == 14);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 13 and EF to be 14", activity.getEarliestStart() == 13 && activity.getEarliestFinish() == 14);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected ES to be 14 and EF to be 16", activity.getEarliestStart() == 14 && activity.getEarliestFinish() == 16);
    }

    /**
     * Test of calculateLatestFinish method, of class PertCpm.
     */
    @Test
    public void testCalculateLatestFinish() {
        System.out.println("calculateLatestFinish");
        PertCpm instance = new PertCpm();
        
        try {
            instance.topologicalSort();
            assertTrue("An exception should have been thrown as there are no vertices in the graph.", false);
        } catch (UnsupportedOperationException ex) {
        }

        ArrayList<Activity> vertexes = new ArrayList<>();
        vertexes.add(activityA);
        vertexes.add(activityB);
        vertexes.add(activityC);
        vertexes.add(activityD);
        vertexes.add(activityE);
        vertexes.add(activityF);
        vertexes.add(activityG);
        vertexes.add(activityH);
        vertexes.add(activityI);
        vertexes.add(activityJ);
        vertexes.add(activityK);
        vertexes.add(activityL);

        HashMap<String, ArrayList<String>> edges = new HashMap<>();
        edges.put("A", new ArrayList<String>());
        edges.put("B", new ArrayList<String>());
        edges.put("C", new ArrayList<String>(Arrays.asList("B")));
        edges.put("D", new ArrayList<String>(Arrays.asList("A")));
        edges.put("E", new ArrayList<String>(Arrays.asList("D")));
        edges.put("F", new ArrayList<String>(Arrays.asList("C", "D")));
        edges.put("G", new ArrayList<String>(Arrays.asList("E", "F")));
        edges.put("H", new ArrayList<String>(Arrays.asList("F")));
        edges.put("I", new ArrayList<String>(Arrays.asList("G")));
        edges.put("J", new ArrayList<String>(Arrays.asList("G")));
        edges.put("K", new ArrayList<String>(Arrays.asList("G")));
        edges.put("L", new ArrayList<String>(Arrays.asList("H", "I", "K")));

        assertTrue("Safe test, the graph should be loaded with success", instance.loadGraph(vertexes, edges));

        instance.calculateEarliestStart();
        instance.calculateLatestFinish();

        // Lets compare the result.
        Iterator<Vertex<Activity, String>> iterator = instance.getGraph().getVertices().iterator();

        Activity activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 0 and LF to be 0", activity.getLatestStart() == 0 && activity.getLatestFinish() == 0);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 16 and LF to be 16", activity.getLatestStart() == 16 && activity.getLatestFinish() == 16);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 0 and LF to be 1", activity.getLatestStart() == 0 && activity.getLatestFinish() == 1);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 0 and LF to be 4", activity.getLatestStart() == 0 && activity.getLatestFinish() == 4);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 4 and LF to be 6", activity.getLatestStart() == 4 && activity.getLatestFinish() == 6);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 3 and LF to be 6", activity.getLatestStart() == 3 && activity.getLatestFinish() == 6);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 8 and LF to be 10", activity.getLatestStart() == 8 && activity.getLatestFinish() == 10);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 6 and LF to be 10", activity.getLatestStart() == 6 && activity.getLatestFinish() == 10);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 10 and LF to be 13", activity.getLatestStart() == 10 && activity.getLatestFinish() == 13);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 12 and LF to be 14", activity.getLatestStart() == 12 && activity.getLatestFinish() == 14);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 13 and LF to be 14", activity.getLatestStart() == 13 && activity.getLatestFinish() == 14);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 15 and LF to be 16", activity.getLatestStart() == 15 && activity.getLatestFinish() == 16);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 13 and LF to be 14 ", activity.getLatestStart() == 13 && activity.getLatestFinish() == 14);
        activity = iterator.next().getElement();
        assertTrue(activity.getActivity_ID() + ": Expected LS to be 14 and LF to be 16", activity.getLatestStart() == 14 && activity.getLatestFinish() == 16);

    }

    /**
     * Test of completionSort method, of class PertCpm.
     */
    @Test
    public void testCompletionSort() {
        System.out.println("getGraph");
        PertCpm instance = new PertCpm();
        
        try {
            instance.topologicalSort();
            assertTrue("An exception should have been thrown as there are no vertices in the graph.", false);
        } catch (UnsupportedOperationException ex) {
        }

        ArrayList<Activity> vertexes = new ArrayList<>();
        vertexes.add(activityA);
        vertexes.add(activityB);
        vertexes.add(activityC);
        vertexes.add(activityD);
        vertexes.add(activityE);
        vertexes.add(activityF);
        vertexes.add(activityG);
        vertexes.add(activityH);
        vertexes.add(activityI);
        vertexes.add(activityJ);
        vertexes.add(activityK);
        vertexes.add(activityL);

        HashMap<String, ArrayList<String>> edges = new HashMap<>();
        edges.put("A", new ArrayList<String>());
        edges.put("B", new ArrayList<String>());
        edges.put("C", new ArrayList<String>(Arrays.asList("B")));
        edges.put("D", new ArrayList<String>(Arrays.asList("A")));
        edges.put("E", new ArrayList<String>(Arrays.asList("D")));
        edges.put("F", new ArrayList<String>(Arrays.asList("C", "D")));
        edges.put("G", new ArrayList<String>(Arrays.asList("E", "F")));
        edges.put("H", new ArrayList<String>(Arrays.asList("F")));
        edges.put("I", new ArrayList<String>(Arrays.asList("G")));
        edges.put("J", new ArrayList<String>(Arrays.asList("G")));
        edges.put("K", new ArrayList<String>(Arrays.asList("G")));
        edges.put("L", new ArrayList<String>(Arrays.asList("H", "I", "K")));

        assertTrue("Safe test, the graph should be loaded with success", instance.loadGraph(vertexes, edges));

        // If i don't do this calculation the ES of all activities will be 0... ;)
        instance.calculateEarliestStart();

        LinkedList<Activity> sortedList = instance.completionSort();

        assertTrue("Expected activity A", sortedList.remove().equals(activityA));
        assertTrue("Expected activity B", sortedList.remove().equals(activityB));
        assertTrue("Expected activity D", sortedList.remove().equals(activityD));
        assertTrue("Expected activity C", sortedList.remove().equals(activityC));
        assertTrue("Expected activity E", sortedList.remove().equals(activityE));
        assertTrue("Expected activity F", sortedList.remove().equals(activityF));
        assertTrue("Expected activity H", sortedList.remove().equals(activityH));
        assertTrue("Expected activity G", sortedList.remove().equals(activityG));
        assertTrue("Expected activity I", sortedList.remove().equals(activityI));
        assertTrue("Expected activity J", sortedList.remove().equals(activityJ));
        assertTrue("Expected activity K", sortedList.remove().equals(activityK));
        assertTrue("Expected activity L", sortedList.remove().equals(activityL));
        assertTrue("Is the list empty?", sortedList.isEmpty());
    }

    /**
     * Test of toString method, of class PertCpm.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PertCpm instance = new PertCpm();

        ArrayList<Activity> vertexes = new ArrayList<>();
        vertexes.add(activityA);
        vertexes.add(activityB);

        HashMap<String, ArrayList<String>> edges = new HashMap<>();
        edges.put("A", new ArrayList<String>());
        edges.put("B", new ArrayList<String>(Arrays.asList("A")));

        instance.loadGraph(vertexes, edges);
        instance.calculateEarliestStart();
        instance.calculateLatestFinish();

        String expResult = "| Activity | Cost            | Duration | Predecessors | ES    | LS    | EF    | LF    | Slack |\n";
        expResult += "| A     | VCA 3360.0€     | 1.0   | Start      | 0.0   | 0.0   | 1.0   | 1.0   | 0.0   |\n";
        expResult += "| B     | FCA 2500.0€     | 4.0   | StartA     | 1.0   | 1.0   | 5.0   | 5.0   | 0.0   |\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of allPathsAndSize method, of class PertCpm.
     */
    @Test
    public void testAllPathsAndSize() {
        System.out.println("allPathsAndSize");
        PertCpm instance = new PertCpm();
        
        try {
            instance.topologicalSort();
            assertTrue("An exception should have been thrown as there are no vertices in the graph.", false);
        } catch (UnsupportedOperationException ex) {
        }

        ArrayList<Activity> vertexes = new ArrayList<>();
        vertexes.add(activityA);
        vertexes.add(activityB);
        vertexes.add(activityC);
        vertexes.add(activityD);
        vertexes.add(activityE);
        vertexes.add(activityF);
        vertexes.add(activityG);
        vertexes.add(activityH);
        vertexes.add(activityI);
        vertexes.add(activityJ);
        vertexes.add(activityK);
        vertexes.add(activityL);

        HashMap<String, ArrayList<String>> edges = new HashMap<>();
        edges.put("A", new ArrayList<String>());
        edges.put("B", new ArrayList<String>());
        edges.put("C", new ArrayList<String>(Arrays.asList("B")));
        edges.put("D", new ArrayList<String>(Arrays.asList("A")));
        edges.put("E", new ArrayList<String>(Arrays.asList("D")));
        edges.put("F", new ArrayList<String>(Arrays.asList("C", "D")));
        edges.put("G", new ArrayList<String>(Arrays.asList("E", "F")));
        edges.put("H", new ArrayList<String>(Arrays.asList("F")));
        edges.put("I", new ArrayList<String>(Arrays.asList("G")));
        edges.put("J", new ArrayList<String>(Arrays.asList("G")));
        edges.put("K", new ArrayList<String>(Arrays.asList("G")));
        edges.put("L", new ArrayList<String>(Arrays.asList("H", "I", "K")));

        assertTrue("Safe test, the graph should be loaded with success", instance.loadGraph(vertexes, edges));

        LinkedHashMap<LinkedList<Activity>, Double> paths = instance.allPathsAndSize();
        assertTrue("There should be a total of 11 different paths.", paths.size() == 11);

        // Lets go through all paths and see what we got
        Iterator<Entry<LinkedList<Activity>, Double>> iterator = paths.entrySet().iterator();

        // First Path
        Entry<LinkedList<Activity>, Double> entryPath = iterator.next();
        LinkedList<Activity> path = entryPath.getKey();
        Double duration = entryPath.getValue();

        assertTrue("Expected activities", path.size() == 8);
        assertTrue("Expected the duration", duration == 12.0);

        // Second Path
        entryPath = iterator.next();
        path = entryPath.getKey();
        duration = entryPath.getValue();

        assertTrue("Expected activities", path.size() == 7);
        assertTrue("Expected the duration", duration == 10.0);

        // Third Path
        entryPath = iterator.next();
        path = entryPath.getKey();
        duration = entryPath.getValue();

        assertTrue("Expected activities", path.size() == 8);
        assertTrue("Expected the duration", duration == 12.0);

        // Fourth Path
        entryPath = iterator.next();
        path = entryPath.getKey();
        duration = entryPath.getValue();

        assertTrue("Expected activities", path.size() == 8);
        assertTrue("Expected the duration", duration == 14.0);

        // Fifth Path
        entryPath = iterator.next();
        path = entryPath.getKey();
        duration = entryPath.getValue();

        assertTrue("Expected activities", path.size() == 7);
        assertTrue("Expected the duration", duration == 12.0);

        // Sixth Path
        entryPath = iterator.next();
        path = entryPath.getKey();
        duration = entryPath.getValue();

        assertTrue("Expected activities", path.size() == 8);
        assertTrue("Expected the duration", duration == 14.0);

        // Seventh Path
        entryPath = iterator.next();
        path = entryPath.getKey();
        duration = entryPath.getValue();

        assertTrue("Expected activities", path.size() == 7);
        assertTrue("Expected the duration", duration == 12.0);

        // Eigth Path
        entryPath = iterator.next();
        path = entryPath.getKey();
        duration = entryPath.getValue();

        assertTrue("Expected activities", path.size() == 8);
        assertTrue("Expected the duration", duration == 16.0);

        // Ninth Path
        entryPath = iterator.next();
        path = entryPath.getKey();
        duration = entryPath.getValue();

        assertTrue("Expected activities", path.size() == 7);
        assertTrue("Expected the duration", duration == 14.0);

        // Tenth Path
        entryPath = iterator.next();
        path = entryPath.getKey();
        duration = entryPath.getValue();

        assertTrue("Expected activities", path.size() == 8);
        assertTrue("Expected the duration", duration == 16.0);

        // Eleventh Path
        entryPath = iterator.next();
        path = entryPath.getKey();
        duration = entryPath.getValue();

        assertTrue("Expected activities", path.size() == 7);
        assertTrue("Expected the duration", duration == 14.0);
    }

    /**
     * Test of allCriticalPaths method, of class PertCpm.
     */
    @Test
    public void testAllCriticalPaths() {
        System.out.println("allCriticalPaths");
        PertCpm instance = new PertCpm();
        
        try {
            instance.topologicalSort();
            assertTrue("An exception should have been thrown as there are no vertices in the graph.", false);
        } catch (UnsupportedOperationException ex) {
        }

        ArrayList<Activity> vertexes = new ArrayList<>();
        vertexes.add(activityA);
        vertexes.add(activityB);
        vertexes.add(activityC);
        vertexes.add(activityD);
        vertexes.add(activityE);
        vertexes.add(activityF);
        vertexes.add(activityG);
        vertexes.add(activityH);
        vertexes.add(activityI);
        vertexes.add(activityJ);
        vertexes.add(activityK);
        vertexes.add(activityL);

        HashMap<String, ArrayList<String>> edges = new HashMap<>();
        edges.put("A", new ArrayList<String>());
        edges.put("B", new ArrayList<String>());
        edges.put("C", new ArrayList<String>(Arrays.asList("B")));
        edges.put("D", new ArrayList<String>(Arrays.asList("A")));
        edges.put("E", new ArrayList<String>(Arrays.asList("D")));
        edges.put("F", new ArrayList<String>(Arrays.asList("C", "D")));
        edges.put("G", new ArrayList<String>(Arrays.asList("E", "F")));
        edges.put("H", new ArrayList<String>(Arrays.asList("F")));
        edges.put("I", new ArrayList<String>(Arrays.asList("G")));
        edges.put("J", new ArrayList<String>(Arrays.asList("G")));
        edges.put("K", new ArrayList<String>(Arrays.asList("G")));
        edges.put("L", new ArrayList<String>(Arrays.asList("H", "I", "K")));

        assertTrue("Safe test, the graph should be loaded with success", instance.loadGraph(vertexes, edges));
        instance.calculateEarliestStart();
        instance.calculateLatestFinish();
        ArrayList<LinkedList<Activity>> result = instance.allCriticalPaths();
        assertTrue("There should be a total of 2 different paths.", result.size() == 2);
    }

}
