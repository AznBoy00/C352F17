/**
 * This class defines a restructure based upon the definition of an AVL tree. When nodes of a tree are unbalanced
 * the first bad node from the one altered (the first where heights differ by more than 1) is sent to this class.
 * From this node, an in-order traversal defines the next two nodes (y and x), the children of the bad nodes and the
 * children of y and x makes up nodes x, y, z, t0, t1, t2, t3. These 7 nodes are rearranged in such a way that
 * upon re-attaching to the tree, the tree is no longer unbalanced.
 * @param <E> Takes any type which implements the comparable class
 */
class Restructure<E extends Comparable<E>> {

    enum RotationType {LEFT_LINEAR, RIGHT_LINEAR, LEFT_SKEW, RIGHT_SKEW, NONE};


    static <E extends Comparable<E>> void restructure(Node<E> badNode, AVLTree<E> T){

        //Gets  nodes x, y and z
        Node<E> z = badNode;
        Node<E> y = getY(z);
        Node<E> x = getX(y);

        //Gets the rotation required based upon the location of nodes x,y and z in relation to each other
        RotationType rotation = getRotation(x,y,z);
        if (rotation == RotationType.NONE)
            System.out.println("\nFatal error: undefined rotation type");

        //Knowing what the rotation is, and which nodes are x,y and z, nodes a,b,c,t0,t1,t2,t3 can now be acquired
        Node<E> a = getA(x,y,z,rotation);
        Node<E> b = getB(x,y,z,rotation);
        Node<E> c = getC(x,y,z,rotation);
        Node<E> t0 = getT0(x,y,z,rotation);
        Node<E> t1 = getT1(x,y,z,rotation);
        Node<E> t2 = getT2(x,y,z,rotation);
        Node<E> t3 = getT3(x,y,z,rotation);

        //A temporary node is created in case no parent exists above the bad node, otherwise the parent is acquired
        Node<E> temp = new Node<E>();
        Node<E> parent = badNode.getParent();
        if (parent == null)
            parent = temp;	//Will temporarily hold values during binding

        //Determines whether the bad node is the parents left or right child.
        boolean leftConnection = false;
        if(parent == temp)
            leftConnection = (rotation == RotationType.LEFT_SKEW || rotation == RotationType.LEFT_LINEAR);
        else
            leftConnection = (parent.getLeft()==a||parent.getLeft()==b||parent.getLeft()==c);

        //Binds all the nodes based upon the details determined above
        bindNodes(a,b,c,t0,t1,t2,t3, parent,leftConnection);
        if (parent == temp)	//otherwise the root is fine
            setRoot(parent, T);

        //Adjusts the heights of the nodes from nodes a,c up until the root node of the tree.
        adjustHeightLinear(a,c);

    }

    /**
     * This method is ONLY invoked if a temporary node was used to do the restructuring, if this happens
     * the root of the tree needs to be reset depending on whether the temp nodes left node or right node exists.
     * Whichever is present becomes the new root.
     * @param parent The parent of the bad node (this can be a temporary node if no such parent exists)
     * @param T A tree structure with an accessible root node
     * @param <E> The tree structure must have nodes which implement a comparable value
     */
    private static <E extends Comparable<E>> void setRoot(Node<E> parent, AVLTree<E> T){
        //This should never happen
        if(parent.hasRight() && parent.hasLeft())
            System.out.println("Error: unexpected state in setting root node during restructuring");
            //If the temp node has a left node, this is then the new root
        else if(parent.hasLeft()) {
            T.setRoot(parent.getLeft());
            T.getRoot().setParent(null);
        }
        //If the temp node has a right node, then is then the new root
        else if(parent.hasRight()){
            T.setRoot(parent.getRight());
            T.getRoot().setParent(null);
        }
        //This should never happen
        else
            System.out.println("Error: no children in setting root node during restructuring");
    }

