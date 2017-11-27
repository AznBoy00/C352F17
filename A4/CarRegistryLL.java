
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lin_K
 */
public class CarRegistryLL implements Registry {

    /**
     * Node class for LL
     */
    private class Node {

        private String value;
        private Node next;
        private Node previous;
        private Car c;

        public Car getCar() {
            return c;
        }

        public void setCar(Car c) {
            this.c = c;
        }

        public Node(String x, Node n, Node p) {
            value = x;
            next = n;
            previous = p;
        }

        public void setX(String y) {
            value = y;
        }

        public void setNext(Node xt) {
            next = xt;
        }

        public void setPrevious(Node pr) {
            previous = pr;
        }

        public String getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrevious() {
            return previous;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    /**
     * Default Constructor
     */
    public CarRegistryLL() {
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * getKey at an index
     *
     * @param index
     * @return key of index
     */
    public String getKey(int index) {
        if (index > size - 1) {
            System.out.println("ERROR: Index is out of range! Terminating program.");
            throw new NoSuchElementException();
        }
        int i = 0;
        Node temp = head;
        while (i != index) {
            temp = temp.next;
            i++;
        }
        return temp.value;
    }

    /**
     * Return Car object of the input key
     *
     * @param key
     * @return
     */
    @Override
    public Car getValues(String key) {
        if (key.length() > 12 || key.length() < 6) {
            System.out.println("Key entered is too long or too short. (Only strings between 6-12 is allowed.)");
            return null;
        }
        Node temp = head;
        while (key.compareTo(temp.getCar().getKey()) != 0) {
            temp = temp.next;
        }
        return temp.getCar();
    }

    /**
     * Default LL add()
     *
     * @param v key
     */
    public void add(String v) {
        if (head == null) {
            Node temp = new Node(v, null, null);
            head = temp;
            head.next = head;
            head.previous = head;
            tail = head;
        } else {
            Node temp = new Node(v, head, tail);
            head.previous = temp;
            tail.next = temp;
            tail = temp;
            temp = null;
        }
        size++;
    }

    /**
     * add an entry for the given key and value
     *
     * @param key Key
     * @param c Car
     */
    @Override
    public void add(String key, Car c) {
        if (head == null) {
            Node temp = new Node(key, null, null);
            head = temp;
            head.next = head;
            head.previous = head;
            tail = head;
        } else {
            Node temp = new Node(key, head, tail);
            head.previous = temp;
            tail.next = temp;
            tail = temp;
            temp = null;
        }
        c = new Car(key);
        size++;
    }

    /**
     * remove the entry for the given key
     * @return
     */
    @Override
    public void remove(String key) {
        int index;
        if (key.length() > 12 || key.length() < 6) {
            System.out.println("Key entered is too long or too short. (Only strings between 6-12 is allowed.)");
        } else {
            Node temp = head;
            index  = 0;
            while (key.compareTo(temp.getCar().getKey()) != 0) {
                temp = temp.next;
                index++;
            }
            if (index > size - 1) {
                System.out.println("ERROR: Given index is out of range! Program will terminate. \n");
                throw new NoSuchElementException();
            }

            // Handle the special cases when deletion on head or tail
            if (index == 0) {
                System.out.println("\nDeleting node with value " + head.getValue() + " from index # " + index + ".");
                head = head.next;
                head.previous = tail;
                tail.next = head;
            } else if (index == size - 1) {
                System.out.println("\nDeleting node with value " + tail.getValue() + " from index # " + index + ".");
                tail = tail.previous;
                tail.next = head;
                head.previous = tail;
            } else {
                // Now we are pointing at the node preceding index where deletion should take place
                System.out.println("\nDeleting node with value " + temp.getValue() + " from index # " + index + ".");
                temp.next = temp.next.next;
                temp.next.previous = temp;
            }
            size--;
        }
    }

    /**
     * return the key for the successor of key
     *
     * @param key
     * @return the key for the successor of key
     */
    @Override
    public String nextKey(String key) {
        if (key.length() > 12 || key.length() < 6) {
            System.out.println("Key entered is too long or too short. (Only strings between 6-12 is allowed.)");
            return null;
        }
        Node temp = head;
        while (key.compareTo(temp.getCar().getKey()) != 0) {
            temp = temp.next;
        }
        return temp.next.getValue();
    }

    /**
     * return the key for the predecessor of key
     *
     * @param key
     * @return the key for the predecessor of key
     */
    @Override
    public String prevKey(String key) {
        if (key.length() > 12 || key.length() < 6) {
            System.out.println("Key entered is too long or too short. (Only strings between 6-12 is allowed.)");
            return null;
        }
        Node temp = head;
        while (key.compareTo(temp.getCar().getKey()) != 0) {
            temp = temp.next;
        }
        return temp.previous.getValue();
    }

    /**
     * Show content of list.
     */
    public void showListContents() {
        if (size == 0) {
            System.out.println("There is nothing to display; list is empty.");
            return;
        }
        System.out.println("List size is: " + size + ". The content is: ");
        Node temp = head;
        while (temp != tail) {
            System.out.print("" + temp.value + ", ");
            temp = temp.next;
        }
        System.out.println("" + temp.value + ".");
    }
}
