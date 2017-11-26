import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class A3AVLTree <E extends Comparable<E>> implements Tree<E> {
    
    Node<E> root;
    private int size;

    public A3AVLTree() {
        root = null;
        size = 0;
    }

    @Override
    public void add(E e) {
        root = add(root, e);
    }
    
    @Override
    public void addAll(Collection<? extends E> c) {
        while(c.iterator().hasNext()) {
            E el = c.iterator().next();
            add(el);
        }
    }
    
    private Node<E> add(Node<E> node, E e) {
        if (node == null) {
            size++;
            return new Node<E>(e);
        }
        if (node.getContent().compareTo(e) > 0) {
            size++;
            node = new Node<E>(node.getContent(), add(node.getLeft(), e), node.getRight());
        } else if (node.getContent().compareTo(e) < 0) {
            size++;
            node = new Node<E>(node.getContent(), node.getLeft(), add(node.getRight(), e));
        }
        switch (getBalanceNumber(node)) {
            case 1:
                node = rotateLeft(node);
                break;
            case -1:
                node = rotateRight(node);
                break;
            default:
                return node;
        }
        return node;
    }
    
    /**
     * Getter for balanceNumber
     * @param node
     * @return balanceNumber
     */
    private int getBalanceNumber(Node<E> node) {
        int left = depth(node.getLeft());
        int right = depth(node.getRight());
        if (left - right >= 2) {
            return -1;
        } else if (left - right <= -2) {
            return 1;
        }
        return 0;
    }
    
    //Rotation process
    
    /**
     * Rotate left for AVL Tree
     * @param node
     * @return the rotated node
     */
    private Node<E> rotateLeft(Node<E> node) {
        Node<E> x = node;
        Node<E> xRight = x.getRight();
        Node<E> xLeft = x.getLeft();
        Node<E> xRLeft = xRight.getLeft();
        Node<E> xRRight = xRight.getRight();
        x = new Node<E>(x.getContent(), xLeft, xRLeft);
        xRight = new Node<E>(xRight.getContent(), x, xRRight);
        return xRight;
    }
    
    /**
     * Rotate right for AVL Tree
     * @param node
     * @return the rotated node
     */
    private Node<E> rotateRight(Node<E> node) {
        Node<E> x = node;
        Node<E> xLeft = x.getLeft();
        Node<E> xRight = x.getRight();
        Node<E> xLLeft = xLeft.getLeft();
        Node<E> xLRight = xLeft.getRight();
        x = new Node<E>(x.getContent(), xLRight, xRight);
        xLeft = new Node<E>(xLeft.getContent(), xLLeft, x);
        return xLeft;
    }

    @Override
    public boolean remove(Object o) {
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        List<E> iterate = new LinkedList<E>();
        return iterate.iterator();
    }

    /**
     * Get height of the whole tree.
     * @return height of root
     */
    @Override
    public int height() {
        return height(root);
    }

    /**
     * Get size of the whole tree.
     * @return size of root
     */
    @Override
    public int size() {
        return getSize(root);
    }
    
    /**
     * Get size of a node.
     * @param node
     * @return size of node.
     */
    private int depth(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return node.getDepth();
    }
    
    private int getSize(Node<E> node) {
        return size;
    }
    
    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight(node);
    }
    
    public static void main(String[] args) {
        A3AVLTree t1 = new A3AVLTree();
        t1.add(3);
        t1.add(2);
        t1.add(1);
        t1.add(4);
        t1.add(5);
        t1.add(5);
        t1.add(6);
        t1.add(7);
        t1.add(16);
        t1.add(15);
    }
}
