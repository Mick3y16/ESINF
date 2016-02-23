package tree;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents a Binary Search Tree through an initial(root) node.
 *
 * @param <E> Generic type of the BST.
 */
public class BST<E extends Comparable<E>> {

    /**
     * The root of the binary search tree.
     */
    protected Node<E> root = null;

    /**
     * Constructs an empty binary search tree.
     */
    public BST() {
        this.root = null;
    }

    /**
     * Returns the root Node of the tree (or null if tree is empty).
     *
     * @return Root Node of the tree (or null if tree is empty).
     */
    protected Node<E> root() {
        return this.root;
    }

    /**
     * Verifies if the tree is empty.
     *
     * @return True if the tree is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return Number of nodes in the tree.
     */
    public int size() {
        return size(this.root);
    }

    /**
     * Recursively count the number of nodes in the tree.
     *
     * @param node Starting node.
     * @return The number of nodes in the tree.
     */
    private int size(Node<E> node) {
        if (node == null) {
            return 0;
        }

        int size = 1;

        if (node.getRight() != null) {
            size += size(node.getRight());
        }

        if (node.getLeft() != null) {
            size += size(node.getLeft());
        }

        return size;
    }

    /**
     * Inserts an element in the tree.
     *
     * @param element Element to add in the tree.
     */
    public void insert(E element) {
        this.root = insert(element, this.root);
    }

    /**
     * Recursively inserts an element in the tree, finding the correct location
     * to do so.
     *
     * @param element Element to add in the tree.
     * @param node Starting node.
     * @return The node.
     */
    private Node<E> insert(E element, Node<E> node) {
        if (node == null) {
            return new Node(element, null, null);
        }

        if (node.getElement() == element) {
            node.setElement(element);
        } else if (node.getElement().compareTo(element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
        } else {
            node.setRight(insert(element, node.getRight()));
        }

        return node;
    }

    /**
     * Removes an element from the tree maintaining its consistency as a Binary
     * Search Tree.
     *
     * @param element Element in the tree to remove.
     */
    public void remove(E element) {
        root = remove(element, root());
    }

    /**
     * Recursively removes an element in the tree.
     *
     * @param element Element to remove from the tree.
     * @param node Starting node.
     * @return The removed node if found, else null.
     */
    private Node<E> remove(E element, Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.getElement().compareTo(element) < 0) {
            Node<E> rNode = remove(element, node.getRight());
            node.setRight(rNode);
        } else if (node.getElement().compareTo(element) > 0) {
            Node<E> rNode = remove(element, node.getLeft());
            node.setLeft(rNode);
        } else {

            if (node.getLeft() == null) {
                return node.getRight();
            }

            if (node.getRight() == null) {
                return node.getLeft();
            }

            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
        }

        return node;
    }

    /**
     * Returns the smallest element within the tree.
     *
     * @return The smallest element within the tree.
     */
    public E smallestElement() {
        return smallestElement(root);
    }

    /**
     * Recursively searches the smallest node in the tree.
     *
     * @param node Starting node.
     * @return The element of the smallest node in the tree.
     */
    protected E smallestElement(Node<E> node) {
        if (node == null) {
            return null;
        }

        E leftElement = smallestElement(node.getLeft());

        if (leftElement != null) {
            return leftElement;
        }

        return node.getElement();
    }

    /**
     * Returns a map with a list of nodes per tree level.
     *
     * @return Map with a list of nodes per tree level
     */
    public Map<Integer, List<E>> nodesByLevel() {
        Map<Integer, List<E>> nodesPerLevel = new LinkedHashMap<Integer, List<E>>();

        processBstByLevel(this.root, nodesPerLevel, 0);

        return nodesPerLevel;
    }

    /**
     * Fills a map with all the nodes in each level of the tree.
     *
     * @param node Starting node.
     * @param result Map with nodes by level(key).
     * @param level Starting level.
     */
    private void processBstByLevel(Node<E> node, Map<Integer, List<E>> result, int level) {
        LinkedList<Node<E>> currentLevel = new LinkedList<Node<E>>();
        LinkedList<Node<E>> nextLevel = new LinkedList<Node<E>>();
        LinkedList<E> elements = new LinkedList<E>();
        currentLevel.add(node);

        while (!currentLevel.isEmpty()) {
            Node<E> nParent = currentLevel.remove();
            elements.add(nParent.getElement());

            if (nParent.getLeft() != null) {
                nextLevel.add(nParent.getLeft());
            }

            if (nParent.getRight() != null) {
                nextLevel.add(nParent.getRight());
            }

            if (currentLevel.isEmpty()) {
                result.put(level, new LinkedList<E>(elements));
                elements.clear();
                currentLevel.addAll(nextLevel);
                nextLevel.clear();
                level++;
            }
        }
    }

    /**
     * Returns the height of the tree.
     *
     * @return The height of tree.
     */
    public int height() {
        return height(this.root);

    }

