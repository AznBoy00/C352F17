
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


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
    private static int keyLength = 6;
    
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
    
    private int getThreshold(){
        return elementCount;
    }
    
    private int setKeyLength(int length) {
        if (length > 12 || length < 6) {
            System.out.println("Key length input is invalid, please input a valid length between 6-12.");
            return -1;
        }
        return length;
    }
    
    private int getKeyLength() {
        return keyLength;
    }
    
    public String[] generate(int n) {
        String[] keySequence = new String[n];
        Random randomNum = new Random(); // Create random object
        final int MIN_STRING_LENGTH = 6; // Minimum length of key to generate
        final int MAX_STRING_LENGTH = 13; // Maximum length of key to generate - 1
        setKeyLength(randomNum.nextInt(MAX_STRING_LENGTH - MIN_STRING_LENGTH) + MIN_STRING_LENGTH); // Assign a random length of key

        String randomKey = generateHelper();

        for (int i = 0; i < keySequence.length; i++) {
            while (duplicate(randomKey)) {
                randomKey = generateHelper();
            }
            keySequence[i] = randomKey;
        }
        return keySequence;
    }

    private String generateHelper() {
        final String alphanumericSeq = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        String randomKey = "";
        Random randomNum = new Random(); // Create random object

        for (int i = 0; i < getKeyLength(); i++) {
            randomKey += alphanumericSeq.charAt(randomNum.nextInt(alphanumericSeq.length()));
        }
        return randomKey;
    }

    private boolean duplicate(String key) {
        if (key.length() > 12 || key.length() < 6) {
            System.out.println("Key entered is too long or too short. (Only strings between 6-12 is allowed.)");
            return true;
        }
        if (registry.getValues(key).getKey() == null) {
            return false;
        } else {
            return true;
        }
    }
}
