/**
 * This class defines a node with a generic comparable value. Nodes have left, right and parent Nodes associated with them as well
 * as a depth, height to show where they are on a tree. A count defines the amount of duplicate values (1 being no duplicates).
 * @param <E> Takes any type which implements the comparable class
 */
public class Node<E extends Comparable<E>>{

    private E value;
    private int depth;
    private int height=0;
    private int count;
    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;

    /**
     * Basic constructor. Everything is set to null
     */
    public Node(){
        left = null;
        right = null;
    }

    /**
     * Value based constructor sets count to 1 (no duplicates)
     * @param value Sets the value of the node, must implement comparable.
     */
    public Node(E value){
        this.value = value;
        this.left = null;
        this.right = null;
        count = 1;
    }

    /**
     *
     * @return A string: The right and left node values are returned as well as the value of the calling object.
     */
    public String toString(){
        if(left !=null && right!=null)
            return "This is node " + value + " with a height of " + height + ". Its left node is " + left.value +
                    " and its right node is " + right.value;
        else if(left == null && right != null)
            return "This is node " + value + " with a height of " + height + " . Its left node is null and its right node is " +
                    right.value;
        else if(left !=null && right == null)
            return "This is node " + value + " with a height of " + height +". Its left node is " + left.value +
                    " and its right node is null";
        else
            return "This is node " + value + " with a height of " + height +". Its left and right nodes are both null";

    }


    /**
     *
     * @param height Sets the height of the node
     */
    public void setHeight(int height){  this.height = height; }

    /**
     *
     * @return Returns the height of the node
     */
    public int getHeight(){return height;}

    /**
     *
     * @return Returns the depth of the node
     */
    public int getDepth() {
        return depth;
    }

    /**
     *
     * @return Returns the number of duplicate node values, 1 means no duplicates 2+ means duplicates
     */
    public int getCount(){return count;}

    /**
     *
     * @param count Returns the number of duplicate node values, 1 means no duplicates 2+ means duplicates
     */
    public void setCount(int count){this.count = count;}

    /**
     *
     * @param depth Returns the depth from the root of a tree
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     *
     * @return Returns a generic type which implements comparable
     */
    public E getValue() {
        return value;
    }

    /**
     *
     * @param value Sets the value of a node to a generic type which implements comparable
     */
    public void setValue(E value) {
        this.value = value;
    }

    /**
     *
     * @return Gets the parent of a node
     */
    public Node<E> getParent() {
        return parent;
    }

    /**
     *
     * @param parent Sets the parent of a node
     */
    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    /**
     *
     * @return Gets the left node
     */
    public Node<E> getLeft() {
        return left;
    }

    /**
     *
     * @param left Sets the left node
     */
    public void setLeft(Node<E> left) {
        this.left = left;
    }

    /**
     *
     * @return Gets the right node
     */
    public Node<E> getRight() {
        return right;
    }

    /**
     *
     * @param right Sets the right node
     */
    public void setRight(Node<E> right) {
        this.right = right;
    }

    /**
     *
     * @return True if this node has a right node, otherwise false.
     */
    public boolean hasRight() {
        return this.getRight() != null;
    }

    /**
     *
     * @return True if this node has a left node, false otherwise.
     */
    public boolean hasLeft() {
        return this.getLeft() != null;
    }

    /**
     *
     * @param other Another node whose value implements comparable
     * @return -1 if this object is less than, 0 if it is equal or 1 if it is greater than the other object.
     */
    public int compareTo(Object other) {
        Node<E> o = (Node<E>)other;
        int k = this.getValue().compareTo(o.getValue());
        return k;
    }


}
