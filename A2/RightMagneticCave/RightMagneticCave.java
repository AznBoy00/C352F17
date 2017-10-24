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
        int a[] = {8, 16, 10, 4, 6, 10, 2, 12, 8, 0};
        int b[] = {10, 16, 4, 6, 2, 10, 0};
        int c[] = {0, 0};
        int d[];
        int e[];
        int f[];
        int g[];
        int h[];
        int i[];
        int j[];
        int k[];
        int l[];
        int m[];
        int n[];
        int o[];
        int p[];
        int q[];
        int r[];
        int s[];
        int t[];
        System.out.println(recursiveRMC(0, c));
        //linkedListRMC(array);
    }
    
    public static void test(int pos, int arr[]) {
        Scanner sc = new Scanner(System.in);
        int i;
        int movable;
        // Define how many positions you can move to the left of right.
        if (arr[pos] % 2 == 0) {
            movable = arr[pos] / 2;
        } else {
            movable = arr[pos] / 2 + 1;
        }
        if (pos == arr.length - 1 && arr[pos] == 0) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println("Your current position is at index " + pos + " with the value " + arr[pos]);
            System.out.println("Game Complete!");
        } else {
            System.out.println("The array is: ");
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println("Your current position is at index " + pos + " with the value " + arr[pos]);
            if (pos + movable > arr.length - 1 && pos - movable < 0) {
                System.out.println("You are stuck, please play again.");
                System.exit(1);
            }
            System.out.println("You can move " + movable + " positions.");
            System.out.println("Where do you want to go? 1 = left, 2 = right");
            i = sc.nextInt();
            switch(i) {
                case 1:
                    if (pos - movable < 0) {
                        System.out.println("\n\nYou cannot move left, let's try to move right...");
                        if (pos + movable > arr.length - 1) {
                            System.out.println("It looks like you are stuck, please play again.");
                            break;
                        }
                        recursiveRMC(pos + movable, arr);
                        break;
                    }
                    recursiveRMC(pos - movable, arr);
                    break;
                case 2:
                    if (pos + movable > arr.length - 1) {
                        System.out.println("\n\nYou cannot move right, let's try to move left...");
                        if (pos + movable < 0) {
                            System.out.println("It looks like you are stuck, please play again.");
                            break;
                        }
                        recursiveRMC(pos - movable, arr);
                        break;
                    }
                    recursiveRMC(pos + movable, arr);
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
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
            return false;
        }
        if (pos == arr.length - 1 && arr[pos] == 0) {
            return true;
        }
        if (pos + movable > arr.length - 1 && pos - movable < 0) {
            return false;
        }
        if (pos - movable < 0) {
            iterationNumber++;
            return recursiveRMC(pos + movable, arr);
        } else {
            iterationNumber++;
            return recursiveRMC(pos - movable, arr);
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
            if (arr[pos] % 2 == 0) {
                movable = list.getItemAt(pos) / 2;
            } else {
                movable = list.getItemAt(pos) / 2 + 1;
            }
            if (pos + movable > list.getSize() - 1 && pos - movable < 0) {
                return false;
            }
            if (pos - movable < 0) {
                for (int i = 0; i < movable; i++) {
                    
                }
            } else {
                return checkRecursiveSolution(pos - movable, arr);
            }
        } while (pos != list.getSize() - 1 && list.getItemAt(pos) != 0);
        
        return true;
    }
}
