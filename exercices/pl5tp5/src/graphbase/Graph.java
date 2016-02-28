package graphbase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Graph<V, E> implements GraphInterface<V, E> {

    private int numVert;
    private int numEdge;
    private final boolean isDirected;
    private ArrayList<Vertex<V, E>> listVert;  //Vertice list

    // Constructs an empty graph (either undirected or directed)
    public Graph(boolean directed) {
        numVert = 0;
        numEdge = 0;
        isDirected = directed;
        listVert = new ArrayList<>();
    }

    @Override
    public int numVertices() {
        return numVert;
    }

    @Override
    public Iterable<Vertex<V, E>> vertices() {
        return listVert;
    }

    @Override
    public int numEdges() {
        return numEdge;
    }

    @Override
    public Iterable<Edge<V, E>> edges() {
        ArrayList<Edge<V, E>> listEdges = new ArrayList<>();

        for (Vertex<V, E> vertex : this.listVert) {
            listEdges.addAll(vertex.getOutgoing().values());
        }

        return listEdges;
    }

    @Override
    public Edge<V, E> getEdge(Vertex<V, E> vorig, Vertex<V, E> vdest) {
        if (listVert.contains(vorig) && listVert.contains(vdest)) {
            return vorig.getOutgoing().get(vdest);
        }

        return null;
    }

    @Override
    public Vertex<V, E>[] endVertices(Edge<V, E> e) {
        for (Vertex<V, E> vertex : this.listVert) {
            for (Edge<V, E> edge : vertex.getOutgoing().values()) {
                if (edge.equals(e)) {
                    return e.getEndpoints();
                }
            }
        }

        return null;
    }

    @Override
    public Vertex<V, E> opposite(Vertex<V, E> vert, Edge<V, E> e) {
        for (Vertex<V, E> v : this.listVert) {
            if (v.equals(vert)) {
                for (Edge<V, E> edge : vert.getOutgoing().values()) {
                    if (edge.equals(e)) {
                        return e.getVDest();
                    }
                }
            }
        }

        return null;
    }

    @Override
    public int outDegree(Vertex<V, E> v) {
        if (listVert.contains(v)) {
            return v.getOutgoing().size();
        } else {
            return -1;
        }
    }

    @Override
    public int inDegree(Vertex<V, E> v) {
        if (!this.isDirected) {
            return this.outDegree(v);
        }

        if (this.listVert.contains(v)) {
            int cont = 0;

            for (Vertex<V, E> vert : this.listVert) {
                if (vert.getOutgoing().containsKey(v)) {
                    cont++;
                }
            }

            return cont;
        }

        return -1;
    }

    @Override
    public Iterable<Edge<V, E>> outgoingEdges(Vertex<V, E> v) {
        if (!listVert.contains(v)) {
            return null;
        }

        ArrayList<Edge<V, E>> edges = new ArrayList<>();

        Map<Vertex<V, E>, Edge<V, E>> map = v.getOutgoing();
        Iterator<Map.Entry<Vertex<V, E>, Edge<V, E>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            edges.add(it.next().getValue());
        }

        return edges;
    }

    @Override
    public Iterable<Edge<V, E>> incomingEdges(Vertex<V, E> v) {
        if (!this.isDirected) {
            return this.outgoingEdges(v);
        }

        ArrayList<Edge<V, E>> list = new ArrayList<>();

        for (Vertex<V, E> vert : this.listVert) {
            if (vert.getOutgoing().containsKey(v)) {
                list.add(vert.getOutgoing().get(v));
            }
        }

        return list;
    }

    @Override
    public Vertex<V, E> insertVertex(V vInf) {
        Vertex<V, E> vert = getVertex(vInf);

        if (vert == null) {
            Vertex<V, E> newvert = new Vertex<>(numVert, vInf);
            listVert.add(newvert);
            numVert++;

            return newvert;
        }

        return vert;
    }

    @Override
    public Edge<V, E> insertEdge(V vOrig, V vDest, E eInf, double eWeight) {
        Vertex<V, E> vorig = getVertex(vOrig);
        if (vorig == null) {
            vorig = insertVertex(vOrig);
        }

        Vertex<V, E> vdest = getVertex(vDest);
        if (vdest == null) {
            vdest = insertVertex(vDest);
        }

        if (getEdge(vorig, vdest) == null) {
            Edge<V, E> newedge = new Edge<>(eInf, eWeight, vorig, vdest);
            vorig.getOutgoing().put(vdest, newedge);
            numEdge++;

            //if graph is not direct insert other edge in the opposite direction 
            if (!isDirected) {
                if (getEdge(vdest, vorig) == null) {
                    Edge<V, E> otheredge = new Edge<>(eInf, eWeight, vdest, vorig);
                    vdest.getOutgoing().put(vorig, otheredge);
                    numEdge++;
                }
            }

            return newedge;
        }

        return null;
    }

    @Override
    public void removeVertex(V vInf) {
        Vertex<V, E> vertex = getVertex(vInf);

        if (vertex != null) {
            for (Edge edge : edges()) {
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

    @Override
    public void removeEdge(Edge<V, E> edge) {

        Vertex<V, E>[] endpoints = endVertices(edge);

        Vertex<V, E> vorig = endpoints[0];
        Vertex<V, E> vdest = endpoints[1];

        if (vorig != null && vdest != null) {
            if (edge.equals(getEdge(vorig, vdest))) {
                vorig.getOutgoing().remove(vdest);
                numEdge--;
            }
        }
    }

    public Vertex<V, E> getVertex(V vInf) {

        for (Vertex<V, E> vert : this.listVert) {
            if (vInf.equals(vert.getElement())) {
                return vert;
            }
        }

        return null;
    }

    public Vertex<V, E> getVertex(int vKey) {

        if (vKey < listVert.size()) {
            return listVert.get(vKey);
        }

        return null;
    }

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

    @Override
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

        if (numVert != other.numVert || numEdge != other.numEdge) {
            return false;
        }

        //graph must have same vertices
        boolean eqvertex;
        for (Vertex<V, E> v1 : this.vertices()) {
            eqvertex = false;
            for (Vertex<?, ?> v2 : other.vertices()) {
                if (v1.equals(v2)) {
                    eqvertex = true;
                }
            }

            if (!eqvertex) {
                return false;
            }
        }

        //graph must have same edges
        boolean eqedge;
        for (Edge<V, E> e1 : this.edges()) {
            eqedge = false;
            for (Edge<?, ?> e2 : other.edges()) {
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

    //string representation
    @Override
    public String toString() {
        String s = "";
        if (numVert == 0) {
            s = "\nGraph not defined!!";
        } else {
            s = "Graph: " + numVert + " vertices, " + numEdge + " edges\n";
            for (Vertex<V, E> vert : listVert) {
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
