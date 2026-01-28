import java.util.ArrayList; // Advanced: Resizable Arrays
import java.util.List;      // Advanced: Interface for Lists

// 1. INTERFACE: The contract
interface SoundMaker {
    void makeSound();
}

// 2. ABSTRACT CLASS: The Base
abstract class Animal implements SoundMaker {
    
    // Encapsulation
    private String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void sleep() {
        System.out.println(name + " is sleeping...");
    }
}

// 3. SUBCLASSES
class Lion extends Animal {
    public Lion(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " roars loudly!");
    }
}

class Parrot extends Animal {
    public Parrot(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " squawks!");
    }

    // A method specific ONLY to Parrots
    public void fly() {
        System.out.println(getName() + " is flying high.");
    }
}

// 4. MAIN CLASS (Matches your filename 'OOPJAVASYNTAX.java')
public class JAVAOOPSYNTAX {

    public static void main(String[] args) {

        System.out.println("--- ADVANCED ZOO SYSTEM ---");

        // COMPLEX SYNTAX: Collections
        // Instead of 'Animal[]', we use 'ArrayList'.
        // This list can grow dynamically and hold any type of Animal.
        List<Animal> zoo = new ArrayList<>();

        // Adding different objects to the same list (Polymorphism)
        zoo.add(new Lion("Simba", 5));
        zoo.add(new Parrot("Polly", 2));
        zoo.add(new Lion("Mufasa", 12));

        // COMPLEX SYNTAX: Enhanced For-Loop
        // Read as: "For every Animal 'a' inside the 'zoo' list"
        for (Animal a : zoo) {
            
            // 1. Polymorphic call (works for all animals)
            a.makeSound(); 

            // 2. COMPLEX SYNTAX: 'instanceof' and Casting
            // We check: "Is this specific animal actually a Parrot?"
            if (a instanceof Parrot) {
                // If yes, we treat it like a Parrot to access the fly() method.
                ((Parrot) a).fly(); 
            }
            
            System.out.println("---");
        }
    }
}