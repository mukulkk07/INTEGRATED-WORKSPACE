// 1. INTERFACE: A contract.
interface Refuelable {
    void refuel();
}

// 2. ABSTRACT CLASS: The Parent Blueprint.
abstract class Vehicle implements Refuelable {
    
    private String brand;  // Encapsulation: Private data
    protected int speed;   // Protected: Accessible to children

    public Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public String getBrand() {
        return brand;
    }

    public void honk() {
        System.out.println(brand + " says: Beep Beep!");
    }

    // Abstract method: Children MUST define how to drive.
    public abstract void drive();
}

// 3. CONCRETE CLASS: The actual object.
class Car extends Vehicle {

    public Car(String brand, int speed) {
        super(brand, speed);
    }

    @Override
    public void drive() {
        System.out.println(getBrand() + " is zooming at " + speed + " mph.");
    }

    @Override
    public void refuel() {
        System.out.println(getBrand() + " is pumping gas.");
    }
}

// 4. MAIN CLASS (Must match filename 'OOPJAVASYNTAX.java' exactly)
public class OOPJAVASYNTAX {

    public static void main(String[] args) {
        System.out.println("--- SYSTEM START ---");

        // Polymorphism: A 'Vehicle' variable holding a 'Car' object
        Vehicle myRide = new Car("Ferrari", 200);

        myRide.honk();
        myRide.drive();
        myRide.refuel();

        System.out.println("--- SYSTEM END ---");
    }
}