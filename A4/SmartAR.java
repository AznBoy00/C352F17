
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
        RegistryGenerator registry;
        String fis = "";
        Scanner i = new Scanner(System.in);
        int input;
        
        System.out.println("COMP352 A4 - Test Menu");
        System.out.println("Which file would you like to test? (1-3)");
        
        do {
            input = i.nextInt();
        } while(input < 1 || input > 3);
        
        switch (input) {
            case 1:
                fis = test1;
                break;
            case 2:
                fis = test2;
                break;
            case 3:
                fis = test3;
                break;
            default:
                System.out.println("Error.");
                System.exit(1);
                break;
        }
        
        registry = new RegistryGenerator(fis);
    }
}
