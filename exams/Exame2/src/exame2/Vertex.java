package exame2;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the Vertex through a key, an generic element which refers to what 
 * is contained by the Vertex and a map of outgoing vertices.
 * 
 * @param <V> Generic element which refers to what is contained by the Vertex.
 * @param <E> Generic element which refers to what is considered as weight.
 */
public class Vertex<V, E> {

    /**
     * Vertex ID.
     */
    private int key;

    /**
     * Vertex generic element.
     */
    private V element;

    /**
     * Map to the outgoing vertices.
     */
    private Map<Vertex<V, E>, Edge<V, E>> outVerts;

    /**
     * Creates an instance of vertex.
     */
    public Vertex() {
    }

    /**
     * Creates an instance of vertex, receiving a key and a generic 
     * element which refers to what is contained by the Vertex. 
     * 
     * @param k Key of the vertex.
     * @param vInf Generic element which refers to what is contained by the Vertex.
     */
    public Vertex(int k, V vInf) {
        this.key = k;
        this.element = vInf;
        this.outVerts = new LinkedHashMap<>();
    }

    /**
     * Returns the key of the Vertex.
     * 
     * @return Key of the Vertex.
     */
    public int getKey() {
        return  this.key;
    }

    /**
     * Returns the generic element which refers to what is contained by the Vertex.
     * 
     * @return Generic element which refers to what is contained by the Vertex.
     */
    public V getElement() {
        return  this.element;
    }
    
    /**
     * Sets the key of the Vertex.
     * 
     * @param k Key of the Vertex.
     */
    public void setKey(int k) {
         this.key = k;
    }

    /**
     * Sets the generic element which refers to what is contained by the Vertex.
     * 
     * @param vInf Generic element which refers to what is contained by the Vertex.
     */
    public void setElement(V vInf) {
         this.element = vInf;
    }

    /**
     * Returns the outgoing vertices.
     * @return
     */
    public Map<Vertex<V, E>, Edge<V, E>> getOutgoing() {
        return  this.outVerts;
    }

    /**
     * Compares two vertices, first it looks at their location in the memory,
     * then if different it ensures that both the classes are the same and if
     * one of them is not null and finally compares all their attributes.
     *
     * @param obj Activity that will be compared.
     * @return True if both activities are equals or false if not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Vertex<V, E> otherVertex = (Vertex<V, E>) obj;

        return (this.key == otherVertex.key
                && this.element != null && otherVertex.element != null
                && this.element.equals(otherVertex.element));
    }

    /**
     * Clones a vertex and its respective edges.
     * 
     * @return The cloned Vertex.
     */
    @Override
    public Vertex<V, E> clone() {

        Vertex<V, E> newVertex = new Vertex<>();

        newVertex.key = key;
        newVertex.element = element;

        Map<Vertex<V, E>, Edge<V, E>> newMap = new LinkedHashMap<>();

        Iterator<Map.Entry<Vertex<V, E>, Edge<V, E>>> itmap;
        itmap = this.outVerts.entrySet().iterator();
        while (itmap.hasNext()) {
            Map.Entry<Vertex<V, E>, Edge<V, E>> entry = itmap.next();
            newMap.put(entry.getKey(), entry.getValue());
        }
        newVertex.outVerts = newMap;

        return newVertex;
    }

    /**
     * Returns the information of the Vertex.
     * 
     * @return the information of the vertex.
     */
    @Override
    public String toString() {
        return  this.element + " (" +  this.key + "): ";
    }

}
