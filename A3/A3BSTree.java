
import java.util.Collection;
import java.util.Iterator;
import java.util.*;

public class A3BSTree<E extends Comparable<E>> implements Tree<E> {

    private static int size;
    BSTNode root;

    public A3BSTree() {
        this.root = new BSTNode();
    }

    @Override
    public void add(E e) {
        root = addHelper(root, e);
    }

    private BSTNode addHelper(BSTNode n, E e) {
        if (n == null || n.content == null) {
            return new BSTNode(e);
        } else if (n.content.compareTo(e) > 0) {
            n.left = addHelper(n.left, e);
        } else if (n.content.compareTo(e) <= 0) {
            n.right = addHelper(n.right, e);
        }

        return n;
    }
    
    private BSTNode findNode(E e){
        BSTNode temp = root;
        while (temp.content != e){
            if (e.compareTo(temp.content) < 0){
                temp = temp.left;
            }
            else
                temp = temp.right;
        }
        return temp;
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        for (E elements : c) {
            add(elements);
        }
    }

    @Override
    public boolean remove(Object o) {
        E element = (E) o;

        return true;
    }

    @Override
    public Iterator<E> iterator() {
        List<E> iterate = new LinkedList<E>();
        return iterate.iterator();
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public int size() {

        return 0;
    }

    public class BSTNode {

        public E content;
        public BSTNode left;
        public BSTNode right;

        public BSTNode() {
            this.content = null;
            this.left = null;
            this.right = null;
        }

        public BSTNode(E e) {
            this.content = e;
            this.left = null;
            this.right = null;
        }

        public BSTNode(E e, BSTNode l, BSTNode r) {
            this.content = e;
            this.left = l;
            this.right = r;
        }

    }
}
