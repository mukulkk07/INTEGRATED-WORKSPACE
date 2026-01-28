import java.util.ArrayList;
import java.util.List;

// 1. ENUMS (Enumerations)
// Instead of using numbers (1, 2, 3) or Strings ("Small"), we create a specific TYPE.
// This prevents bugs. You literally CANNOT order a "HUGE" pizza because it's not in the list.
enum PizzaSize {
    SMALL, MEDIUM, LARGE, MONSTER
}

enum OrderStatus {
    PLACED, BAKING, DELIVERING, DELIVERED
}

class PizzaOrder {
    
    private PizzaSize size;
    private OrderStatus status;
    private List<String> toppings = new ArrayList<>();

    public PizzaOrder(PizzaSize size) {
        this.size = size;
        this.status = OrderStatus.PLACED; // We always start as PLACED
    }

    public void addTopping(String t) {
        toppings.add(t);
    }

    // 2. SWITCH STATEMENT
    // This looks at the 'status' variable and jumps to the matching 'case'.
    public void advanceStatus() {
        switch (status) {
            case PLACED:
                status = OrderStatus.BAKING;
                System.out.println(">> Kitchen: Pizza is now in the oven.");
                break; // 'break' is mandatory! Without it, Java falls into the next case automatically.
            
            case BAKING:
                status = OrderStatus.DELIVERING;
                System.out.println(">> Driver: Pizza is on the way!");
                break;
            
            case DELIVERING:
                status = OrderStatus.DELIVERED;
                System.out.println(">> Customer: Ding dong! Pizza arrived.");
                break;
                
            case DELIVERED:
                System.out.println(">> Error: You already ate this.");
                break;
        }
    }

    // 3. STRINGBUILDER (Text Construction)
    // In Java, using "A" + "B" + "C" creates hidden temporary objects in memory.
    // StringBuilder is a mutable object specifically designed to build long strings efficiently.
    public String generateReceipt() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n=== OFFICIAL RECEIPT ===\n");
        sb.append("Size: ").append(size).append("\n");
        sb.append("Current Status: ").append(status).append("\n");
        sb.append("Toppings Included: ");
        
        for (String t : toppings) {
            sb.append(t).append(", ");
        }
        
        sb.append("\n========================\n");
        
        return sb.toString(); // Convert the builder back to a normal String
    }
}

// 4. MAIN CLASS
public class OOPEXAMPLE1 {

    public static void main(String[] args) {
        System.out.println("--- DOMINOS SIMULATOR ---");

        // Creating the order using the Enum (Notice we don't use quotes "LARGE")
        PizzaOrder myOrder = new PizzaOrder(PizzaSize.LARGE);
        
        myOrder.addTopping("Pepperoni");
        myOrder.addTopping("Mushrooms");
        myOrder.addTopping("Extra Cheese");

        // Print initial receipt
        System.out.println(myOrder.generateReceipt());

        // Simulate the pizza lifecycle
        myOrder.advanceStatus(); // PLACED -> BAKING
        myOrder.advanceStatus(); // BAKING -> DELIVERING
        myOrder.advanceStatus(); // DELIVERING -> DELIVERED
        
        // One last check of the receipt to see the status change
        System.out.println(myOrder.generateReceipt());

        System.out.println("--- SYSTEM END ---");
    }
}