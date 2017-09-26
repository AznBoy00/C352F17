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
public class Exponential {
    public static void main(String[] args) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("out_exponential.txt"));
            
            System.out.println("Running exponential tetranacciR() recursive method:");
            pw.write("\nRunning exponential tetranacciR() recursive method:\n");

            for (int i = 5; i <= 40; i+=5) {
                System.out.println("Running tetranacci(" + i + "): ");
                pw.write("Running tetranacci(" + i + "): ");
                long startTime = System.nanoTime();
                System.out.println(tetranacciR(i));
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println("Runtime: " + totalTime + "ns\n");
                pw.write("\nRuntime: " + totalTime + "ns\n");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
    
    public static int tetranacciR(int x) {
        if (x < 3)
            return 0;
        if (x == 3)
            return 1;
        else
            return tetranacciR(x-1) + tetranacciR(x-2) + tetranacciR(x-3) + tetranacciR(x-4);
    }
}