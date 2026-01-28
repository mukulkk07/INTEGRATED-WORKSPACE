import java.io.File;           // Represents a file on your hard drive
import java.io.FileWriter;     // Used to WRITE text to a file
import java.util.Scanner;      // Used to READ text from a file
import java.time.LocalDateTime; // Modern Date/Time handling
import java.time.format.DateTimeFormatter; // To make the date look pretty

class DiaryManager {
    
    // We will create a file named "diary.txt" in your current folder
    private static final String FILE_NAME = "diary.txt";

    // METHOD 1: WRITE TO FILE
    public void writeEntry(String message) {
        
        // 1. GET CURRENT TIME
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        // 2. TRY-WITH-RESOURCES (The "Modern" Syntax)
        // Notice the 'try' has parentheses ( ... ).
        // Anything opened inside these ( ) will be CLOSED automatically when done.
        // The 'true' in FileWriter means "Append mode" (don't overwrite, add to the end).
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            
            writer.write("[" + timestamp + "] " + message + "\n");
            System.out.println(">> Success: Entry saved to disk.");

        } catch (Exception e) {
            System.out.println("!! Error writing to file: " + e.getMessage());
        }
    }

    // METHOD 2: READ FROM FILE
    public void readDiary() {
        File file = new File(FILE_NAME);

        // Check if file exists first
        if (!file.exists()) {
            System.out.println(">> No diary found yet. Write something first!");
            return;
        }

        System.out.println("\n--- READING DIARY ---");
        
        try (Scanner fileReader = new Scanner(file)) {
            
            // Loop while the file has another line
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("!! Error reading file: " + e.getMessage());
        }
        System.out.println("---------------------\n");
    }
}

// MAIN CLASS
public class OOPEXAMPLE2 {

    public static void main(String[] args) {
        System.out.println("--- PERSONAL DIARY APP ---");

        DiaryManager myDiary = new DiaryManager();

        // 1. Let's write some data
        // This will create 'diary.txt' in your folder if it doesn't exist.
        myDiary.writeEntry("System started.");
        myDiary.writeEntry("I learned about File I/O today.");
        myDiary.writeEntry("Java makes handling dates easier than I thought.");

        // 2. Let's read it back
        myDiary.readDiary();

        System.out.println("--- SYSTEM END ---");
        
        // TIP: If you run this program a second time, it will add NEW lines
        // instead of deleting the old ones, because of the 'append' mode.
    }
}