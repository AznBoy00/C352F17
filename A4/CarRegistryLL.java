
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
public class CarRegistryLL implements Registry{
    
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
    private Car c;

    /**
     * Get/Set for Car object.
     * @return Car
     */
    public Car getC() {
        return c;
    }

    public void setC(Car c) {
        this.c = c;
    }
    
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
     * @param index
     * @return key of index
     */
    public String getKey(int index) {
        if (index > size -1) {
                System.out.println("ERROR: Index is out of range! Terminating program.");
                throw new NoSuchElementException();
        }
        int i = 0;
        Node temp = head;
        while(i != index) {
                temp = temp.next;
                i++;
        }
        return temp.value;
    }
    
    /**
     * Return Car object of the input key
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
        while(key.compareTo(temp.getCar().getKey()) != 0) {
            temp = temp.next;
        }
        return temp.getCar();
    }
    
    /**
     * Default LL add()
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
    
    public String remove() {
        return "";
    }
    
    /**
     * Show content of list.
     */
    public void showListContents() {
        if (size == 0) {
            System.out.println("There is nothing to display; list is empty." );
            return;
        }	
        System.out.println("List size is: " + size + ". The content is: ");
        Node temp = head;
        while(temp != tail) {
            System.out.print("" + temp.value  + ", ");
            temp = temp.next;		
        }
        System.out.println("" + temp.value  + ".");
    }
}
