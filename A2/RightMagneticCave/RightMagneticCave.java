/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
/**
 *
 * @author Lin_K
 */
public class RightMagneticCave {
    public static int iterationNumber = 0;
    
    public static void main(String[] args) {
        int[] a = {8,16,10,4,6,10,2,12,8,0};
        int[] b = {10, 9, 1, 7, 6, 4, 3, 9, 0};
        int[] c = {2, 0};
        int[] d = {10, 4, 8, 9, 3, 0};
        int[] e = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0};
        int[] f = {3, 8, 4, 10, 6, 0};
        int[] g = {10, 6, 7, 9, 4, 8, 0};
        int[] h = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] i = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 0};
        int[] j = {2, 4, 6, 8, 10, 0};
        int[] k = {10, 8, 6, 4, 2, 0};
        int[] l = {1, 3, 5, 7, 9, 0};
        int[] m = {9, 7, 5, 3, 1, 0};
        int[] n = {1, 4, 7, 4, 1, 0};
        int[] o = {2, 5, 8, 5, 2, 0};
        int[] p = {1, 2, 4, 5, 7, 7, 5, 4, 2, 1, 0};
        int[] q = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 0};
        int[] r = {10, 2, 10, 2, 10, 2, 0};
        int[] s = {12, 9, 6, 3, 6, 5, 7, 3, 0};
        int[] t = {10, 2, 8, 2, 6, 2, 4, 2, 0};
        int[] u = {10,16,4,6,2,10,0};
        
        System.out.println(recursiveRMC(0, a));
        System.out.println(recursiveRMC(0, b));
        System.out.println(recursiveRMC(0, c));
        System.out.println(recursiveRMC(0, d));
        System.out.println(recursiveRMC(0, e));
        System.out.println(recursiveRMC(0, f));
        System.out.println(recursiveRMC(0, g));
        System.out.println(recursiveRMC(0, h));
        System.out.println(recursiveRMC(0, i));
        System.out.println(recursiveRMC(0, j));
        System.out.println(recursiveRMC(0, k));
        System.out.println(recursiveRMC(0, l));
        System.out.println(recursiveRMC(0, m));
        System.out.println(recursiveRMC(0, n));
        System.out.println(recursiveRMC(0, o));
        System.out.println(recursiveRMC(0, p));
        System.out.println(recursiveRMC(0, q));
        System.out.println(recursiveRMC(0, r));
        System.out.println(recursiveRMC(0, s));
        System.out.println(recursiveRMC(0, t));
        System.out.println(recursiveRMC(0, u));
        System.out.println("asd");
        System.out.println(linkedListRMC(a));
        System.out.println(linkedListRMC(b));
        System.out.println(linkedListRMC(c));
        System.out.println(linkedListRMC(d));
        System.out.println(linkedListRMC(e));
        System.out.println(linkedListRMC(f));
        System.out.println(linkedListRMC(g));
        System.out.println(linkedListRMC(h));
        System.out.println(linkedListRMC(i));
        System.out.println(linkedListRMC(j));
        System.out.println(linkedListRMC(k));
        System.out.println(linkedListRMC(l));
        System.out.println(linkedListRMC(m));
        System.out.println(linkedListRMC(n));
        System.out.println(linkedListRMC(o));
        System.out.println(linkedListRMC(p));
        System.out.println(linkedListRMC(q));
        System.out.println(linkedListRMC(r));
        System.out.println(linkedListRMC(s));
        System.out.println(linkedListRMC(t));
        System.out.println(linkedListRMC(u));
        //linkedListRMC(array);
    }
    
    public static boolean recursiveRMC(int pos, int arr[]) {
        int movable;
        // Define how many positions you can move to the left of right.
        if (arr[pos] % 2 == 0) {
            movable = arr[pos] / 2;
        } else {
            movable = arr[pos] / 2 + 1;
        }
        if (iterationNumber >= 5 * arr.length) {
            iterationNumber = 0;
            return false;
        }
        if (pos == arr.length - 1 && arr[pos] == 0) {
            iterationNumber = 0;
            return true;
        }
        if (pos + movable > arr.length - 1 && pos - movable < 0) {
            iterationNumber = 0;
            return false;
        }
        if (pos + movable > arr.length - 1) {
            iterationNumber++;
            return recursiveRMC(pos - movable, arr);
        } else {
            iterationNumber++;
            return recursiveRMC(pos + movable, arr);
        }
    }
    
    public static boolean linkedListRMC(int arr[]) {
        ArrayLL list = new ArrayLL();
        int pos = 0;
        int movable;
        
        for (int i = 0; i < arr.length; i++) {
            list.addToList(arr[i]);
        }
        //list.showListContents();

        do {
            if (iterationNumber >= 5 * list.getSize()) {
                iterationNumber = 0;
                return false;
            }
            if (list.getItemAt(pos) % 2 == 0) {
                movable = list.getItemAt(pos) / 2;
            } else {
                movable = list.getItemAt(pos) / 2 + 1;
            }
            if (pos + movable > list.getSize() - 1 && pos - movable < 0) {
                iterationNumber = 0;
                return false;
            }
            if (pos + movable > list.getSize() - 1) {
                iterationNumber++;
                pos -= movable;
            } else {
                iterationNumber++;
                pos += movable;
            }
        } while (pos != list.getSize() && list.getItemAt(pos) != 0);
        
        return true;
    }
}
