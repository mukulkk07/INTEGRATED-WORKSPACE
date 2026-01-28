// 1. PACKAGES: Organize code into folders (optional but recommended for big projects)
package com.learning.syntax;

// 2. IMPORTS: Bring in pre-written code from Java's libraries
import java.util.Scanner; // Imports the Scanner class to get user input

/* 3. CLASS DECLARATION:
 * Everything in Java must live inside a Class.
 * The class name (SyntaxShowcase) MUST match the filename (SyntaxShowcase.java).
 * 'public' means other classes can see this.
 */
public class JAVASYNTAX {

    /* 4. THE MAIN METHOD:
     * This is the entry point. When you run the program, Java looks for this specific line.
     * public: accessible everywhere
     * static: belongs to the class itself, not a specific object
     * void: returns nothing
     * main: the name of the method
     * String[] args: allows command-line arguments
     */
    public static void main(String[] args) {

        // --- A. VARIABLES & TYPES ---
        // Java is "Statically Typed" - you must declare the type of data first.

        int wholeNumber = 10;           // Integer (whole number)
        double decimalNumber = 19.99;   // Decimal number
        boolean isJavaFun = true;       // True or False
        char letter = 'A';              // Single character (single quotes)
        String text = "Hello Java";     // Sequence of characters (double quotes)

        // Note: Lines must end with a semicolon (;). It acts like a period in a sentence.

        // --- B. OUTPUT ---
        // System.out.println prints text and moves to a new line.
        System.out.println("Variable Check: " + text);

        // --- C. CONTROL FLOW (Logic) ---
        // Code blocks are enclosed in curly braces { }.

        if (wholeNumber > 5) {
            System.out.println("The number is greater than 5.");
        } else {
            System.out.println("The number is small.");
        }

        // --- D. LOOPS (Repetition) ---

        // The "For" Loop: (Start; Stop Condition; Step)
        System.out.print("Counting: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " "); // Prints 0 1 2 3 4
        }
        System.out.println(); // New line

        // --- E. METHOD CALL ---
        // We are calling the custom method defined below.
        int result = addNumbers(5, 10);
        System.out.println("Method Result: " + result);
    }

    /* 5. CUSTOM METHODS:
     * Defined outside 'main' but inside the 'class'.
     * This method expects two 'int' inputs and promises to return an 'int'.
     */
    public static int addNumbers(int a, int b) {
        return a + b; // 'return' sends data back to where the method was called
    }

} // End of Class