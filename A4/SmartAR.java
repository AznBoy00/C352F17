
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lin_K
 */
public class SmartAR {
    public static void main(String[] args) {
        final String test1 = "ar_test_file1.txt";
        final String test2 = "ar_test_file2.txt";
        final String test3 = "ar_test_file3.txt";
        FileInputStream fis;
        Scanner i = new Scanner(System.in);
        int input;
        
        System.out.println("COMP352 A4 - Test Menu");
        System.out.println("Which file would you like to test? (1-3)");
        input = i.nextInt();
        
        try {
            switch (input) {
                case 1:
                    fis = new FileInputStream(test1);
                    break;
                case 2:
                    fis = new FileInputStream(test2);
                    break;
                case 3:
                    fis = new FileInputStream(test3);
                    break;
                default:
                    System.out.println("Error.");
                    System.exit(1);
                    break;
            }
        } catch (FileNotFoundException e) { // Catch file not found exception to prevent errors.
            System.out.println("FileInputStream error caught.");
            System.exit(1);
        }
    }
}
