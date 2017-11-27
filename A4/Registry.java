
import java.util.ArrayList;


/**
 *COMP/SOEN Program
 *By: Kevin Lin, Concordia University, 40002383
 * */
/**
 *
 * @author Lin_K
 */
public interface Registry {
    public String[] allKeys();
    public void add(String key, Car c); // add(key,value2): add an entry for the given key and value
    public void remove(String key); // remove(key): remove the entry for the given key
    public Car getValues(String key); // getValues(key): return the values of the given key
    public String nextKey(String key); // nextKey(key): return the key for the successor of key
    public String prevKey(String key); // prevKey(key): return the key for the predecessor of key
    public ArrayList<Car> previousCars(String key); // previousCars(key): returns a sequence (sorted in reverse chronological order) of cars(previously) registered with the given key (license plate).
}
