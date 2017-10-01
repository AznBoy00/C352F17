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
        int a[] = {1,2,3,4,5,6,7,8,9,10,11};
        reverse(a);
    }
    
    public static void reverse(int input[]) {
        int array[] = input;
        int a = 0, b = ((array.length)/2) - 1, c, d = array.length -1, temp;
        if (array.length >= 1) {
            if (array.length % 2 == 0) {
                c = ((array.length)/2);
            } else {
                c = ((array.length)/2) + 1;
            }
            while (a < b) {
                    temp = array[a];
                    array[a] = array[a+1];
                    array[a+1] = temp;
                    a += 2;
            }
            while (c < d) {
                array[c+1] = array[c] + array[c+1];
                c += 2;
            }
        }
    }
}
