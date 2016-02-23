package PL;

/**
 *
 * @author DEI-ESINF
 * @param <E>
 */
public class AVL<E extends Comparable<E>> extends BST<E> {

    public AVL() {
        super();
    }

    public boolean perfectBalanced() {
        return perfectBalance(root);
    }

    private boolean perfectBalance(Node<E> node) {
        if (node == null) {
            return true;
        }

        if (balanceFactor(node) != 0) {
            return false;
        }

        if (perfectBalance(node.getLeft())) {
            return perfectBalance(node.getRight());
        }

        return false;
    }

    private int balanceFactor(Node<E> node) {
        return height(node.getRight()) - height(node.getLeft());
    }

    private Node<E> rightRotation(Node<E> node) {
        Node<E> leftSon = node.getLeft();
        node.setLeft(leftSon.getRight());
        leftSon.setRight(node);
        node = leftSon;

        return node;
    }

    private Node<E> leftRotation(Node<E> node) {
        Node<E> rightSon = node.getRight();
        node.setRight(rightSon.getLeft());
        rightSon.setLeft(node);
        node = rightSon;

        return node;
    }

    private Node<E> twoRotations(Node<E> node) {
        if (balanceFactor(node) < 0) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        } else {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }

        return node;
    }

    private Node<E> balanceNode(Node<E> node) {
        if (balanceFactor(node) < 0) {
            if (balanceFactor(node.getLeft()) < 0) {
                node = rightRotation(node);
            } else if (balanceFactor(node.getLeft()) > 0) {
                node = twoRotations(node);
            }
        } else if (balanceFactor(node) > 0) {
            if (balanceFactor(node.getRight()) > 0) {
                node = leftRotation(node);
            } else if (balanceFactor(node.getRight()) < 0) {
                node = twoRotations(node);
            }
        }

        return node;
    }

    @Override
    public void insert(E element) {
        root = insert(element, root);
    }

    private Node<E> insert(E element, Node<E> node) {
        if (node == null) {
            return new Node(element, null, null);
        }

        if (node.getElement().compareTo(element) == 0) {
            node.setElement(element);
        } else if (node.getElement().compareTo(element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
            node = balanceNode(node);
        } else {
            node.setRight(insert(element, node.getRight()));
            node = balanceNode(node);
        }

        return node;
    }

    @Override
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, BST.Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.getElement().compareTo(element) > 0) {
            node.setLeft(remove(element, node.getLeft()));
            node = balanceNode(node);
        } else if (node.getElement().compareTo(element) < 0) {
            node.setRight(remove(element, node.getRight()));
            node = balanceNode(node);
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }

            if (node.getLeft() == null) {
                return node.getRight();
            }

            if (node.getRight() == null) {
                return node.getLeft();
            }

            E smallElem = smallestElement(node.getRight());
            node.setElement(smallElem);
            node.setRight(remove(smallElem, node.getRight()));
            node = balanceNode(node);
        }

        return node;
    }

    public boolean equals(AVL<E> second) {
        return equals(root, second.root);
    }

    public boolean equals(Node<E> root1, Node<E> root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

}
