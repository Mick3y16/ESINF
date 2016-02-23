package PL;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author DEI-ESINF
 * @param <E>
 */
/*
public interface:

public BST()
public boolean isEmpty()
public int size()
public void insert(E element)
public void remove(E element)
public String toString()

public int height()
public E smallestElement()
public Iterable<E> inOrder()
public Iterable<E> preOrder()
public Iterable<E> postOrder()
public Map<Integer,List<E>> nodesByLevel()
++++++++++++++++++++++++++++++
public Iterator<E> iterator()

public List<E> ascdes()


 */
public class TREE<E extends Comparable<E>> extends BST<E> {
    //---------------------------------------------------------------   

    /**
     * Returns the number of levels separating E element from the root.
     *
     * @param element A valid element within the tree
     * @return
     * @throws IllegalArgumentException if element is not a valid E for this
     * tree.
     */
    public int depth(E element) {
        return (depth(element, root));
    }

    private int depth(E element, Node<E> node) {
        if (node != null) {
            if (node.getElement().compareTo(element) == 0) {
                return 0;
            } else if (node.getElement().compareTo(element) < 0) {
                return depth(element, node.getRight()) + 1;
            } else if (node.getElement().compareTo(element) > 0) {
                return depth(element, node.getLeft()) + 1;
            }
        }

        throw new IllegalArgumentException("Element does not exist in the tree.");
    }
    //---------------------------------------------------------------   

    public boolean contains(E element) {
        return find(element, root) != null;
    }

    //---------------------------------------------------------------   
    public boolean isLeaf(E element) {
        Node<E> node = find(element, root);

        return node != null && node.getLeft() == null && node.getRight() == null;
    }

//---------------------------------------------------------------   
    /**
     * Returns the parent's Element of an Element (or null if Element is the
     * root or not belongs to the tree).
     *
     * @param element A valid element within the tree
     * @return Element of element's parent (or null if element is root)
     */
    public E parent(E element) {
        if (root.getElement().compareTo(element) == 0) {
            return null;
        }

        return parent(element, root);
    }

    private E parent(E element, Node<E> parentNode) {
        if (parentNode != null) {
            if (    parentNode.getLeft().getElement().compareTo(element) == 0 ||
                    parentNode.getRight().getElement().compareTo(element) == 0) {
                return parentNode.getElement();
            } else {
                if (parentNode.getElement().compareTo(element) > 0) {
                    return parent(element, parentNode.getLeft());
                } else {
                    return parent(element, parentNode.getRight());
                }
            }
        }

        return null;
    }

//****************************************************************
    public Iterator<E> iterator() {
        return new BSTIterator();
    }
//#########################################################################
//#########################################################################
//---------------------------------------------------------------   

    /**
     * Returns the tree without leaves.
     *
     * @return tree without leaves
     */
    public BST<E> autumnTree() {
        BST<E> tree = new TREE();

        tree.root = copyRec(root);

        return tree;
    }

    private Node<E> copyRec(Node<E> node) {
        Node<E> newNode = null;

        if (node != null && (node.getLeft() != null || node.getRight() != null)) {
            newNode = new Node(node.getElement(), copyRec(node.getLeft()), copyRec(node.getRight()));
        }

        return newNode;
    }

//#########################################################################
//#########################################################################  
    private class BSTIterator implements Iterator<E> {

        private final Stack<Node<E>> stack;
        E curElement;       //current element
        boolean canRemove;  //to enable remove()

        public BSTIterator() {
            stack = new Stack<>();
            Node<E> cur = (Node<E>) root();
            while (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();
            }
            curElement = null;
            canRemove = false;
        }

        /**
         * @return whether we have a next smallest element
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * @return the next smallest element
         */
        @Override
        public E next() {
            Node<E> curr = stack.pop();
            curElement = curr.getElement();

            curr = curr.getRight();

            while (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }

            canRemove = true;
            return curElement;
        }

        /**
         * remove the current element
         */
        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("You can't remove the element.");
            }

            TREE.super.remove(curElement);

            curElement = null;
            canRemove = false;
            stack.clear();
        }
    }
//#########################################################################
//######################################################################### 

    /**
     * build a list with all elements of the tree. The elements in the left
     * subtree in ascending order and the elements in the right subtree in
     * descending order.
     *
     * @return returns a list with the elements of the left subtree in ascending
     * order and the elements in the right subtree is descending order.
     */
    public Iterable<E> ascdes() {
        List<E> list = new LinkedList<>();

        ascSubtree(root.getLeft(), list);
        list.add(root.getElement());
        desSubtree(root.getRight(), list);

        return list;
    }

    private void ascSubtree(Node<E> node, List<E> snapshot) {
        if (node != null) {
            ascSubtree(node.getLeft(), snapshot);
            snapshot.add(node.getElement());
            ascSubtree(node.getRight(), snapshot);
        }
    }

    private void desSubtree(Node<E> node, List<E> snapshot) {
        if (node != null) {
            desSubtree(node.getRight(), snapshot);
            snapshot.add(node.getElement());
            desSubtree(node.getLeft(), snapshot);
        }
    }

}
