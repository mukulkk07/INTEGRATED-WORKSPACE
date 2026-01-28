import java.util.ArrayList;
import java.util.Comparator; // Used for sorting
import java.util.List;
import java.util.stream.Collectors; // Used to package results back into a List

// A simple data class (POJO - Plain Old Java Object)
class Product {
    private String name;
    private double price;
    private String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class OOPEXAMPLE3 {

    public static void main(String[] args) {
        System.out.println("--- STORE ANALYTICS SYSTEM ---");

        // 1. SETUP DATA
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1200.00, "Tech"));
        products.add(new Product("Mouse", 25.00, "Tech"));
        products.add(new Product("Desk Chair", 150.00, "Furniture"));
        products.add(new Product("HDMI Cable", 10.00, "Tech"));
        products.add(new Product("Coffee Table", 200.00, "Furniture"));

        // ==========================================
        // CONCEPT 1: FILTERING (The "Stream" way)
        // ==========================================
        // Old Way: We would write a 'for' loop and an 'if' statement.
        // New Way: We treat data like a stream of water flowing through pipes.
        
        System.out.println("\n[1] Tech Products under $100:");
        
        List<Product> cheapTech = products.stream()
            // FILTER: Keep only items where this Lambda equation is true
            // Syntax: (variable) -> (logic check)
            .filter(p -> p.getCategory().equals("Tech"))
            .filter(p -> p.getPrice() < 100)
            .collect(Collectors.toList()); // Collect the drops back into a list

        // METHOD REFERENCE (System.out::println)
        // Shortcut for: p -> System.out.println(p)
        cheapTech.forEach(System.out::println);


        // ==========================================
        // CONCEPT 2: TRANSFORMATION (.map)
        // ==========================================
        // We want to create a list of JUST the names, converted to Uppercase.
        
        System.out.println("\n[2] Product Names (Uppercase):");
        
        products.stream()
            .map(p -> p.getName().toUpperCase()) // Transform Product -> String
            .forEach(System.out::println);


        // ==========================================
        // CONCEPT 3: SORTING
        // ==========================================
        // Let's sort from Cheapest to Most Expensive.
        
        System.out.println("\n[3] Sorted by Price:");

        products.stream()
            // Comparator.comparing is a helper that reads the price for sorting
            .sorted(Comparator.comparing(Product::getPrice)) 
            .forEach(p -> System.out.println(p.getName() + ": " + p.getPrice()));
            
        System.out.println("\n--- SYSTEM END ---");
    }
}