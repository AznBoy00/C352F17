
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
public class ArrayLL {
    private class Node {
        private int value;
        private Node next;
        private Node previous;
        
        public Node(int x, Node n, Node p) {
            value = x;
            next = n;
            previous = p;
        }
    }
    
    private Node head;
    private Node tail;
    private int size;
    
    public ArrayLL() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public int getSize() {
            return size;
    }
    
    public int getItemAt(int index) {
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
    
    public void append(int v) {
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
        System.out.println("List size is: " + size + ". The content it: ");
        Node temp = head;
        while(temp != tail) {
            System.out.print("" + temp.value  + ", ");
            temp = temp.next;		
        }
            System.out.println("" + temp.value  + ".");
	}
}
