public class Node<E extends Comparable<E>> implements Comparable<Node<E>> {

    private E content;
    private Node<E> left;
    private Node<E> right;
    public int level;
    private int depth;

    public Node(E data) {
        this(data, null, null);
    }
    
    public Node(E content, Node<E> left, Node<E> right) {
        super();
        this.content = content;
        this.left = left;
        this.right = right;
        if (left == null && right == null) {
            setDepth(1);
        } else if (left == null) {
            setDepth(right.getDepth() + 1);
        } else if (right == null) {
            setDepth(left.getDepth() + 1);
        } else {
            setDepth(Math.max(left.getDepth(), right.getDepth()) + 1);
        }
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int compareTo(Node<E> o) {
        return this.content.compareTo(o.content);
    }
}