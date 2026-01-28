import java.util.HashMap;
import java.util.Map;

class GradeBook {
    
    // KEY = Student Name (String)
    // VALUE = Score (Integer)
    private Map<String, Integer> scores = new HashMap<>();

    public void addStudent(String name, int score) {
        scores.put(name, score);
    }

    public void printAllGrades() {
        System.out.println("\n--- CLASS ROSTER ---");
        
        // LOOPING THROUGH A MAP
        // We iterate over the 'keySet' (the list of names)
        for (String student : scores.keySet()) {
            
            // We use .get(key) to retrieve the value
            int score = scores.get(student);
            String letter = calculateLetter(score);
            
            System.out.println(student + ": " + score + " [" + letter + "]");
        }
    }

    public void printStatistics() {
        if (scores.isEmpty()) return;

        int sum = 0;
        int max = Integer.MIN_VALUE; // Start with the lowest possible number
        int min = Integer.MAX_VALUE; // Start with the highest possible number

        // 'var' KEYWORD (Java 10+)
        // Java infers that 'score' is an Integer because scores.values() contains integers.
        for (var score : scores.values()) {
            sum += score;
            
            // MATH CLASS
            // Math.max(a, b) returns whichever number is bigger
            max = Math.max(max, score);
            min = Math.min(min, score);
        }

        double average = (double) sum / scores.size();

        System.out.println("\n--- STATISTICS ---");
        System.out.println("Class Average: " + average);
        System.out.println("Highest Score: " + max);
        System.out.println("Lowest Score:  " + min);
    }

    // Helper method to convert number to letter
    private String calculateLetter(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
}

// MAIN CLASS
public class OOPEXAMPLE5 {

    public static void main(String[] args) {
        System.out.println("--- GRADEBOOK SYSTEM ---");

        GradeBook mathClass = new GradeBook();

        // Adding data to the Map
        mathClass.addStudent("Alice", 95);
        mathClass.addStudent("Bob", 72);
        mathClass.addStudent("Charlie", 48);
        mathClass.addStudent("Diana", 88);

        // HashMap keys must be unique!
        // If we add "Alice" again, it OVERWRITES the old 95 with 98.
        mathClass.addStudent("Alice", 98); 

        mathClass.printAllGrades();
        mathClass.printStatistics();
        
        System.out.println("\n--- SYSTEM END ---");
    }
}