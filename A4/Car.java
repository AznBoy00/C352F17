
/**
 *COMP/SOEN Program
 *By: Kevin Lin, Concordia University, 40002383
 * */
/**
 *
 * @author Lin_K
 */
public class Car {
    private String brand;
    private String owner;
    private int year;
    private int wheels;
    private int doors;
    private double price;
    private String key;
    
    public Car(String brand, String owner, int year, int wheels, int doors, double price, String key) {
        this.brand = brand;
        this.owner = owner;
        this.year = year;
        this.wheels = wheels;
        this.doors = doors;
        this.price = price;
        this.key = key;
    }

    public Car(String key) {
        this.brand = "EDIT THIS";
        this.owner = "EDIT THIS";
        this.year = 0;
        this.wheels = 0;
        this.doors = 0;
        this.price = 0;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" + "brand=" + brand + ", owner=" + owner + ", year=" + year + ", wheels=" + wheels + ", doors=" + doors + ", price=" + price + '}';
    }
}
