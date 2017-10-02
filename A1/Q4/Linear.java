/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author Lin_K
 */
public class Linear {
    public static void main(String[] args) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("out_exponential.txt"));// pw for out_exponential.txt
            
            System.out.println("Running exponential tetranacciExponential() recursive method:");
            pw.write("\nRunning exponential tetranacciExponential() recursive method:\n"); // print the following line to the txt

            for (int i = 5; i <= 100; i+=5) { // will attempt to find the first 100 Tetranacci numbers
                System.out.println("Running tetranacciExponential(" + i + "): ");
                pw.write("Running tetranacciExponential(" + i + "): ");
                long startTime = System.nanoTime();// tracks runtime in nanoseconds (start)
                int result = tetranacciLinear(i, 0, 0, 0, 1);
                System.out.println(result);
                pw.write("\nTetranacci(" + i + "): " + result); //print the result after finding it
                long endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
                long totalTime = endTime - startTime;
                System.out.println("Runtime: " + totalTime + "ns\n");
                pw.write("\nRuntime: " + totalTime + "ns\n"); //prints the time on the txt file
            }
            pw.close();
        } catch (FileNotFoundException e) { // Catch file not found exception to prevent errors.
            System.out.println("File not found.");
        }
    }
    /*
        Algorithm to find the tetranacci numbers in a linear recursive fashion by inputing x amount of numbers with a, b, c, d to be 0,0,0,1 respectively in order to obtain the proper algorithm result.
    */
    public static int tetranacciLinear(int x, int a, int b, int c, int d) {
        if (x < 3) //Base case 1
            return 0;
        if (x == 3) // Base case 2
            return d;
        else
            return tetranacciLinear(x - 1, b, c, d, a + b + c + d); // recursive case
    }
}