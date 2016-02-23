package tree;

/**
 * Represents a tree of activities.
 *
 * @param <E> Generic type of the activities tree.
 */
public class ActivityTree<E extends Comparable<E>> extends BST<E> {

    /**
     * Creates an instance of a tree of activities.
     * 
     * @param <E> Generic type of the activities tree.
     */
    public <E> ActivityTree() {
        super();
    }

}
