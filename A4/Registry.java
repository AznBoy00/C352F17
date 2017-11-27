
/**
 *COMP/SOEN Program
 *By: Kevin Lin, Concordia University, 40002383
 * */
/**
 *
 * @author Lin_K
 */
public interface Registry {
    public void add(String key, Car c); // add(key,value2): add an entry for the given key and value
    public String remove(String key); // remove(key): remove the entry for the given key
    public Car getValues(String key); // getValues(key): return the values of the given key
    public Node nextKey(String key); // nextKey(key): return the key for the successor of key
    public Node prevKey(String key); // prevKey(key): return the key for the predecessor of key
}
