
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
    CarRegistryLL registryLL;
    AVLTree registryAVL;
    
    public RegistryGenerator(String fis) {        
        try {
            initializeData(fis);
        } catch(IOException e) {
            System.out.println("IOException caught.\nProgram shutting down.");
            System.exit(1);
        }
    }
    
    private void initializeData(String fis) throws IOException{
        int elementCount = 0;
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
        
        if (elementCount > 500000) {
            this.registryLL = new CarRegistryLL();
            while (lineContent != null) {
                lineContent = br.readLine();
                if (lineContent != null) {
                    registryLL.addToList(lineContent);
                }
            }
        } else if (elementCount > 100) {
            this.registryAVL = new AVLTree();
            while (lineContent != null) {
                lineContent = br.readLine();
                if (lineContent != null) {
                    registryAVL.add(lineContent);
                }
            }
        } else {
            this.registryLL = new CarRegistryLL();
            while (lineContent != null) {
                lineContent = br.readLine();
                if (lineContent != null) {
                    registryLL.addToList(lineContent);
                }
            }
        }
        br.close();
    }
    
}
