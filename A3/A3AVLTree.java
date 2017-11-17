import java.util.Collection;
import java.util.Iterator;

public class A3AVLTree <E extends Comparable<E>> implements Tree<E> {
    
    Node<E> root;

    public A3AVLTree() {
        root = null;
    }

    @Override
    public void add(E e) {
        root = add(root, e);
    }
    
    @Override
    public void addAll(Collection<? extends E> c) {
    }
    
    private Node<E> add(Node<E> node, E e) {
        if (node == null) {
            return new Node<E>(e);
        }
        if (node.getContent().compareTo(e) > 0) {
            node = new Node<E>(node.getContent(), add(node.getLeft(), e), node.getRight());
        } else if (node.getContent().compareTo(e) < 0) {
            node = new Node<E>(node.getContent(), node.getLeft(), add(node.getRight(), e));
        }
        switch (getBalanceNumber(node)) {
            case 1:
                break;
            case -1:
                break;
            default:
                return node;
        }
        return node;
    }
    
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
    
    //Balances node by checking left and right in relation with the parent node.
    private void balance(Node n) {
    }

    @Override
    public boolean remove(Object o) {
    }

    @Override
    public Iterator<E> iterator() {
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }
    
    private int depth(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return node.getDepth();
    }
}
