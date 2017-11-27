
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
public class CarRegistryLL {
    private class Node {
        private String value;
        private Node next;
        private Node previous;
        
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
    
    public String getItemAt(int index) {
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
    
    public void addToList(String v) {
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
