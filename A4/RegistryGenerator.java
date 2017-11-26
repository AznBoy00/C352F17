
import java.io.FileInputStream;


/**
 *  COMP/SOEN Program
 *  By: Kevin Lin, Concordia University, 40002383
 * */
/**
 *
 * @author Lin_K
 */
public class RegistryGenerator {

    CarRegistryLL reg6;
    CarRegistryLL reg7;
    CarRegistryLL reg8;
    CarRegistryLL reg9;
    CarRegistryLL reg10;
    CarRegistryLL reg11;
    CarRegistryLL reg12;
    
    public RegistryGenerator(FileInputStream fis) {
        reg6 = new CarRegistryLL();
        reg7 = new CarRegistryLL();
        reg8 = new CarRegistryLL();
        reg9 = new CarRegistryLL();
        reg10 = new CarRegistryLL();
        reg11 = new CarRegistryLL();
        reg12 = new CarRegistryLL();
        initializeData(fis);
    }
    
    private void initializeData(FileInputStream fis) {
        
    }
    
}
