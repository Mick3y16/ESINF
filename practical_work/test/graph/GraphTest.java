/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import project.Activity;
import project.ActivityCategory;
import project.FixedCostActivity;
import project.TimeCategory;
import project.VariableCostActivity;

/**
 *
 */
public class GraphTest {

    Graph<Activity, String> instance = new Graph<>(true);
    VariableCostActivity activityA = new VariableCostActivity("A", ActivityCategory.VCA, "High level analysis", 1, TimeCategory.Week, 30, 112);
    FixedCostActivity activityB = new FixedCostActivity("B", ActivityCategory.FCA, "Order Hardware platform", 4, TimeCategory.Week, 2500);
    FixedCostActivity activityC = new FixedCostActivity("C", ActivityCategory.FCA, "Installation and commissioning of hardware", 2, TimeCategory.Week, 1250); // B
    VariableCostActivity activityD = new VariableCostActivity("D", ActivityCategory.VCA, "Detailed analysis of core modules", 2, TimeCategory.Week, 30, 162); // A
    VariableCostActivity activityE = new VariableCostActivity("D", ActivityCategory.VCA, "Detailed analysis of supporting modules", 2, TimeCategory.Week, 30, 108); // D

    /*
F,VCA,Programming of core modules,4,Week,20,108,C,D
G,VCA,Programming of supporting modules,3,Week,20,54,E,F
H,VCA,Quality assurance of core modules,2,Week,30,54,F
I,VCA,Quality assurance of supporting modules,1,Week,30,27,G
J,FCA,Application Manual,1,Week,550,G
K,FCA,User Manual,1,Week,750,G
L,FCA,Core and supporting module training,2,Week,1500,H,I,K
     */
    public GraphTest() {
    }

    @Before
    public void setUp() {

    }

    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        System.out.println("Test numVertices");

        assertTrue("result should be zero", (instance.getNumVertices() == 0));

        Vertex<Activity, String> vert = instance.insertVertex(activityA);
        assertTrue("result should be one", (instance.getNumVertices() == 1));
        vert = instance.insertVertex(activityB);
        assertTrue("result should be two", (instance.getNumVertices() == 2));

