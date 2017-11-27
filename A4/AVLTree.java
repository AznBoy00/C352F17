
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class AVLTree<E extends Comparable<E>> implements Registry, Iterable {

    private int treeHeight;
    private int size;
    private Node<E> root;
    private Car c;

    public Car getC() {
        return c;
    }

    public void setC(Car c) {
        this.c = c;
    }

    /**
     * Default (only) constructor, sets all values to null or zero to be
     * overwritten when elements are added
     */
    public AVLTree() {
        root = null;
        treeHeight = 0;
        size = 0;
    }

    /**
     * Adds the specified element to this tree (duplicates are allowed, and are
     * stored in a counter for that Node's value)
     *
     * @param key element to add
     */
    @Override
    public void add(String key, Car c) {
        Node<E> nToAdd = new Node<E>(key);
        //If this is the first addition, replace the null root
        if (root == null) {
            this.setRoot(nToAdd);
        } else {
            //If the Node<E> already exists, increment existing Node<E> count
            Node<E> toFind = find(nToAdd, root);
            if (toFind != null) {
                toFind.setCount(toFind.getCount() + 1);
            } else {	//otherwise add a new node
                Node<E> newNode = new Node<E>(key);
                Node<E> correctParent = findCorrectParent(newNode, root);

                newNode.setParent(correctParent);
                if (correctParent.compareTo(newNode) < 0) //if (correctParent.getValue() < newNode.getValue());
                {
                    correctParent.setRight(newNode);
                } else {
                    correctParent.setLeft(newNode);
                }

                newNode.setDepth(correctParent.getDepth() + 1);
                if (treeHeight < newNode.getDepth()) {
                    treeHeight = newNode.getDepth();
                }
                Node<E> toCheck = checkBad(newNode);
                if (toCheck != null) {
                    Restructure.restructure(toCheck, this);
                }
            }
        }
        c = new Car(key);
        size++;
    }

    /**
     * Finds the correct parent for the node being added to the tree
     *
     * @param toAdd the node being added whose parent is to be found
     * @param subRoot Node<E> is initially the root, changed over recursive
     * calls
     * @return the correct parent for the node being added
     */
    private Node<E> findCorrectParent(Node<E> toAdd, Node<E> subRoot) {
        if (subRoot.compareTo(toAdd) < 0 && !subRoot.hasRight()) //if(subRoot < toAdd)
        {
            return subRoot;
        } else if (subRoot.compareTo(toAdd) >= 0 && !subRoot.hasLeft()) //if(subRoot >= toAdd)
        {
            return subRoot;
        } else if (subRoot.compareTo(toAdd) < 0 && subRoot.hasRight()) //if(subRoot < toAdd)
        {
            subRoot = subRoot.getRight();
        } else if (subRoot.compareTo(toAdd) >= 0 && subRoot.hasLeft()) //if(subRoot >= toAdd)
        {
            subRoot = subRoot.getLeft();
        }
        return findCorrectParent(toAdd, subRoot);
    }

    /**
     * Removes the specified element from this set if it is present. (if more
     * than one is present, decrements the object's counter by one)
     *
     * @param key object to remove
     * @return the object removed, null if it was not founeW in the tree
     */
    @Override
    public void remove(String key) {
        Node<E> toDel = new Node<E>((String) key);
        Node<E> result = find(toDel, root);
        if (result.getCount() > 1) {
            result.setCount(result.getCount() - 1);
        } else {
            boolean root = result.equals(getRoot());
            Node<E> parent = result.getParent();
            if (result.getLeft() == null && result.getRight() == null) {	//the node to delete is a leaf
                if (result == parent.getLeft()) //result is a left child
                {
                    parent.setLeft(null);
                } else //otherwise result is a right child
                {
                    parent.setRight(null);
                }
            } else if (result.getLeft() != null && result.getRight() != null) //It is an internal node with both children
            {
                Node<E> toReplace = nextKey(result);	//The node to replace result
                Node<E> tempParent = toReplace.getParent();
                remove(toReplace.getValue());
                if (!(toReplace.getCount() > 1)) {	//only restructure the tree if the toReplace deleted was the final count of that value
                    if (root) {
                        setRoot(toReplace);
                        this.root.setHeight(result.getHeight());
                        this.root.setLeft(result.getLeft());
                        this.root.setRight(result.getRight());
                        tempParent = this.root;
                    } else if (result == parent.getLeft()) //result is a left child
                    {
                        parent.setLeft(toReplace);
                        parent.getLeft().setHeight(result.getHeight());
                        parent.getLeft().setLeft(result.getLeft());
                        parent.getLeft().setRight(result.getRight());
                    } else //otherwise result is a right child
                    {
                        parent.setRight(toReplace);
                        parent.getRight().setHeight(result.getHeight());
                        parent.getRight().setLeft(result.getLeft());
                        parent.getRight().setRight(result.getRight());
                    }
                    parent = tempParent;
                }
            } else //It has only one child
            {
                if (root) {
                    if (this.root.hasLeft()) {
                        setRoot(this.root.getLeft());
                    } else {
                        setRoot(this.root.getRight());
                    }
                    parent = this.root;
                } else if (result == parent.getLeft()) //result is a left child
                {
                    if (result.getLeft() != null) {
                        parent.setLeft(result.getLeft());
                    } else {
                        parent.setLeft(result.getRight());
                    }
                } else //otherwise result is a right child
                if (result.getLeft() != null) {
                    parent.setRight(result.getLeft());
                } else {
                    parent.setRight(result.getRight());
                }
            }

            //Must adjust the parent's height it if has no children remaining
            if (parent.getLeft() == null && parent.getRight() == null) {
                parent.setHeight(0);
            }

            //Correct heights up the tree from parent
            Node<E> temp = parent;
            while (temp.getParent() != null) {
                temp = temp.getParent();
                Restructure.adjustHeight(temp);
            }

            Node<E> toCheck = checkBad(parent);
            if (toCheck != null) {
                Restructure.restructure(toCheck, this);
            }
        }
    }

    /**
     * Returns an iterator over the elements in this tree in ascending order
     *
     * @return the iterator described above
     */
    @Override
    public Iterator<E> iterator() {
        return (new InorderIterator());
    }

    /**
     * Returns the treeHeight of the tree. For a tree of one Node<E> returns 0
     *
     * @return treeHeight of the tree
     */
    public int height() {
        return treeHeight;
    }

    /**
     * Returns the number of elements (Nodes and duplicates) in the tree.
     *
     * @return number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Gets the tree's root node
     *
     * @return the tree's root node
     */
    public Node<E> getRoot() {
        return root;
    }

    /**
     * Finds and returns the first (lowest value) node in the tree
     *
     * @return the node with the value described above
     */
    private Node<E> getFirst() {
        Node<E> temp = this.root;
        while (temp.hasLeft()) {
            temp = temp.getLeft();
        }
        return temp;
    }

    /**
     * Finds and returns the last (highest value) node in the tree
     *
     * @return the node with the value described above
     */
    private Node<E> getLast() {
        Node<E> temp = this.root;
        while (temp.hasRight()) {
            temp = temp.getRight();
        }
        return temp;
    }

    /**
     * Sets the tree's root to a new value
     *
     * @param node the Node<E> to replace the root
     */
    void setRoot(Node<E> node) {
        this.root = node;
    }

    /**
     * Traverses up the tree from an added node (or the parent of a removed
     * node) to check for an unbalanced node
     *
     * @param added the node just added, or the parent of the node just removed
     * @return the unbalanced node, or null if the tree is balanced
     */
    private Node<E> checkBad(Node<E> added) {
        Node<E> bad = new Node<E>();
        while (added != null && added != bad) {

            int height = added.getHeight();
            //keep moving up parents
            added = added.getParent();
            //If you've reached the root node
            if (added == null) {
                return added;
            }
            //If the parent has the same height as the child, increment the parents height by 1
            if (added.getHeight() == height) {
                added.setHeight(added.getHeight() + 1);
            }

            //If left is null and right is not, it is a bad node if the right node has height 1 or more.
            if (!added.hasLeft() && added.hasRight()) {
                if (Math.abs(0 - added.getRight().getHeight()) >= 1) {
                    return added;
                }
            }

            //If right is null and left is not, it is a  bad node if the left node has height 1 or more.
            if (added.hasLeft() && !added.hasRight()) {
                if (Math.abs(0 - added.getLeft().getHeight()) >= 1) {
                    return added;
                }
            }

            //If right and left nodes are not null, it is a bad node if the difference between their heights is greater than 1
            if (added.hasLeft() && added.hasRight()) {
                if (Math.abs(added.getLeft().getHeight() - added.getRight().getHeight()) > 1) {
                    return added;
                }
            }
        }
        System.out.println("Returning a dummy node");
        return bad;
    }

    //Not used in the program, but useful if you only want to display values, as opposed to iterating over them
    /**
     * Displays the inOrder traversal of the tree
     *
     * @param root initially the root of the tree, is changed over recursive
     * calls
     */
    private void inOrder(Node<E> root) {
        if (root != null) {
            this.inOrder(root.getLeft());
            System.out.print(root.getValue() + ", ");
            this.inOrder(root.getRight());
        }
    }

    /**
     * Finds and returns the calling node's inOrder successor
     *
     * @param root the node whose next node is to be found
     * @return the calling node's inOrder successor, or null if the calling node
     * is the final node
     */
    public Node nextKey(Node root) {

        Node<E> temp = root.getRight(), next = new Node<E>();

        if (temp != null) {	//If your root has a right node, the next node will be in that subtree
            while (temp != null) {
                next = temp;
                temp = temp.getLeft();
            }
            return next;
        } else {		//Otherwise, the next node will be above the node in the tree: travel up until the next node is a left child: it's parent is next. If you reach the root of the tree, your node was the final node
            next = root;
            temp = root.getParent();
            while (temp != null) {
                if (temp.getLeft() == next) {
                    return temp;
                } else {
                    next = temp;
                    temp = temp.getParent();
                }
            }
            return null; //Indicates the calling node was the final entry
        }
    }

    /**
     * Finds and returns the calling value's node's inOrder successor
     *
     * @param key the key to the node whose next node is to be found
     * @return the calling node's inOrder successor, or null if the calling node
     * is the final node
     */
    @Override
    public String nextKey(String key) {
        Node<E> toFind = find(key);
        Node<E> temp = root.getRight(), next = new Node<E>();

        if (temp != null) {	//If your root has a right node, the next node will be in that subtree
            while (temp != null) {
                next = temp;
                temp = temp.getLeft();
            }
            return next.getValue();
        } else {		//Otherwise, the next node will be above the node in the tree: travel up until the next node is a left child: it's parent is next. If you reach the root of the tree, your node was the final node
            next = root;
            temp = root.getParent();
            while (temp != null) {
                if (temp.getLeft() == next) {
                    return temp.getValue();
                } else {
                    next = temp;
                    temp = temp.getParent();
                }
            }
            return null; //Indicates the calling node was the final entry
        }
    }

    /**
     * Finds and returns the calling node's inOrder predecessor
     *
     * @param root the node whose previous node is to be found
     * @return the calling node's inOrder predecessor, or null if the calling
     * node is the first node
     */
    public Node prevNode(Node root) {

        Node<E> temp = root.getLeft(), prev = new Node<E>();

        if (temp != null) {	//If your root has a left node, the previous node will be in that subtree
            while (temp != null) {
                prev = temp;
                temp = temp.getRight();
            }
            return prev;
        } else {		//Otherwise, the previous node will be above the node in the tree: travel up until the next node is a right child: it's parent is the previous node. If you reach the root of the tree, your node was the first node
            prev = root;
            temp = root.getParent();
            while (temp != null) {
                if (temp.getRight() == prev) {
                    return temp;
                } else {
                    prev = temp;
                    temp = temp.getParent();
                }
            }
            return null; //Indicates the calling node was the first entry
        }
    }

    /**
     * Finds and returns the calling value's node's inOrder predecessor
     *
     * @param root the node whose previous node is to be found
     * @return the calling node's inOrder predecessor, or null if the calling
     * node is the first node
     */
    @Override
    public String prevKey(String key) {
        Node<E> root = find(key);
        Node<E> temp = root.getLeft(), prev = new Node<E>();

        if (temp != null) {	//If your root has a left node, the previous node will be in that subtree
            while (temp != null) {
                prev = temp;
                temp = temp.getRight();
            }
            return prev.getValue();
        } else {		//Otherwise, the previous node will be above the node in the tree: travel up until the next node is a right child: it's parent is the previous node. If you reach the root of the tree, your node was the first node
            prev = root;
            temp = root.getParent();
            while (temp != null) {
                if (temp.getRight() == prev) {
                    return temp.getValue();
                } else {
                    prev = temp;
                    temp = temp.getParent();
                }
            }
            return null; //Indicates the calling node was the first entry
        }
    }

    /**
     * Finds and returns the node in the tree with the same value as the node
     * toAdd
     *
     * @param toFind the node to be added
     * @param initial Node<E> is initially the root, changed over recursive
     * calls
     * @return the node that matches the above description, or null if there is
     * no such node
     */
    public Node<E> find(Node<E> toFind, Node<E> initial) {
        if (initial.getValue() == toFind.getValue()) {
            return initial;
        } else if (initial.compareTo(toFind) < 0 && initial.hasRight()) {
            return find(toFind, initial.getRight());
        } else if (initial.compareTo(toFind) > 0 && initial.hasLeft()) {
            return find(toFind, initial.getLeft());
        } else {
            return null;
        }

    }

    /**
     * Finds and returns the node in the tree with the provided value
     *
     * @param valueToAdd the value to be added
     * @param initial Node<E> is initially the root, changed over recursive
     * calls
     * @return the node that matches the above description, or null if there is
     * no such node
     */
    public Node find(String valueToFind) {
        Node<E> initial = this.root;
        Node<E> toAdd = new Node<E>(valueToFind);
        if (initial.getValue() == toAdd.getValue()) {
            return initial;
        } else if (initial.compareTo(toAdd) < 0 && initial.hasRight()) {
            return find(toAdd, initial.getRight());
        } else if (initial.compareTo(toAdd) > 0 && initial.hasLeft()) {
            return find(toAdd, initial.getLeft());
        } else {
            return null;
        }

    }

    /**
     * return the values of the given key
     *
     * @param valueToFind
     * @return return the values of the given key
     */
    @Override
    public Car getValues(String valueToFind) {
        Node<E> initial = this.root;
        Node<E> toAdd = new Node<E>(valueToFind);
        if (initial.getValue() == toAdd.getValue()) {
            return initial.getCar();
        } else if (initial.compareTo(toAdd) < 0 && initial.hasRight()) {
            return find(toAdd, initial.getRight()).getCar();
        } else if (initial.compareTo(toAdd) > 0 && initial.hasLeft()) {
            return find(toAdd, initial.getLeft()).getCar();
        } else {
            return null;
        }

    }

    /**
     * Standard toString overriding method return string description of the
     * calling tree, gives details on its size and height
     */
    public String toString() {
        return "This tree has " + size + " elements and has a tree height of " + treeHeight;
    }

    /**
     * Inner class for the A3BSTree iterator
     */
    abstract class AVLIterator implements Iterator<E> {

        Node<E> currentValue;
        AVLTree<E> t;
        int duplicateCounter = 0;

        private AVLIterator(AVLTree<E> t) {
            currentValue = null;
            this.t = t;
        }

        /**
         * Checks if there are values remaining to iterate over
         *
         * @return true if the iterator has a next value
         */
        @Override
        public boolean hasNext() {
            if (currentValue == null && t.size() != 0) {
                return true;
            }
            Node<E> nextValue = t.nextKey(currentValue);
            if (nextValue != null) {
                return true;
            }
            return false;
        }

        /**
         * Gets the next value to iterate over, taking duplicates into
         * consideration
         *
         * @return the next value from the iterator
         */
        public String nextValue() {

            //if you're at the very beginning of the iterator's sequence
            if (currentValue == null) {
                currentValue = t.getFirst();
                duplicateCounter = currentValue.getCount();
                return currentValue.getValue();
            }

            //If there are still duplicates to be read out
            if (duplicateCounter > 1) {
                --duplicateCounter;
                return currentValue.getValue();
            }

            //If there are no more duplicates to be read out, go to the actual next node
            Node<E> nextValue = t.nextKey(currentValue);
            if (nextValue != null) {
                currentValue = nextValue;
                duplicateCounter = currentValue.getCount();
                return nextValue.getValue();
            }

            //if you're trying to go past the end of the iterator's sequence
            throw new NoSuchElementException("Reached end of structure");
        }

    }

    // an iterator, doesn't implement remove().
    private class InorderIterator implements Iterator<E> {

        private Stack<Node<E>> st = new Stack<>();

        public InorderIterator() {
            if (root != null) {
                Node<E> current = root;
                while (current != null) {
                    st.push(current);
                    current = current.getLeft();
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !st.isEmpty();
        }

        @Override
        public E next() {
            E answer = null;
            if (!st.isEmpty()) {
                Node<E> current = st.pop();
                answer = (E) current.getValue();
                if (current != null && current.getRight() != null) {
                    current = current.getRight();
                }
                if (current != null && current.getValue() != answer) {
                    st.push(current);
                    current = st.pop();
                    while (current != null) {
                        st.push(current);
                        current = current.getLeft();
                    }
                }
            }

            return answer;
        }

    }
    
    /**
     * return all keys as a sorted sequence (lexicographic order)
     * @return all keys as a sorted sequence (lexicographic order)
     */
    @Override
    public String[] allKeys() {
        String[] sortedSequence = new String[size];
        if (size == 0) {
            System.out.println();
            sortedSequence[0] = "There is nothing to display; list is empty.\n";
        }
        int index = 0;
        Node temp = root;
        while(temp.hasLeft()) {
            sortedSequence[index] = temp.getValue();
            temp = temp.getLeft();
            index++;
            if (temp.getLeft() == null && temp.getRight() != null) {
                temp = temp.getParent().getLeft();
                sortedSequence[index] = temp.getValue();
                index++;
            }
        }
        //NOT COMPLETE
        return sortedSequence;
    }

}