    /**
     * Returns the height of the subtree rooted at Node node.
     *
     * @param node A valid Node within the tree.
     * @return The height of the tree.
     */
    protected int height(Node<E> node) {
        if (node == null) {
            return -1;
        }

        int tempLeft = height(node.getLeft());
        int tempRight = height(node.getRight());

        if (tempRight > tempLeft) {
            return tempRight + 1;

        }

        return tempLeft + 1;
    }

    /**
     * Returns an iterable collection of elements of the tree, sorted in
     * in-order.
     *
     * @return Iterable collection of the tree's elements sorted in in-order.
     */
    public Iterable<E> inOrder() {
        List<E> treeInOrder = new LinkedList<E>();

        inOrderSubtree(this.root, treeInOrder);

        return treeInOrder;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot
     * using an in-order traversal.
     *
     * @param node Node serving as the root of a subtree.
     * @param snapshot A list to which results are appended.
     */
    private void inOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node != null) {
            inOrderSubtree(node.getLeft(), snapshot);
            snapshot.add(node.getElement());
            inOrderSubtree(node.getRight(), snapshot);
        }
    }

    /**
     * Returns an iterable collection of elements of the tree, sorted in
     * pre-order.
     *
     * @return Iterable collection of the tree's elements sorted in pre-order.
     */
    public Iterable<E> preOrder() {
        List<E> treePreOrder = new LinkedList<>();

        preOrderSubtree(this.root, treePreOrder);

        return treePreOrder;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot
     * using an pre-order traversal
     *
     * @param node Node serving as the root of a subtree.
     * @param snapshot A list to which results are appended.
     */
    private void preOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node != null) {
            snapshot.add(node.getElement());
            preOrderSubtree(node.getLeft(), snapshot);
            preOrderSubtree(node.getRight(), snapshot);
        }
    }

    /**
     * Returns an iterable collection of elements of the tree, sorted in
     * post-order.
     *
     * @return Iterable collection of the tree's elements sorted in post-order
     */
    public Iterable<E> postOrder() {
        List<E> treePosOrder = new LinkedList<>();

        postOrderSubtree(this.root, treePosOrder);

        return treePosOrder;
    }

    /**
     * Adds positions of the subtree rooted at Node node to the given snapshot
     * using an post-order traversal.
     *
     * @param node Node serving as the root of a subtree.
     * @param snapshot A list to which results are appended.
     */
    private void postOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node != null) {
            postOrderSubtree(node.getLeft(), snapshot);
            postOrderSubtree(node.getRight(), snapshot);
            snapshot.add(node.getElement());
        }
    }

    //---------------- nested Node class ----------------
    /**
     * Nested static class for a binary search tree node.
     */
    protected static class Node<E> {

        private E element;          // an element stored at this node
        private Node<E> left;       // a reference to the left child (if any)
        private Node<E> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e The element to be stored.
         * @param leftChild Reference to a left child node.
         * @param rightChild Reference to a right child node.
         */
        public Node(E e, Node<E> leftChild, Node<E> rightChild) {
            this.element = e;
            this.left = leftChild;
            this.right = rightChild;
        }

        /**
         * Return the element of the node.
         *
         * @return The element of the node.
         */
        public E getElement() {
            return this.element;
        }

        /**
         * Returns the left node of the node.
         *
         * @return The left node of the node.
         */
        public Node<E> getLeft() {
            return this.left;
        }

        /**
         * Returns the right node of the node.
         *
         * @return The right node of node.
         */
        public Node<E> getRight() {
            return this.right;
        }

        /**
         * Sets the element of the node.
         *
         * @param e Element.
         */
        public void setElement(E e) {
            this.element = e;
        }

        /**
         * Sets the left node of the node.
         *
         * @param leftChild Left node of the node.
         */
        public void setLeft(Node<E> leftChild) {
            this.left = leftChild;
        }

        /**
         * Sets the right node of node.
         *
         * @param rightChild Right node of node.
         */
        public void setRight(Node<E> rightChild) {
            this.right = rightChild;
        }
    } //----------- end of nested Node class -----------

    /**
     * Returns a string representation of the tree. Draw the tree horizontally.
     *
     * @return The representation of the tree.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        toStringRec(this.root, 0, sb);

        return sb.toString();
    }

    /**
     * Recursively gets the information of the tree.
     *
     * @param root Starting node.
     * @param level Start level.
     * @param sb The string being constructed.
     */
    private void toStringRec(Node<E> root, int level, StringBuilder sb) {
        if (root == null) {
            return;
        }

        toStringRec(root.getRight(), level + 1, sb);

        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                sb.append("|\t");
            }

            sb.append("|-------").append(root.getElement()).append("\n");
        } else {
            sb.append(root.getElement()).append("\n");
        }

        toStringRec(root.getLeft(), level + 1, sb);
    }

} //----------- end of BST class -----------
