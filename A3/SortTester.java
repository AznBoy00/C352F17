import java.util.Arrays;
import java.util.Collections;

public class SortTester {

    public static void main(String[] args) {
        //BST
        Tree bstA = new A3BSTree<>();
        Tree bstB = new A3BSTree<>();
        Tree bstC = new A3BSTree<>();
        //BST Reverse
        Tree bstAr = new A3BSTree<>();
        Tree bstBr = new A3BSTree<>();
        Tree bstCr = new A3BSTree<>();
        //AVL
        Tree avlA = new A3AVLTree<>();
        Tree avlB = new A3AVLTree<>();
        Tree avlC = new A3AVLTree<>();
        //AVL Reverse
        Tree avlAr = new A3AVLTree<>();
        Tree avlBr = new A3AVLTree<>();
        Tree avlCr = new A3AVLTree<>();
        
        Number a[] = new Number[10];
        Number b[] = new Number[100];
        Number c[] = new Number[10000];

        long startTime, endTime, totalTime;// tracks runtime in nanoseconds (start)
        
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = i;
        }
        for (int i = 0; i < c.length; i++) {
            c[i] = i;
        }
        System.out.printf("Initial%n%s%n", Arrays.toString(a));
        System.out.printf("Initial%n%s%n", Arrays.toString(b));
        System.out.printf("Initial%n%s%n", Arrays.toString(c));
        
        //N = 10 (A)
        System.out.println("N = " + a.length);
        startTime = System.nanoTime();
        TreeSort.sort(bstA, a);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("BST\t" + totalTime + "ns\n");
        
        startTime = System.nanoTime();
        TreeSort.sort(avlA, a);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("AVL\t" + totalTime + "ns\n");
        
        //Reverse
        Collections.shuffle(Arrays.asList(a));
        //System.out.printf("Shuffled%n%s%n", Arrays.toString(a));

        startTime = System.nanoTime();
        TreeSort.sort(bstAr, a);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("BST(rev-sorted)\t" + totalTime + "ns\n");
        
        startTime = System.nanoTime();
        TreeSort.sort(avlAr, a);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("AVL(rev-sorted)\t" + totalTime + "ns\n");
        
        //B
        System.out.println("N = " + b.length);
        startTime = System.nanoTime();
        TreeSort.sort(bstB, b);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("BST\t" + totalTime + "ns\n");
        
        startTime = System.nanoTime();
        TreeSort.sort(avlB, b);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("AVL\t" + totalTime + "ns\n");
        
        //Reverse
        Collections.shuffle(Arrays.asList(b));
        //System.out.printf("Shuffled%n%s%n", Arrays.toString(b));

        startTime = System.nanoTime();
        TreeSort.sort(bstBr, b);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("BST(rev-sorted)\t" + totalTime + "ns\n");
        
        startTime = System.nanoTime();
        TreeSort.sort(avlBr, b);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("AVL(rev-sorted)\t" + totalTime + "ns\n");
        
        //C
        System.out.println("N = " + c.length);
        startTime = System.nanoTime();
        TreeSort.sort(bstC, c);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("BST\t" + totalTime + "ns\n");
        
        startTime = System.nanoTime();
        TreeSort.sort(avlC, c);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("AVL\t" + totalTime + "ns\n");
        
        //Reverse
        Collections.shuffle(Arrays.asList(c));
        //System.out.printf("Shuffled%n%s%n", Arrays.toString(c));
        
        startTime = System.nanoTime();
        TreeSort.sort(bstCr, c);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("BST(rev-sorted)\t" + totalTime + "ns\n");
        
        startTime = System.nanoTime();
        TreeSort.sort(avlCr, c);
        endTime = System.nanoTime();// tracks runtime in nanoseconds (end)
        totalTime = endTime - startTime;
        System.out.println("AVL(rev-sorted)\t" + totalTime + "ns\n");
        
        System.out.printf("Sorted%n%s%n", Arrays.toString(a));
        System.out.printf("Sorted%n%s%n", Arrays.toString(b));
        System.out.printf("Sorted%n%s%n", Arrays.toString(c));
    }
}