    /**
     * Nodes around node z are assessed for their relative heights, from these details node y is determined
     * @param z The bad node given to start the restructure
     * @param <E> A comparable value
     * @return Node y determined by where nodes are positioned with relation to node z
     */
    private static <E extends Comparable<E>> Node<E> getY(Node<E> z){
        //Create an undefined node y
        Node<E> y = null;
        //Find the height of the left and right children of z
        int zLeft = (z.hasLeft() ? z.getLeft().getHeight() : -1);
        int zRight = (z.hasRight() ? z.getRight().getHeight() : -1);
        //If z has right and left children, then the one with the greatest height becomes node y
        //If they are equal, then the right node becomes y
        if(z.hasLeft() && z.hasRight()) {
            if (zLeft > zRight)
                y = z.getLeft();
            else if (zRight >= zLeft) {
                y = z.getRight();
            }
            //Should never happen
            else
                System.out.println("Error getting node Y in restructuring");
        }
        //If no right child exists, then y is the left child
        else if(!z.hasRight()) {
            y = z.getLeft();
        }
        else
            //If no left child exists, then y is the right child
            y = z.getRight();
        return y;
    }

    /**
     * Nodes around node y are assessed for their relative heights, from these details node x is determined
     * @param y The node following from the bad node, determined by the getY method
     * @param <E> A comparable value
     * @return Node x determined by where nodes are positioned with relation to node y
     */
    private static <E extends Comparable<E>> Node<E> getX(Node<E> y){
        //Create an undefined node y
        Node<E> x = null;
        //Find the height of the left and right children of y
        int yLeft = (y.hasLeft() ? y.getLeft().getHeight() : -1);
        int yRight = (y.hasRight() ? y.getRight().getHeight() : -1);
        //If y has right and left children, then the one with the greatest height becomes node x
        //If they are equal, then the right node becomes x
        if(y.hasRight() && y.hasLeft()) {
            if (yLeft > yRight)
                x = y.getLeft();
            else if (yRight >= yLeft) {
                x = y.getRight();
            }
            //Should never happen
            else
                System.out.println("Error getting node X in restructuring");
        }
        //If no right child exists, then x is the left child
        else if(!y.hasRight())
            x = y.getLeft();
            //If no left child exists, then x is the right child
        else
            x = y.getRight();
        return x;
    }

    /**
     * Determines where node a is depending on what rotation type is required
     * @param x Node x, as determined by the getX method
     * @param y Node y, as determined by the getY method
     * @param z Node z, the bad node sent to the restructure class
     * @param rotation The rotation type, determined in the getRotation method
     * @param <E> All generic value types are allowed which implement comparable
     * @return Node a, determined by what kind of rotation is required to restructure
     */
    private static <E extends Comparable<E>> Node<E> getA(Node<E> x, Node<E> y, Node<E> z, RotationType rotation){
        if(rotation == RotationType.RIGHT_LINEAR)
            return z;
        else if(rotation == RotationType.LEFT_LINEAR)
            return x;
        else if(rotation == RotationType.RIGHT_SKEW)
            return z;
        else if(rotation == RotationType.LEFT_SKEW)
            return y;
        else return null;
    }

    /**
     * Determines where node b is depending on what rotation type is required
     * @param x Node x, as determined by the getX method
     * @param y Node y, as determined by the getY method
     * @param z Node z, the bad node sent to the restructure class
     * @param rotation The rotation type, determined in the getRotation method
     * @param <E> All generic value types are allowed which implement comparable
     * @return Node b, determined by what kind of rotation is required to restructure
     */
    private static <E extends Comparable<E>> Node<E> getB(Node<E> x, Node<E> y, Node<E> z, RotationType rotation){
        if(rotation == RotationType.RIGHT_LINEAR)
            return y;
        else if(rotation == RotationType.LEFT_LINEAR)
            return y;
        else if(rotation == RotationType.RIGHT_SKEW)
            return x;
        else if(rotation == RotationType.LEFT_SKEW)
            return x;
        else return null;
    }

    /**
     * Determines where node c is depending on what rotation type is required
     * @param x Node x, as determined by the getX method
     * @param y Node y, as determined by the getY method
     * @param z Node z, the bad node sent to the restructure class
     * @param rotation The rotation type, determined in the getRotation method
     * @param <E> All generic value types are allowed which implement comparable
     * @return Node c, determined by what kind of rotation is required to restructure
     */
    private static <E extends Comparable<E>> Node<E> getC(Node<E> x,Node<E> y, Node<E> z, RotationType rotation){
        if(rotation == RotationType.RIGHT_LINEAR)
            return x;
        else if(rotation == RotationType.LEFT_LINEAR)
            return z;
        else if(rotation == RotationType.RIGHT_SKEW)
            return y;
        else if(rotation == RotationType.LEFT_SKEW)
            return z;
        else return null;
    }

