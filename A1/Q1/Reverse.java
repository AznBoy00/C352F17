/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q1;

/**
 *
 * @author Lin_K
 */
public class Reverse {
    public static void main(String[] args) {
        int array[] = {1,2,3,4,5,6,7,8,9,10,11,12};
        System.out.print("Input array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        int x, y, z;
        int a, b, c, d;
        if (array.length >= 1) {
            a = 0;
            b = ((array.length)/2);
            if (array.length % 2 == 0) {
                c = ((array.length)/2);
            } else {
                c = ((array.length)/2) + 1;
            }
            d = array.length - 1;
            while (a < b - 1) {
                    x = array[a];
                    y = array[a+1];
                    z = x;
                    x = y;
                    y = z;
                    array[a] = x;
                    array[a+1] = z;
                    a += 2;
            }
            while (c < d) {
                x = array[c];
                y = array[c+1];
                z = x + y;
                y = z;
                array[c+1] = z;
                c += 2;
            }
        }
        System.out.print("\nNew Array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
