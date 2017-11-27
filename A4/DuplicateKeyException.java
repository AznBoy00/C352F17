/*
    Assignment #4
    Written by: Kevin Lin - 40002383
 */

/**
 *
 * @author Kevin Lin - @AznBoy00
 */
public class DuplicateKeyException extends Exception{
    private int duplicatedIndex;
    
    public DuplicateKeyException() {
        super("Duplicated Key found.");
    }
    
    public DuplicateKeyException(int duplicatedIndex) {
        super("Diplicated Key found at index " + duplicatedIndex + ".");
    }
    
    public DuplicateKeyException(String s) {
        super(s);
    }

    public int getDuplicatedIndex() {
        return duplicatedIndex;
    }
    
    public String getMessage() {
        return super.getMessage();
    }
}
