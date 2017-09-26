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
            PrintWriter pw = new PrintWriter(new FileOutputStream("out_Linear.txt"));
            
            System.out.println("Running exponential tetranacciLinear() recursive method:");
            pw.write("\nRunning exponential tetranacciLinear() recursive method:\n");

            for (int i = 5; i <= 35; i+=5) {
                System.out.println("Running tetranacciLinear(" + i + "): ");
                pw.write("Running tetranacciLinear(" + i + "): ");
                long startTime = System.nanoTime();
                int result = tetranacciLinear(i, 0, 0, 0, 1);
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
    
    public static int tetranacciLinear(int x, int a, int b, int c, int d) {
        if (x < 3)
            return 0;
        if (x == 3)
            return d;
        else
            return tetranacciLinear(x - 1, b, c, d, a + b + c + d);
    }
}