    /**
     * Determines where node t0 is depending on what rotation type is required
     * @param x Node x, as determined by the getX method
     * @param y Node y, as determined by the getY method
     * @param z Node z, the bad node sent to the restructure class
     * @param rotation The rotation type, determined in the getRotation method
     * @param <E> All generic value types are allowed which implement comparable
     * @return Node t0, determined by what kind of rotation is required to restructure
     */
    private static <E extends Comparable<E>> Node<E> getT0(Node<E> x,Node<E> y, Node<E> z, RotationType rotation){
        if(rotation == RotationType.RIGHT_LINEAR)
            return z.getLeft();
        else if(rotation == RotationType.LEFT_LINEAR)
            return x.getLeft();
        else if(rotation == RotationType.RIGHT_SKEW)
            return z.getLeft();
        else if(rotation == RotationType.LEFT_SKEW)
            return y.getLeft();
        else return null;
    }

    /**
     * Determines where node t1 is depending on what rotation type is required
     * @param x Node x, as determined by the getX method
     * @param y Node y, as determined by the getY method
     * @param z Node z, the bad node sent to the restructure class
     * @param rotation The rotation type, determined in the getRotation method
     * @param <E> All generic value types are allowed which implement comparable
     * @return Node t1, determined by what kind of rotation is required to restructure
     */
    private static <E extends Comparable<E>> Node<E> getT1(Node<E> x,Node<E> y, Node<E> z, RotationType rotation){
        if(rotation == RotationType.RIGHT_LINEAR)
            return y.getLeft();
        else if(rotation == RotationType.LEFT_LINEAR)
            return x.getRight();
        else if(rotation == RotationType.RIGHT_SKEW)
            return x.getLeft();
        else if(rotation == RotationType.LEFT_SKEW)
            return x.getLeft();
        else return null;
    }

    /**
     * Determines where node t2 is depending on what rotation type is required
     * @param x Node x, as determined by the getX method
     * @param y Node y, as determined by the getY method
     * @param z Node z, the bad node sent to the restructure class
     * @param rotation The rotation type, determined in the getRotation method
     * @param <E> All generic value types are allowed which implement comparable
     * @return Node t2, determined by what kind of rotation is required to restructure
     */
    private static <E extends Comparable<E>> Node<E> getT2(Node<E> x,Node<E> y, Node<E> z, RotationType rotation){
        if(rotation == RotationType.RIGHT_LINEAR)
            return x.getLeft();
        else if(rotation == RotationType.LEFT_LINEAR)
            return y.getRight();
        else if(rotation == RotationType.RIGHT_SKEW)
            return x.getRight();
        else if(rotation == RotationType.LEFT_SKEW)
            return x.getRight();
        else return null;
    }
    /**
     * Determines where node t3 is depending on what rotation type is required
     * @param x Node x, as determined by the getX method
     * @param y Node y, as determined by the getY method
     * @param z Node z, the bad node sent to the restructure class
     * @param rotation The rotation type, determined in the getRotation method
     * @param <E> All generic value types are allowed which implement comparable
     * @return Node t3, determined by what kind of rotation is required to restructure
     */
    private static <E extends Comparable<E>> Node<E> getT3(Node<E> x,Node<E> y, Node<E> z, RotationType rotation){
        if(rotation == RotationType.RIGHT_LINEAR)
            return x.getRight();
        else if(rotation == RotationType.LEFT_LINEAR)
            return z.getRight();
        else if(rotation == RotationType.RIGHT_SKEW)
            return y.getRight();
        else if(rotation == RotationType.LEFT_SKEW)
            return z.getRight();
        else return null;
    }