        instance.removeVertex(activityA);
        assertTrue("result should be one", (instance.getNumVertices() == 1));
        instance.removeVertex(activityB);
        assertTrue("result should be zero", (instance.getNumVertices() == 0));
    }

    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        System.out.println("Test vertices");

        Iterator<Vertex<Activity, String>> itVerts = instance.getVertices().iterator();

        assertTrue("vertices should be empty", itVerts.hasNext() == false);

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);

        itVerts = instance.getVertices().iterator();

        assertTrue("first vertice should be vert1", (itVerts.next().equals(vert1) == true));
        assertTrue("second vertice should be vert2", (itVerts.next().equals(vert2) == true));

        instance.removeVertex(activityA);

        itVerts = instance.getVertices().iterator();
        assertTrue("first vertice should now be vert2", (itVerts.next().equals(vert2)) == true);

        instance.removeVertex(activityB);

        itVerts = instance.getVertices().iterator();
        assertTrue("vertices should now be empty", (itVerts.hasNext() == false));
    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {
        System.out.println("Test numEdges");

        assertTrue("result should be zero", (instance.getNumEdges() == 0));

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        assertTrue("result should be one", (instance.getNumEdges() == 1));

        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        assertTrue("result should be two", (instance.getNumEdges() == 2));

        instance.removeEdge(edge1);
        assertTrue("result should be one", (instance.getNumEdges() == 1));

        instance.removeEdge(edge2);
        assertTrue("result should be zero", (instance.getNumEdges() == 0));
    }

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {
        System.out.println("Test Edges");

        Iterator<Edge<Activity, String>> itEdge = instance.getEdges().iterator();

        assertTrue("edges should be empty", (itEdge.hasNext() == false));

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);

        itEdge = instance.getEdges().iterator();

        itEdge.next();
        itEdge.next();
        assertTrue("third edge should be edge3", (itEdge.next().equals(edge3) == true));
        itEdge.next();
        itEdge.next();
        assertTrue("sixth edge should be edge6", (itEdge.next().equals(edge6) == true));

        instance.removeEdge(edge1);

        itEdge = instance.getEdges().iterator();
        assertTrue("first edge should now be edge2", (itEdge.next().equals(edge2) == true));

        instance.removeEdge(edge2);
        instance.removeEdge(edge3);
        instance.removeEdge(edge4);
        instance.removeEdge(edge5);
        instance.removeEdge(edge6);
        instance.removeEdge(edge7);
        instance.removeEdge(edge8);
        itEdge = instance.getEdges().iterator();
        assertTrue("vertices should now be empty", (itEdge.hasNext() == false));
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        System.out.println("Test getEdge");

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);

        assertTrue("edge should be null", instance.getEdge(vert2, vert5) == null);

        assertTrue("edge should be edge3", instance.getEdge(vert2, vert4).equals(edge3) == true);
        assertTrue("edge should be null", instance.getEdge(vert4, vert2) == null);

        instance.removeEdge(edge6);
        assertTrue("edge should be null", instance.getEdge(vert4, vert1) == null);

        assertTrue("edge should be edge8", instance.getEdge(vert5, vert5).equals(edge8) == true);
    }

    /**
     * Test of endVertices method, of class Graph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("Test endVertices");

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);

        Edge<Activity, String> edge0 = new Edge<>();

        Vertex<Activity, String>[] vertices = instance.endVertices(edge0);

        assertTrue("endVertices should be null", vertices == null);

        vertices = instance.endVertices(edge5);

        Activity v1 = vertices[0].getElement();
        Activity v2 = vertices[1].getElement();

        assertTrue("first vertex should be C", v1.equals(activityC));
        assertTrue("second vertex should be E", v2.equals(activityE));
    }

    /**
     * Test of opposite method, of class Graph.
     */
    @Test
    public void testOpposite() {
        System.out.println("Test opposite");

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);

        Vertex<Activity, String> vert = instance.opposite(vert1, edge5);
        assertTrue("opposite should be null", vert == null);

        vert = instance.opposite(vert1, edge1);
        assertTrue("opposite should be vert2", vert.equals(vert2) == true);

        vert = instance.opposite(vert5, edge8);
        assertTrue("opposite should be vert5", vert.equals(vert5) == true);
    }

    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        System.out.println("Test outDegree");

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);

        Vertex<Activity, String> vert = new Vertex<>();
        int outdeg = instance.outDegree(vert);
        assertTrue("degree should be -1", outdeg == -1);

        outdeg = instance.outDegree(vert1);
        assertTrue("degree should be 2", outdeg == 2);

        outdeg = instance.outDegree(vert2);
        assertTrue("degree should be 1", outdeg == 1);

        outdeg = instance.outDegree(vert5);
        assertTrue("degree should be 2", outdeg == 2);
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        System.out.println("Test inDegree");

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);

        Vertex<Activity, String> vert = new Vertex<>();
        int indeg = instance.inDegree(vert);
        assertTrue("in degree should be -1", indeg == -1);

        indeg = instance.inDegree(vert1);
        assertTrue("in degree should be 1", indeg == 1);

        indeg = instance.inDegree(vert4);
        assertTrue("in degree should be 3", indeg == 3);

        indeg = instance.inDegree(vert5);
        assertTrue("in degree should be 2", indeg == 2);
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        System.out.println(" Test outgoingEdges");

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);

        Iterator<Edge<Activity, String>> itEdge = instance.outgoingEdges(vert3).iterator();
        Edge<Activity, String> first = itEdge.next();
        Edge<Activity, String> second = itEdge.next();
        assertTrue("Outgoing Edges of vert3 should be edge4 and edge5",
                ((first.equals(edge4) == true) && (second.equals(edge5) == true))
                || ((first.equals(edge5) == true) && (second.equals(edge4) == true)));

        itEdge = instance.outgoingEdges(vert5).iterator();
        first = itEdge.next();
        second = itEdge.next();
        assertTrue("Outgoing Edges of vert5 should be edge7 and edge8",
                ((first.equals(edge7) == true || second.equals(edge8) == true)
                || (second.equals(edge7) == true || first.equals(edge8) == true)));

        instance.removeEdge(edge8);

        itEdge = instance.outgoingEdges(vert5).iterator();

        assertTrue("first edge should be edge7", (itEdge.next().equals(edge7) == true));

        instance.removeEdge(edge7);

        itEdge = instance.outgoingEdges(vert5).iterator();
        assertTrue("edges should be empty", (itEdge.hasNext() == false));
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);

        Iterator<Edge<Activity, String>> itEdge = instance.incomingEdges(vert4).iterator();

        assertTrue("first edge should be edge3", (itEdge.next().equals(edge3) == true));
        assertTrue("second edge should be edge4", (itEdge.next().equals(edge4) == true));
        assertTrue("third edge should be edge7", (itEdge.next().equals(edge7) == true));

        itEdge = instance.incomingEdges(vert5).iterator();

        assertTrue("first edge should be edge5", (itEdge.next().equals(edge5) == true));
        assertTrue("second edge should be edge8", (itEdge.next().equals(edge8) == true));

        instance.removeEdge(edge8);

        itEdge = instance.incomingEdges(vert5).iterator();

        assertTrue("first edge should be edge5", (itEdge.next().equals(edge5) == true));

        instance.removeEdge(edge5);

        itEdge = instance.incomingEdges(vert5).iterator();
        assertTrue("edges should be empty", (itEdge.hasNext() == false));
    }

    /**
     * Test of insertVertex method, of class Graph.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("Test insertVertex");

        assertTrue("num. vertices should be zero", (instance.getNumVertices() == 0));

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        assertTrue("Num vertices should be one", (instance.getNumVertices() == 1));
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        assertTrue("Num vertices should be two", (instance.getNumVertices() == 2));
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        assertTrue("Num vertices should be three", (instance.getNumVertices() == 3));
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        assertTrue("Num vertices should be four", (instance.getNumVertices() == 4));
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);
        assertTrue("Num vertices should be five", (instance.getNumVertices() == 5));

        Iterator<Vertex<Activity, String>> itVert = instance.getVertices().iterator();

        assertTrue("first vertex should be vert1", (itVert.next().equals(vert1) == true));
        assertTrue("second vertex should be vert2", (itVert.next().equals(vert2) == true));
        assertTrue("third vertex should be vert3", (itVert.next().equals(vert3) == true));
        assertTrue("fourth vertex should be vert4", (itVert.next().equals(vert4) == true));
        assertTrue("fifth vertex should be vert5", (itVert.next().equals(vert5) == true));
    }

    /**
     * Test of insertEdge method, of class Graph.
     */
    @Test
    public void testInsertEdge() {
        System.out.println("Test insertEdge");

        assertTrue("num. edges should be zero", (instance.getNumEdges() == 0));

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        assertTrue("num. edges should be 1", (instance.getNumEdges() == 1));

        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        assertTrue("num. edges should be 2", (instance.getNumEdges() == 2));

        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        assertTrue("num. edges should be 3", (instance.getNumEdges() == 3));

        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        assertTrue("num. edges should be 4", (instance.getNumEdges() == 4));

        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        assertTrue("num. edges should be 5", (instance.getNumEdges() == 5));

        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        assertTrue("num. edges should be 6", (instance.getNumEdges() == 6));

        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        assertTrue("num. edges should be 7", (instance.getNumEdges() == 7));

        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);
        assertTrue("num. edges should be 8", (instance.getNumEdges() == 8));

        Iterator<Edge<Activity, String>> itEd = instance.getEdges().iterator();

        itEd.next();
        itEd.next();
        assertTrue("third edge should be edge3", (itEd.next().equals(edge3) == true));
        itEd.next();
        itEd.next();
        assertTrue("sixth edge should be edge6", (itEd.next().equals(edge6) == true));

    }

    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("Test removeVertex");

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        instance.removeVertex(activityC);
        assertTrue("Num vertices should be 4", (instance.getNumVertices() == 4));
        assertTrue("Num edges should be 0", (instance.getNumEdges() == 0));

        Iterator<Vertex<Activity, String>> itVert = instance.getVertices().iterator();
        assertTrue("first vertex should be vert1", (itVert.next().equals(vert1) == true));
        assertTrue("second vertex should be vert2", (itVert.next().equals(vert2) == true));
        assertTrue("third vertex should be vert4", (itVert.next().equals(vert4) == true));
        assertTrue("fourth vertex should be vert5", (itVert.next().equals(vert5) == true));

        itVert = instance.getVertices().iterator();
        assertTrue("first vertex key should be 0", itVert.next().getKey() == 0);
        assertTrue("second vertex key should be 1", itVert.next().getKey() == 1);
        assertTrue("third vertex key should be 2", itVert.next().getKey() == 2);
        assertTrue("fourth vertex key should be 3", itVert.next().getKey() == 3);

        instance.insertEdge(activityA, activityB, "AB", 0.0);
        instance.insertEdge(activityA, activityD, "AD", 0.0);
        instance.insertEdge(activityD, activityB, "DB", 0.0);
        instance.insertEdge(activityE, activityA, "EA", 0.0);
        
        assertTrue("Num edges should be 4", (instance.getNumEdges() == 4));

        instance.removeVertex(activityA);

        assertTrue("Num vertices should be 3", (instance.getNumVertices() == 3));
        assertTrue("Num edges should be 1", (instance.getNumEdges() == 1));
        itVert = instance.getVertices().iterator();

        assertTrue("first vertex should be vert2", (itVert.next().equals(vert2) == true));
        assertTrue("second vertex should be vert4", (itVert.next().equals(vert4) == true));
        assertTrue("third vertex should be vert5", (itVert.next().equals(vert5) == true));

        instance.removeVertex(activityE);
        assertTrue("Num vertices should be 2", (instance.getNumVertices() == 2));

        itVert = instance.getVertices().iterator();

        assertTrue("first vertex should be vert2 but its key should be 0",
                (itVert.next().equals(vert2) == true && vert2.getKey() == 0));
        assertTrue("second vertex should be vert4 but its key should be 1",
                (itVert.next().equals(vert4) == true && vert4.getKey() == 1));
    }

    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {
        System.out.println("Test removeEdge");

        assertTrue("num. edges should be zero", (instance.getNumEdges() == 0));

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);

        assertTrue("Num. edges should be 8", (instance.getNumEdges() == 8));

        instance.removeEdge(edge8);
        assertTrue("Num. edges should be 7", (instance.getNumEdges() == 7));

        Iterator<Edge<Activity, String>> itEd = instance.getEdges().iterator();

        itEd.next();
        itEd.next();
        assertTrue("third edge should be edge3", (itEd.next().equals(edge3) == true));
        itEd.next();
        itEd.next();
        assertTrue("sixth edge should be edge6", (itEd.next().equals(edge6) == true));

        instance.removeEdge(edge4);
        assertTrue("Num. edges should be 6", (instance.getNumEdges() == 6));

        itEd = instance.getEdges().iterator();
        itEd.next();
        itEd.next();
        assertTrue("third edge should be edge3", (itEd.next().equals(edge3) == true));
        assertTrue("fourth edge should be edge5", (itEd.next().equals(edge5) == true));
        assertTrue("fifth edge should be edge6", (itEd.next().equals(edge6) == true));
        assertTrue("...last edge should be edge7", (itEd.next().equals(edge7) == true));
    }

    /**
     * Test of getVertex method, of class Graph.
     */
    @Test
    public void testGetVertex_GenericType() {
        System.out.println("Test getVertex Generic");

        assertTrue("vert should be null", (instance.getVertex(activityC) == null));

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        assertTrue("vert should be vert1", (instance.getVertex(activityA).equals(vert1)));
        assertTrue("vert should be vert2", (instance.getVertex(activityB).equals(vert2)));
        assertTrue("vert should be vert5", (instance.getVertex(activityE).equals(vert5)));
        assertTrue("vert should be vert3", (instance.getVertex(activityC).equals(vert3)));
        assertTrue("vert should be vert4", (instance.getVertex(activityD).equals(vert4)));
    }

    /**
     * Test of getVertex method, of class Graph.
     */
    @Test
    public void testGetVertex_int() {
        System.out.println("Test getVertex indice");

        assertTrue("vert should be null", (instance.getVertex(1) == null));

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        assertTrue("vert should be vert1", (instance.getVertex(0).equals(vert1)));
        assertTrue("vert should be vert2", (instance.getVertex(1).equals(vert2)));
        assertTrue("vert should be vert5", (instance.getVertex(4).equals(vert5)));
        assertTrue("vert should be vert3", (instance.getVertex(2).equals(vert3)));
        assertTrue("vert should be vert4", (instance.getVertex(3).equals(vert4)));
    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {
        System.out.println("Test Clone");

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);

        Graph<Activity, String> instClone = instance.clone();

        assertTrue("number of vertices should be equal", instance.getNumVertices() == instClone.getNumVertices());
        assertTrue("number of edges should be equal", instance.getNumEdges() == instClone.getNumEdges());

        //vertices should be equal
        Iterator<Vertex<Activity, String>> itvertClone = instClone.getVertices().iterator();
        Iterator<Vertex<Activity, String>> itvertSource = instance.getVertices().iterator();
        while (itvertSource.hasNext()) {
            assertTrue("vertices should be equal ", (itvertSource.next().equals(itvertClone.next()) == true));
        }

        //and edges also
        Iterator<Edge<Activity, String>> itedgeSource = instance.getEdges().iterator();
        while (itedgeSource.hasNext()) {
            Iterator<Edge<Activity, String>> itedgeClone = instClone.getEdges().iterator();
            boolean exists = false;
            while (itedgeClone.hasNext()) {
                if (itedgeSource.next().equals(itedgeClone.next())) {
                    exists = true;
                }
            }
            assertTrue("edges should be equal ", (exists == true));
        }
    }

    @Test
    public void testEquals() {
        System.out.println("Test Equals");

        Vertex<Activity, String> vert1 = instance.insertVertex(activityA);
        Vertex<Activity, String> vert2 = instance.insertVertex(activityB);
        Vertex<Activity, String> vert3 = instance.insertVertex(activityC);
        Vertex<Activity, String> vert4 = instance.insertVertex(activityD);
        Vertex<Activity, String> vert5 = instance.insertVertex(activityE);

        Edge<Activity, String> edge1 = instance.insertEdge(activityA, activityB, "AB", 6);
        Edge<Activity, String> edge2 = instance.insertEdge(activityA, activityC, "AC", 1);
        Edge<Activity, String> edge3 = instance.insertEdge(activityB, activityD, "BD", 3);
        Edge<Activity, String> edge4 = instance.insertEdge(activityC, activityD, "CD", 4);
        Edge<Activity, String> edge5 = instance.insertEdge(activityC, activityE, "CE", 1);
        Edge<Activity, String> edge6 = instance.insertEdge(activityD, activityA, "DA", 2);
        Edge<Activity, String> edge7 = instance.insertEdge(activityE, activityD, "ED", 1);
        Edge<Activity, String> edge8 = instance.insertEdge(activityE, activityE, "EE", 1);

        assertFalse("should not be equal to null", instance.equals(null));

        assertTrue("should be equal to itself", instance.equals(instance));

        assertTrue("should be equal to a clone", instance.equals(instance.clone()));

        Graph<Activity, String> other = instance.clone();
        Edge<Activity, String> edge = other.getEdge(other.getVertex(activityE), other.getVertex(activityE));
        other.removeEdge(edge);
        assertFalse("instance should not be equal to other", instance.equals(other));

        other.insertEdge(activityE, activityE, "EE", 1);
        assertTrue("instance should be equal to other", instance.equals(other));

        other.removeVertex(activityD);
        assertFalse("instance should not be equal to other", instance.equals(other));

    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testToString() {
        System.out.println(instance);
    }

}
