
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *  COMP/SOEN Program
 *  By: Kevin Lin, Concordia University, 40002383
 * */
/**
 *
 * @author Lin_K
 */
public class RegistryGenerator {
    Registry registry;
    private static int elementCount;
    
    public RegistryGenerator(String fis) {        
        try {
            initializeData(fis);
        } catch(IOException e) {
            System.out.println("IOException caught.\nProgram shutting down.");
            System.exit(1);
        }
    }
    
    private void initializeData(String fis) throws IOException{
        elementCount = 0;
        BufferedReader br = null;
        String s = "", lineContent = "";
        
        try {
            br = new BufferedReader(new FileReader(fis));
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException caught.\nProgram shutting down.");
            System.exit(1);
        }
        while (lineContent != null) {
            lineContent = br.readLine();
            if (lineContent != null) {
                elementCount++;
            }
        }
        
        setThreshold(elementCount, fis);
        
        br.close();
    }
    
    private void setThreshold(int threshold, String fis) throws IOException{
        BufferedReader br = null;
        String s = "", lineContent = "";
        
        try {
            br = new BufferedReader(new FileReader(fis));
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException caught.\nProgram shutting down.");
            System.exit(1);
        }
        
        if (threshold > 500000) {
            this.registry = new CarRegistryLL();
            while (lineContent != null) {
                lineContent = br.readLine();
                if (lineContent != null) {
                    registry.add(lineContent, new Car(lineContent));
                }
            }
        } else if (threshold > 100) {
            this.registry = new AVLTree();
            while (lineContent != null) {
                lineContent = br.readLine();
                if (lineContent != null) {
                    registry.add(lineContent, new Car(lineContent));
                }
            }
        } else {
            this.registry = new CarRegistryLL();
            while (lineContent != null) {
                lineContent = br.readLine();
                if (lineContent != null) {
                    registry.add(lineContent, new Car(lineContent));
                }
            }
        }
        br.close();
    }
}