    /**
     * Uses the relative positions of x,y and z to determine which rotation will be required to successfully restructure
     * @param x Node x, as determined by the getX method
     * @param y Node y, as determined by the getY method
     * @param z Node z, the bad node sent to the restructure class
     * @param <E> All generic value types are allowed which implement comparable
     * @return An enum describing which rotation will be required to restructure the tree
     */
    private static <E extends Comparable<E>> RotationType getRotation(Node<E> x, Node<E> y, Node<E> z) {
        if (z.getRight()==y && y.getRight()==x)
            return RotationType.RIGHT_LINEAR;
        else if (z.getLeft()==y && y.getLeft()==x)
            return RotationType.LEFT_LINEAR;
        else if (z.getRight()==y && y.getLeft()==x)
            return RotationType.RIGHT_SKEW;
        else if (z.getLeft()==y && y.getRight()==x)
            return RotationType.LEFT_SKEW;
        else
            return RotationType.NONE;

    }

    /**
     * This method uses nodes a,b,c,t0,t1,t2,t3 and parent as well as a boolean determining whether the parent connects
     * to these via its left or right node. These are bound together Making sure left and right children and parents
     * are all updated to reflect the correct structure and relations.
     * @param a Determined by the getA method
     * @param b Determined by the getB method
     * @param c Determined by the getC method
     * @param t0 Determined by the getT0 method
     * @param t1 Determined by the getT1 method
     * @param t2 Determined by the getT2 method
     * @param t3 Determined by the getT3 method
     * @param parent The parent of the bad node (possibly a temporary node)
     * @param left A boolean which is true when the parent node's left child the bad node, otherwise false
     * @param <E> All generic value types are allowed which implement comparable
     */
    private static <E extends Comparable<E>> void bindNodes(Node<E> a, Node<E> b, Node<E> c, Node<E> t0, Node<E> t1, Node<E> t2, Node<E> t3,Node<E> parent, boolean left){
        //Set positions of b's children
        b.setLeft(a);
        b.setRight(c);
        a.setLeft(t0);
        //Set positions of t0, t1,t2,t3 to a's and c's children. Their parents to a and c.
        if(t0!=null)
            t0.setParent(a);
        a.setRight(t1);
        if(t1!=null)
            t1.setParent(a);
        c.setLeft(t2);
        if(t2!=null)
            t2.setParent(c);
        c.setRight(t3);
        if(t3!=null)
            t3.setParent(c);
        //Set a's and c's parent to b
        a.setParent(b);
        c.setParent(b);
        //Set b to the parents left child if left is true, right child otherwise.
        if(left)
            parent.setLeft(b);
        else
            parent.setRight(b);
        //Set b's parent to parent
        b.setParent(parent);

    }

    /**
     * Adjusts the heights of nodes from the bad node's defined a and c, up until the root of the tree
     * All other nodes in the tree are left unaltered.
     * @param a Determined by the getA method
     * @param c Determined by the getC method
     * @param <E> All generic value types are allowed which implement comparable
     */
    public static <E extends Comparable<E>> void adjustHeightLinear(Node<E> a, Node<E> c){
        //Changes the heights of a and c to be correct
        adjustHeight(a);
        adjustHeight(c);
        //If the parent exists then iterate and adjust heights until the root node is reached.
        while(a.getParent() != null){
            a = a.getParent();
            adjustHeight(a);
        }
    }

    /**
     * This method checks the left and right nodes of the node toCheck. If they don't exist the height is 0
     * else the height is 1 more than the greatest height of left or right.
     * @param toCheck The node which needs to have its height fixed.
     * @param <E> All generic value types are allowed which implement comparable
     */
    static <E extends Comparable<E>> void adjustHeight(Node<E> toCheck){
        //If no children exist, then the height is 0
        if(!toCheck.hasLeft() && !toCheck.hasRight())
            toCheck.setHeight(0);
            //If no right child exists, then the height is 1 more than the left child.
        else if(!toCheck.hasRight())
            toCheck.setHeight(toCheck.getLeft().getHeight()+1);
            //If no left child exists, then the height is 1 more than the right child.
        else if(!toCheck.hasLeft())
            toCheck.setHeight(toCheck.getRight().getHeight()+1);
            //If both children exist, then the height is one more than the greatest height of the two
        else if(toCheck.hasLeft() && toCheck.hasRight())
            toCheck.setHeight(Integer.max(toCheck.getLeft().getHeight(),toCheck.getRight().getHeight())+1);
    }

}

