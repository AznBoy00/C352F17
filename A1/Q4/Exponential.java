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
            
            System.out.println("Running exponential tetranacciExponential() recursive method:");
            pw.write("\nRunning exponential tetranacciExponential() recursive method:\n");

            for (int i = 5; i <= 35; i+=5) {
                System.out.println("Running tetranacciExponential(" + i + "): ");
                pw.write("Running tetranacciExponential(" + i + "): ");
                long startTime = System.nanoTime();
                int result = tetranacciExponential(i);
                System.out.println(result);
                pw.write("\nTetranacci(" + i + "): " + result);
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
    
    public static int tetranacciExponential(int x) {
        if (x < 3)
            return 0;
        if (x == 3)
            return 1;
        else
            return tetranacciExponential(x-1) + tetranacciExponential(x-2) + tetranacciExponential(x-3) + tetranacciExponential(x-4);
    }
}